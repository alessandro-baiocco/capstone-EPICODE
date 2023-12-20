package application.capstone.validatorEnums;

import application.capstone.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<RoleEnumValidator, Role> {
    @Override
    public void initialize(RoleEnumValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        List<Role> temi = Arrays.stream(Role.values()).toList();
        return temi.contains(role);

    }


}
