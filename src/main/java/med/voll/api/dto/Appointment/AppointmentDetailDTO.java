package med.voll.api.dto.Appointment;

import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.model.Patient;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(
        Long id,
        Patient patient,
        Doctor doctor,
        LocalDateTime date,
        Boolean canceled,
        String cancelDescription
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(
                appointment.getId(), appointment.getPatient(), appointment.getDoctor(), appointment.getDate(),
                appointment.getCanceled(), appointment.getCancelDescription()
        );
    }
}
