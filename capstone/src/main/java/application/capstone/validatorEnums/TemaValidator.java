package application.capstone.validatorEnums;

import application.capstone.enums.Tema;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.codec.language.bm.Rule;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Arrays;
import java.util.List;

public class TemaValidator implements ConstraintValidator<TemaEnumValidator, Tema> {
    
    @Override
    public boolean isValid(Tema tema, ConstraintValidatorContext constraintValidatorContext) {
        List<Tema> temi = Arrays.stream(Tema.values()).toList();
        return temi.contains(tema);
    }
}
