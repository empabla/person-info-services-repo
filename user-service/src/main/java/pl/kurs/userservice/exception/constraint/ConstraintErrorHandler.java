package pl.kurs.userservice.exception.constraint;


import pl.kurs.userservice.exception.handler.ExceptionResponseBody;

public interface ConstraintErrorHandler {

    ExceptionResponseBody mapToErrorDto();

    String getConstraintName();

}