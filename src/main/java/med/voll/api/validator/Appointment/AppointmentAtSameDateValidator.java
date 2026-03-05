package med.voll.api.validator.Appointment;

import med.voll.api.dto.AppointmentRequest;
import med.voll.api.exception.PatientHasAppointmentException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentAtSameDateValidator implements AppointmentValidator {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentRequest request) {
        LocalDateTime startOfDay = request.date().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = request.date().toLocalDate().plusDays(1).atStartOfDay();

        boolean patientHasAppointment =
                appointmentRepository.patientHasAppointmentThatDay(
                        request.patient(),
                        startOfDay,
                        endOfDay
                );

        if(patientHasAppointment) {
            throw new PatientHasAppointmentException(
                    "O paciente já possui um atendimento agendado para este mesmo dia!"
            );
        }
    }
}
