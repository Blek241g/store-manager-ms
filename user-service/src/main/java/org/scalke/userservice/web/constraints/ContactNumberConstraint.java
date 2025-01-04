package org.scalke.userservice.web.constraints;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.scalke.userservice.web.validators.ContactNumberValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
