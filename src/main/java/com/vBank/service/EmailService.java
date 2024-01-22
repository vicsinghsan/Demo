package com.vBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vBank.dto.EmailDetails;

@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Override
	public void sentEmailAlert(EmailDetails emailDetails) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(senderEmail);
			simpleMailMessage.setTo(emailDetails.getRecipient());
			simpleMailMessage.setSubject(emailDetails.getSubject());
			simpleMailMessage.setText(emailDetails.getMessageBody());

			javaMailSender.send(simpleMailMessage);
		} catch (MailException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

}
