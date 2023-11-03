package pl.kurs.dictionaryservice.exception.constraint;

import org.springframework.stereotype.Service;
import pl.kurs.dictionaryservice.exception.handler.ExceptionResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DictionaryUniqueNameConstraintErrorHandler implements ConstraintErrorHandler {

    @Override
    public ExceptionResponseBody mapToErrorDto() {
        return new ExceptionResponseBody(
                List.of("Duplicated entry for 'name' field."),
                "NAME_NOT_UNIQUE",
                LocalDateTime.now()
        );
    }

    @Override
    public String getConstraintName() {
        return "UC_DICTIONARY_NAME";
    }

}
