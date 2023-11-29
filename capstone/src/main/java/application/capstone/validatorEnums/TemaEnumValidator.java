package application.capstone.validatorEnums;

import application.capstone.entities.BlogArticle;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatoValidator.class)
public @interface TemaEnumValidator {
    String message() default "Valore non valido per lo stato";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
