package med.voll.api.exception;

public class DoctorInactiveException extends RuntimeException {
    public DoctorInactiveException(String message) {
        super(message);
    }
}
