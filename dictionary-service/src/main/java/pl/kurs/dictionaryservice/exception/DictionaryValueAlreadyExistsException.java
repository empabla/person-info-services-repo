package pl.kurs.dictionaryservice.exception;

public class DictionaryValueAlreadyExistsException extends RuntimeException {

    public DictionaryValueAlreadyExistsException(String message) {
        super(message);
    }

}
