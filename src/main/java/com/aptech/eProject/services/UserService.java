package com.aptech.eProject.services;

import com.aptech.eProject.mails.MailUserVerificationCode;
import com.aptech.eProject.models.Profile;
import com.aptech.eProject.models.User;
import com.aptech.eProject.models.Verify;
import com.aptech.eProject.repositories.UserRepository;
import com.aptech.eProject.repositories.VerifyRepository;
import com.aptech.eProject.requests.auth.LoginRequest;
import com.aptech.eProject.requests.auth.SignUpRequest;
import com.aptech.eProject.responses.auth.AuthResponse;
import com.aptech.eProject.utils.JwtUtil;
import com.aptech.eProject.utils.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private VerifyRepository verifyRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	MailUserVerificationCode mailUserVerificationCode;

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findUserById(Integer userId) {
		User user = userRepository.findById(userId).get();
		if (user.getProfile() == null || user.getProfile().getId() == 0) {
			user.setProfile(new Profile());
		}
		return user;
	}

	public void createUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

/*		ModelMapperUtil<User, SignUpRequest> mapper = new ModelMapperUtil<>();
		User user = mapper.mapToModel(new SignUpRequest(), new User());*/

		/*	mailUserVerificationCode.sendMail(user.getEmail(), "code");*/

		userRepository.save(user);
	}

	public void updateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public AuthResponse login(LoginRequest loginReq) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String email = authentication.getName();
			User user = userRepository.findByEmail(email);

			return AuthResponse.builder().token(jwtUtil.generateToken(user)).build();

		} catch (Exception e) {
			throw new Exception("email or password incorrect");
		}
	}

	public void delete(Integer userId) {
		userRepository.deleteById(userId);
	}

	public User signUpUser(SignUpRequest signupRequest) {
		User user = new User();
		try {
			signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
			Verify verifiedToken = new Verify(signupRequest.getEmail());
			verifyRepository.save(verifiedToken);
			String code = verifiedToken.getCode();
			ModelMapperUtil<User, SignUpRequest> mapper = new ModelMapperUtil<>();
			user = mapper.mapToModel(signupRequest, new User());
			user.setVerified(false);
			mailUserVerificationCode.sendMail(user.getEmail(), signupRequest.getFirstName() + signupRequest.getLastName(), code);

			return userRepository.save(user);

		} catch (Exception exception) {

		}

		return user;
	}

	/**
	 * Verify user
	 *
	 * @param email
	 * @return
	 */
	public User verifyUser(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null && !user.isVerified()) {
			user.setVerified(true);
			userRepository.save(user);

			return user;
		}
		return null;
	}
}
