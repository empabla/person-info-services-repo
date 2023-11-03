package pl.kurs.userservice.validator.implementation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.kurs.userservice.validator.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null || password.isEmpty() || password.length() < 7)
            return false;
        return password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
    }

}