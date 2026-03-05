package med.voll.api.validator.Appointment;

import med.voll.api.dto.AppointmentRequest;
import med.voll.api.exception.InvalidAppointmentDatetimeException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class AppointmentDatetimeValidator implements AppointmentValidator {

    @Override
    public void validate(AppointmentRequest request) {
        if(request.date().getDayOfWeek() == DayOfWeek.SUNDAY
                || request.date().getHour() > 18 || request.date().getHour() < 7)
        {
            throw new InvalidAppointmentDatetimeException(
                    "O horário de atendimento da clínica é de segunda a sábado das " +
                            "07 às 19hrs (agendamentos até às 18hrs)"
            );
        }

        if(request.date().isAfter(LocalDateTime.now().minusMinutes(30))) {
            throw new InvalidAppointmentDatetimeException(
                    "A antecedência miníma de agendamento de uma consulta é de 30 minutos"
            );
        }
    }
}
