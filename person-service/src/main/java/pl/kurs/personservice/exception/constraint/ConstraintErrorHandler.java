package pl.kurs.personservice.exception.constraint;

import pl.kurs.personservice.exception.handler.ExceptionResponseBody;

public interface ConstraintErrorHandler {

    ExceptionResponseBody mapToErrorDto();

    String getConstraintName();

}