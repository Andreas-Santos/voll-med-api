package med.voll.api.dto.Appointment;

import med.voll.api.model.Appointment;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        Long patient,
        Long doctor,
        LocalDateTime date,
        Boolean canceled
) {
    public AppointmentDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getDate(),
                appointment.getCanceled()
        );
    }
}
