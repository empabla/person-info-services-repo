package pl.kurs.personservice.exception;

public class MissingRequiredFieldsException extends RuntimeException {

    public MissingRequiredFieldsException(String message) {
        super(message);
    }
}
