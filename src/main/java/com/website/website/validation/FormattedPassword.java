package com.website.website.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = FormattedPasswordValidator.class)
public @interface FormattedPassword
{
    String message() default "password must be between 8 and 20 characters, contain a lowercase and uppercase letter," +
            " contain any of '!@#$%^&-+=()' special characters, and not contain any spaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
