package com.website.website.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = FormattedEmailValidator.class)
public @interface FormattedEmail
{
    String message() default "a correctly formatted email is required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
