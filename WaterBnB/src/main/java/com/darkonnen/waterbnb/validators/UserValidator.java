package com.darkonnen.waterbnb.validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.darkonnen.waterbnb.models.User;
import com.darkonnen.waterbnb.services.UserService;

@Component
public class UserValidator implements Validator {
	private UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		if(user.getRole() == null) {
			errors.rejectValue("role", "Blank");
		}
		
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.matches()) {
            errors.rejectValue("email", "Match");
        }
        	
        User userCheck = userService.findByEmail(user.getEmail());
        
        if (userCheck != null) {
        		errors.rejectValue("email", "Found");
        }
        
        String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern passPattern = Pattern.compile(passRegex);
        Matcher passMatcher = passPattern.matcher(user.getPassword());
        	if (!passMatcher.matches()) {
        		errors.rejectValue("password", "Match");
        	}
	}
}