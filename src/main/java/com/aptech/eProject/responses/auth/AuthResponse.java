package com.aptech.eProject.responses.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
	private String token;
}
