package pl.kurs.dictionaryservice.exception;

public class DictionaryValueNotFoundException extends RuntimeException {

    public DictionaryValueNotFoundException(String message) {
        super(message);
    }

}
