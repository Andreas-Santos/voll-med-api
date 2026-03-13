package med.voll.api.validator.Appointment;

import med.voll.api.dto.Appointment.AppointmentRequest;
import med.voll.api.exception.DoctorHasAppointmentException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DoctorIsFreeValidator implements AppointmentValidator {

    private final AppointmentRepository appointmentRepository;

    public DoctorIsFreeValidator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validate(AppointmentRequest request) {
        if (request.doctor() == null) {
            return;
        }

        LocalDateTime start = request.date().minusHours(1);
        LocalDateTime end = start.plusHours(2);

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
