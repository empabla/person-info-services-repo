package pl.kurs.personservice.validator.implementation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.kurs.personservice.validator.LettersOnly;

public class LettersOnlyValidator implements ConstraintValidator<LettersOnly, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^[a-zA-Z ]*$");
    }

}
