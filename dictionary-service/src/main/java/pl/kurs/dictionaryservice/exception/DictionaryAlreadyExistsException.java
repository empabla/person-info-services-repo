package pl.kurs.dictionaryservice.exception;

public class DictionaryAlreadyExistsException extends RuntimeException {

    public DictionaryAlreadyExistsException(String message) {
        super(message);
    }

}
