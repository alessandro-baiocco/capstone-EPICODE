package application.capstone.validatorEnums;

import application.capstone.enums.Tema;
import application.capstone.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class StatoValidator implements ConstraintValidator<TemaEnumValidator, Tema> {
    @Override
    public void initialize(TemaEnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Tema tema, ConstraintValidatorContext constraintValidatorContext) {
        List<Tema> temi = Arrays.stream(Tema.values()).toList();
        return temi.contains(tema);

    }
}
