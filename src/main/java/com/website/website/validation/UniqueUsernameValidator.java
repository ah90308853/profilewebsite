package com.website.website.validation;

import com.website.website.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void initialize(UniqueUsername uniqueUsername)
    {
        ConstraintValidator.super.initialize(uniqueUsername);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext)
    {
        return !(userDetailsService.existsByUsername(username));
    }
}
