package pl.kurs.dictionaryservice.exception.constraint;


import pl.kurs.dictionaryservice.exception.handler.ExceptionResponseBody;

public interface ConstraintErrorHandler {

    ExceptionResponseBody mapToErrorDto();

    String getConstraintName();

}