package com.darkonnen.examen.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.darkonnen.examen.models.User;


@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("confirm", "Match");
        }         
    }
}