package com.aptech.eProject.controllers.api;

import com.aptech.eProject.models.User;
import com.aptech.eProject.requests.auth.SignUpRequest;
import com.aptech.eProject.responses.ApiResponse;
import com.aptech.eProject.responses.signUp.SignUpResponse;
import com.aptech.eProject.services.UserService;
import com.aptech.eProject.utils.ModelMapperUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/sign-up")
public class SignUpController extends ApiController {
	@Autowired
	ApiResponse apiResponse;

	@Autowired
	UserService userService;

	@PostMapping("")
	public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signupRequest, BindingResult rs) {
		try {
			if (rs.hasErrors()) {
				return apiResponse.failure("invalid", parseFieldErrors(rs), HttpStatus.UNPROCESSABLE_ENTITY.value());
			}

			User user = userService.signUpUser(signupRequest);

			ModelMapperUtil<User, SignUpResponse> mapper = new ModelMapperUtil<>();
			SignUpResponse result = mapper.parseToObj(user, new SignUpResponse());
			return apiResponse.ok("created", result);
		} catch (Exception e) {
			return apiResponse.failure("error");
		}
	}


}
