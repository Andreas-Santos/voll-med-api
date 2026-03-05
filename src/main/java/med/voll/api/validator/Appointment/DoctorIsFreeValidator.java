package med.voll.api.validator.Appointment;

import med.voll.api.dto.AppointmentRequest;
import med.voll.api.exception.DoctorHasAppointmentException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DoctorIsFreeValidator implements AppointmentValidator {
    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(AppointmentRequest request) {
        LocalDateTime start = request.date();
        LocalDateTime end = start.plusHours(1);

        boolean doctorHasAppointment = appointmentRepository.doctorHasAppointment(
                request.doctor(),
                start,
                end
        );

        if(doctorHasAppointment) {
            throw new DoctorHasAppointmentException("Horário indisponível para o médico selecionado!");
        }
    }
}
