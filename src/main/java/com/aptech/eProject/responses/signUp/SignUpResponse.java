package com.aptech.eProject.responses.signUp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SignUpResponse {
	private String firstName;
	private String lastName;
	private String email;
}
