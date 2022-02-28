package com.website.website.validation;

import com.website.website.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueClientEmailValidator implements ConstraintValidator<UniqueClientEmail, String>
{
    @Autowired
    ClientService clientService;

    @Override
    public void initialize(UniqueClientEmail uniqueEmail) {
        ConstraintValidator.super.initialize(uniqueEmail);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext uniqueEmail)
    {
        return !(clientService.existsByEmail(email));
    }
}
