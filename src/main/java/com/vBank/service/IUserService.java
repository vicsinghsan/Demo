package com.vBank.service;

import com.vBank.dto.AccountCreditRequest;
import com.vBank.dto.AccountDebitRequest;
import com.vBank.dto.BankResponse;
import com.vBank.dto.UserRequest;

public interface IUserService {
	
	BankResponse createAccount(UserRequest userRequest);
	
	BankResponse creditAccount(AccountCreditRequest accountCreditRequest);
	
	BankResponse debitAccount(AccountDebitRequest accountDebitRequest);
	
	BankResponse accountInquiry(AccountCreditRequest accountCreditRequest);
	

}
