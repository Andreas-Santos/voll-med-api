package med.voll.api.service;

import med.voll.api.dto.AppointmentRequest;
import med.voll.api.exception.DoctorNotFoundException;
import med.voll.api.exception.PatientNotFoundException;
import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.model.Patient;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.validator.Appointment.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

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

    public void registerAppointment(AppointmentRequest request) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(request.patient());
        if(patientOptional.isEmpty()) {
            throw new PatientNotFoundException("Não existe paciente com esse id!");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(request.doctor());
        if(doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Não existe médico com esse id!");
        }

        validators.forEach(v -> v.validate(request));

        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();

        appointmentRepository.save(new Appointment(patient, doctor, request.date()));
    }
}
