package com.website.website.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattedEmailValidator implements ConstraintValidator<FormattedEmail, String>
{
    @Override
    public void initialize(FormattedEmail formattedEmail)
    {
        ConstraintValidator.super.initialize(formattedEmail);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext)
    {
        Pattern regexPattern = Pattern.compile("^.*[@].*.*[.].*$");
        Matcher matcher = regexPattern.matcher(email);
        if (!(email.length() >= 5) || !(email.length() <= 50))
        {
            return false;
        }
        else
        {
            return matcher.matches();
        }
    }
}
