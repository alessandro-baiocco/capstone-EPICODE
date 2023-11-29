package application.capstone.validatorEnums;

import application.capstone.enums.Tema;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatoValidator implements ConstraintValidator<TemaEnumValidator, Tema> {
    @Override
    public void initialize(TemaEnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Tema tema, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
