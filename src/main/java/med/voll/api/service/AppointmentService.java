package med.voll.api.service;

import med.voll.api.dto.Appointment.AppointmentCancelRequest;
import med.voll.api.dto.Appointment.AppointmentDTO;
import med.voll.api.dto.Appointment.AppointmentDetailDTO;
import med.voll.api.dto.Appointment.AppointmentRequest;
import med.voll.api.exception.*;
import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.model.Patient;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.validator.Appointment.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    List<AppointmentValidator> validators;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment registerAppointment(AppointmentRequest request){
        Patient patient = patientRepository.findById(request.patient())
                .orElseThrow(() -> new PatientNotFoundException("Não existe paciente com esse id!"));

        if(!patient.getActive()) {
            throw new PatientInactiveException("Paciente inativo!");
        }

        Doctor doctor;

        if(request.doctor() != null) {
            doctor = doctorRepository.findById(request.doctor())
                    .orElseThrow(() -> new DoctorNotFoundException("Não existe médico com esse id!"));
        }
        else {
            LocalDateTime start = request.date().minusHours(1);
            LocalDateTime end = start.plusHours(2);

            doctor = doctorRepository
                    .findFirstAvailableDoctor(start, end)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new NoDoctorAvailableException("Nenhum médico disponível nesse horário"));
        }

        if(!doctor.getActive()) {
            throw new DoctorInactiveException("Médico inativo!");
        }

        validators.forEach(v -> v.validate(request));

        Appointment appointment = new Appointment(patient, doctor, request.date());

        appointmentRepository.save(appointment);

        return appointment;
    }

    public Page<AppointmentDTO> getAppointments(Pageable pagination) {
        Page<Appointment> appointments = appointmentRepository.findAll(pagination);

        System.out.println(appointments);

        return appointments.map(AppointmentDTO::new);
    }

    public AppointmentDetailDTO getAppointmentDetail(Long id) {
        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Não existe consulta com esse id!"));

        return new AppointmentDetailDTO(appointment);
    }

    public void cancelAppointment(Long id, AppointmentCancelRequest request) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Não existe consulta com esse id!"));

        if(appointment.getCanceled() == true) {
            throw new AppointmentAlreadyCanceledException("Essa consulta já foi cancelada!");
        }

        if(LocalDateTime.now().plusHours(24).isAfter(appointment.getDate())) {
            throw new InvalidAppointmentDatetimeException(
                    "Só é possível cancelar consultas com pelo menos 24 horas de antecedência!"
            );
        }

        appointment.cancelAppointment(request);
    }
}
