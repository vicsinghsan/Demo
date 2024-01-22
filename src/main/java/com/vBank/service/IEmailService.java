package com.vBank.service;

import com.vBank.dto.EmailDetails;

public interface IEmailService {
   
	void sentEmailAlert(EmailDetails emailDetails);
}
