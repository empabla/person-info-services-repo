package pl.kurs.personservice.exception;

public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException(String message) {
        super(message);
    }

}
