package med.voll.api.validator.Appointment;

import med.voll.api.dto.Appointment.AppointmentRequest;

public interface AppointmentValidator {
    void validate(AppointmentRequest request);
}
