package com.example.security.auditing.securityauditing.domain.validation.validator;

import com.example.security.auditing.securityauditing.domain.Cat;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created on February, 2018
 *
 * @author cylon
 */
public class CatValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cat.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
	}
}
