package com.website.website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattedPasswordValidator implements ConstraintValidator<FormattedPassword, String>
{
    @Override
    public void initialize(FormattedPassword formattedPassword) {
        ConstraintValidator.super.initialize(formattedPassword);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext)
    {
        Pattern regexPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,20}$");
        Matcher matcher = regexPattern.matcher(password);
        if (!(password.length() >= 8) || !(password.length() <= 30))
        {
            return false;
        }
        else
        {
            return matcher.matches();
        }
    }
}
