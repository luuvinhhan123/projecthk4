package com.aptech.eProject.controllers.api;

import com.aptech.eProject.requests.auth.LoginRequest;
import com.aptech.eProject.responses.ApiResponse;
import com.aptech.eProject.responses.auth.AuthResponse;
import com.aptech.eProject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sign-in")
public class SignInController extends ApiController {
	@Autowired
	ApiResponse apiResponse;


	@Autowired
	UserService userService;

	@PostMapping("")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginReq, BindingResult rs) throws Exception {
		try {
			if (rs.hasErrors()) {
				return apiResponse.failure("invalid", parseFieldErrors(rs), HttpStatus.UNPROCESSABLE_ENTITY.value());
			}

			AuthResponse resq = userService.login(loginReq);
			if (resq != null) {
				return apiResponse.ok("login sucees", resq);
			}
			return apiResponse.failure("Invalid email or password");
		} catch (Exception e) {
			return apiResponse.failure(e.getMessage());
		}
	}
}
