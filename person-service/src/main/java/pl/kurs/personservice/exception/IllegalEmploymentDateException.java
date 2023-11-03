package pl.kurs.personservice.exception;

public class IllegalEmploymentDateException extends RuntimeException {

    public IllegalEmploymentDateException(String message) {
        super(message);
    }

}
