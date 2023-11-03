package pl.kurs.personservice.exception;

public class PositionNotBelongToEmployeeException extends RuntimeException {

    public PositionNotBelongToEmployeeException(String message) {
        super(message);
    }

}
