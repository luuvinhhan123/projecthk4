package com.aptech.eProject.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpRequest {
	@NotEmpty(message = "password is not empty")
	private String firstName;

	@NotEmpty(message = "password is not empty")
	private String lastName;

	@NotEmpty(message = "email is not empty")
	@Email(message = "email is a@gmail.com ....")
	private String email;

	@NotEmpty(message = "password is not empty")
	private String password;

	@NotEmpty(message = "Phone is not empty")
	private String phone;
}
