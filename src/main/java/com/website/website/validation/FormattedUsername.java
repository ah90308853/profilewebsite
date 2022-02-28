package com.website.website.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = FormattedUsernameValidator.class)
public @interface FormattedUsername
{
    String message() default "a username must be between 3 and 25 characters and not contain any spaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
