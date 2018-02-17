package com.example.security.auditing.securityauditing.domain.validation;

import com.example.security.auditing.securityauditing.domain.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created on February, 2018
 *
 * @author cylon
 */
public class ValidUserConstraint implements ConstraintValidator<ValidUser, UserDto> {

	@Override
	public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {

		boolean flag = true;
		context.disableDefaultConstraintViolation();
		if (!userDto.getPassword().equals(userDto.getMatchingPassword())) {
			flag = false;
			context.buildConstraintViolationWithTemplate("Passwords are not matching").addPropertyNode("password");
		}
		return flag;
	}
}
