package med.voll.api.exception;

public class PatientHasAppointmentException extends RuntimeException {
    public PatientHasAppointmentException(String message) {
        super(message);
    }
}
