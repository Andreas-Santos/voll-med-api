package med.voll.api.exception;

public class InvalidJWTTokenException extends RuntimeException {
    public InvalidJWTTokenException(String message) {
        super(message);
    }
}
