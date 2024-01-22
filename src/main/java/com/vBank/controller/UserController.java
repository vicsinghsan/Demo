package com.vBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vBank.dto.AccountCreditRequest;
import com.vBank.dto.AccountDebitRequest;
import com.vBank.dto.BankResponse;
import com.vBank.dto.TransferRequest;
import com.vBank.dto.UserRequest;
import com.vBank.service.InternalTransfer;
import com.vBank.service.UserService;

@RestController
@RequestMapping("/vBank")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	InternalTransfer internalTransfer;
	
	
	@PostMapping("/register")
	public BankResponse createAccount(@RequestBody UserRequest userRequest) {
		return userService.createAccount(userRequest);
		
	}
	
	@PostMapping("/credit")
	public BankResponse creditAccount(@RequestBody AccountCreditRequest accountCreditRequest) {
		return userService.creditAccount(accountCreditRequest);
	}
	
	@GetMapping("/inquiry")
	public BankResponse accountInquiry(@RequestBody AccountCreditRequest accountCreditRequest) {
		return userService.accountInquiry(accountCreditRequest);
	}
	
	@GetMapping("/debit")
	public BankResponse debitAccount(@RequestBody AccountDebitRequest accountDebitRequest) {
		return userService.debitAccount(accountDebitRequest);
	}
	
	@GetMapping("/transfer")
	public BankResponse internalTransfer(@RequestBody TransferRequest transferRequest) {
		return internalTransfer.transfer(transferRequest);
	}
}
