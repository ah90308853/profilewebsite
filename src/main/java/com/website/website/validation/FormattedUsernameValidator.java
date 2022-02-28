package com.website.website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattedUsernameValidator implements ConstraintValidator<FormattedUsername, String>
{

    @Override
    public void initialize(FormattedUsername formattedUsername) {
        ConstraintValidator.super.initialize(formattedUsername);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext)
    {
        Pattern regexPattern = Pattern.compile("^\\S+$");
        Matcher matcher = regexPattern.matcher(username);
        if (!(username.length() >= 3) || !(username.length() <= 25))
        {
            return false;
        }
        else
        {
            return matcher.matches();
        }

    }
}
