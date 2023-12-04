package application.capstone.validatorEnums;

import application.capstone.enums.Role;
import application.capstone.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class RoleValidator implements ConstraintValidator<RoleEnumValidator, Role> {

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
            List<Role> ruoli = Arrays.stream(Role.values()).toList();
            return ruoli.contains(role);

    }
}
