package pl.kurs.dictionaryservice.exception;

public class DictionaryNotFoundException extends RuntimeException {

    public DictionaryNotFoundException(String message) {
        super(message);
    }

}
