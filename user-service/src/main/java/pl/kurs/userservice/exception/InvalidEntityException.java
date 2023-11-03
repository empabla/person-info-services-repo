package pl.kurs.userservice.exception;

public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException(String message) {
        super(message);
    }

}
