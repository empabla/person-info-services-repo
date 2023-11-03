package pl.kurs.personservice.exception.constraint;

import org.springframework.stereotype.Service;
import pl.kurs.personservice.exception.handler.ExceptionResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonUniquePeselConstraintErrorHandler implements ConstraintErrorHandler {

    @Override
    public ExceptionResponseBody mapToErrorDto() {
        return new ExceptionResponseBody(
                List.of("Duplicated entry for 'pesel' field."),
                "PESEL_NOT_UNIQUE",
                LocalDateTime.now()
        );
    }

    @Override
    public String getConstraintName() {
        return "UC_PERSON_PESEL";
    }

}
