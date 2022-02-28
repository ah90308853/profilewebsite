package com.website.website.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueClientEmailValidator.class)
public @interface UniqueClientEmail
{
    String message() default "the specified email already exists in the database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}