package com.example.security.auditing.securityauditing.domain;

import com.example.security.auditing.securityauditing.domain.validation.ValidUser;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on February, 2018
 *
 * @author cylon
 */
@Getter
@Setter
@ValidUser
public class UserDto {

	private String username;

	private String password;

	private String email;

	private String matchingPassword;
}
