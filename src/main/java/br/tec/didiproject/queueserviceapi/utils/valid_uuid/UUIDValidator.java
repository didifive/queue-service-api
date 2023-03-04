package br.tec.didiproject.queueserviceapi.utils.valid_uuid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {

    @Override
    public void initialize(ValidUUID validUuid){}

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext cxt) {
        String regex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";
        return uuid.toString().matches(regex);
    }
}