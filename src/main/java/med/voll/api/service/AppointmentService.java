package med.voll.api.service;

import med.voll.api.dto.AppointmentRequest;
import med.voll.api.exception.*;
import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.model.Patient;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.validator.Appointment.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public void registerAppointment(AppointmentRequest request){
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

        appointmentRepository.save(new Appointment(patient, doctor, request.date()));
    }
}
