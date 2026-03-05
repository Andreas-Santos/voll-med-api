package med.voll.api.exception;

public class AppointmentAlreadyCanceledException extends RuntimeException {
    public AppointmentAlreadyCanceledException(String message) {
        super(message);
    }
}
