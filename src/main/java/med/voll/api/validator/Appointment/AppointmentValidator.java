package med.voll.api.validator.Appointment;

import med.voll.api.dto.AppointmentRequest;

public interface AppointmentValidator {
    void validate(AppointmentRequest request);
}
