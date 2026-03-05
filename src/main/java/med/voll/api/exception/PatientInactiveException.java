package med.voll.api.exception;

public class PatientInactiveException extends RuntimeException {
    public PatientInactiveException(String message) {
        super(message);
    }
}
