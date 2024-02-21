package com.aptech.eProject.mails;

import com.aptech.eProject.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailUserVerificationCode {
	final String subject = "Your Fashion Account - Verify Your Email Address";

	final String templateMail = "";

	@Autowired
	MailUtil mailUtil;

	/**
	 * Send mail notification
	 *
	 * @param mailTo
	 * @param code
	 */
	public void sendMail(String mailTo, String name, String code) {
//		Context context = new Context();
//		context.setVariable("message", emailRequest.getBody());
//		mailUtil.sendEmailWithHtmlTemplate(mailTo, subject,  templateMail, context);
		String message = "Dear," + name
				+"\n\n"
				+ "Thanks you. \n"
				+ "Please verify your code to complete your  account.\n"
				+ "This verification code will expire in 30 minutes. If it has expired, try to request a new verification code.\n\n"
				+"Code: " + code +"\n\n"
				+ "Thanks you";
		mailUtil.sendEmail(mailTo, subject, message);
	}
}
