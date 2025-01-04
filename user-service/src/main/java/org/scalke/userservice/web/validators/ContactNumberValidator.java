package org.scalke.userservice.web.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.scalke.userservice.web.constraints.ContactNumberConstraint;

@AllArgsConstructor
public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {


    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[0-9]+")
                && (value.length() > 8) && (value.length() < 14);
    }
}
