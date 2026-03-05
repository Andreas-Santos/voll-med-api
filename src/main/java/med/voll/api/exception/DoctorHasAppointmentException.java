package med.voll.api.exception;

public class DoctorHasAppointmentException extends RuntimeException {
    public DoctorHasAppointmentException(String message) {
        super(message);
    }
}
