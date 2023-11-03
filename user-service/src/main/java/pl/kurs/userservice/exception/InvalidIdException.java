package pl.kurs.userservice.exception;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String message) {
        super(message);
    }

}
