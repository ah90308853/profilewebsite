package com.website.website.validation;

import com.website.website.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>
{
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public void initialize(UniqueEmail uniqueEmail)
    {
        ConstraintValidator.super.initialize(uniqueEmail);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext)
    {
        return !(userDetailsService.existsByEmail(email));
    }
}
