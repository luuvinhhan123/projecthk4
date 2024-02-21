package com.aptech.eProject.controllers.api;

import com.aptech.eProject.models.User;
import com.aptech.eProject.requests.auth.VerifyRequest;
import com.aptech.eProject.responses.ApiResponse;
import com.aptech.eProject.services.UserService;
import com.aptech.eProject.services.VerifyService;
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
@RequestMapping("api/v1/verify")
public class VerifyController extends ApiController {
	@Autowired
	ApiResponse apiResponse;

	@Autowired
	VerifyService verifyService;

	@Autowired
	UserService userService;

	@PostMapping("")
	public ResponseEntity<?> signUp(@RequestBody @Valid VerifyRequest verifyRequest, BindingResult rs) {
		try {
			if (rs.hasErrors()) {
				return apiResponse.failure("invalid", parseFieldErrors(rs), HttpStatus.UNPROCESSABLE_ENTITY.value());
			}

			if (verifyService.isVerify(verifyRequest)) {
				User user = userService.verifyUser(verifyRequest.getEmail());

				if (user != null) {
					return apiResponse.ok("success");
				}
			}

			return apiResponse.failure("error");
		} catch (Exception e) {
			return apiResponse.failure("error");
		}
	}

}
