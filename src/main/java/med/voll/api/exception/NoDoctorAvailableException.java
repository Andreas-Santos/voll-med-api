package med.voll.api.exception;

public class NoDoctorAvailableException extends RuntimeException {
    public NoDoctorAvailableException(String message) {
        super(message);
    }
}
