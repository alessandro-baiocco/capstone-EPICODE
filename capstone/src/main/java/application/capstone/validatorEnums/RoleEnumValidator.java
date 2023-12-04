package application.capstone.validatorEnums;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RoleValidator.class)
public @interface RoleEnumValidator {
    String message() default "Valore non valido per il ruolo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
