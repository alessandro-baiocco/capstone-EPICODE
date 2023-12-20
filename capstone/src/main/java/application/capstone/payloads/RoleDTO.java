package application.capstone.payloads;

import application.capstone.enums.Role;
import application.capstone.validatorEnums.RoleEnumValidator;
import jakarta.validation.constraints.NotNull;

public record RoleDTO (
        @NotNull
        @RoleEnumValidator
        Role role
) {

}
