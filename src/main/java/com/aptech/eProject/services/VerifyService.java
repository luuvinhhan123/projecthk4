package com.aptech.eProject.services;

import com.aptech.eProject.models.Verify;
import com.aptech.eProject.repositories.VerifyRepository;
import com.aptech.eProject.requests.auth.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class VerifyService {
	@Autowired
	VerifyRepository verifyRepository;

	public boolean isVerify(VerifyRequest verifyRequest) {
		Verify verifyCode = verifyRepository.findByCode(verifyRequest.getCode());

		if (verifyCode != null) {
			Date currentTime = Calendar.getInstance().getTime();
			if (verifyRequest.getEmail().equals(verifyCode.getEmail()) && currentTime.before(verifyCode.getExpiredAt())) {
				return true;
			}
		}
		return false;
	}
}
