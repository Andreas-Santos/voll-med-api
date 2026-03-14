package med.voll.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handlerGeneralException(Exception ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handlerEmptyBodyException() {
        ResponseError response = new ResponseError(
                "O corpo da requisição é obrigatório!",
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ResponseError> handlerPatientNotFoundException(PatientNotFoundException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ResponseError> handlerDoctorNotFoundException(DoctorNotFoundException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidAppointmentDatetimeException.class)
    public ResponseEntity<ResponseError> handlerInvalidAppointmentDatetimeException(
            InvalidAppointmentDatetimeException ex
    ) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PatientInactiveException.class)
    public ResponseEntity<ResponseError> handlerPatientInactiveException(PatientInactiveException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DoctorInactiveException.class)
    public ResponseEntity<ResponseError> handlerDoctorInactiveException(DoctorInactiveException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DoctorHasAppointmentException.class)
    public ResponseEntity<ResponseError> handlerDoctorHasAppointmentException(DoctorHasAppointmentException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PatientHasAppointmentException.class)
    public ResponseEntity<ResponseError> handlerPatientHasAppointmentException(PatientHasAppointmentException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoDoctorAvailableException.class)
    public ResponseEntity<ResponseError> handlerNoDoctorAvailableException(NoDoctorAvailableException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ResponseError> handlerAppointmentNotFoundException(AppointmentNotFoundException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AppointmentAlreadyCanceledException.class)
    public ResponseEntity<ResponseError> handlerAppointmentAlreadyCanceledException(AppointmentAlreadyCanceledException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidJWTTokenException.class)
    public ResponseEntity<ResponseError> handlerInvalidJWTTokenException(InvalidJWTTokenException ex) {
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
