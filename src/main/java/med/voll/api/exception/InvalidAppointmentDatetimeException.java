package med.voll.api.exception;

public class InvalidAppointmentDatetimeException extends RuntimeException {
    public InvalidAppointmentDatetimeException(String message) {
        super(message);
    }
}
