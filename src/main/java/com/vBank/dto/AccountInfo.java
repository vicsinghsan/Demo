package com.vBank.dto;

import java.math.BigDecimal;

public class AccountInfo {
	private String accountName;
	
	private BigDecimal accountBalance;
	
	private String accountNumber;

	public AccountInfo(String accountName, BigDecimal accountBalance, String accountNumber) {
		super();
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
	}

	public AccountInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	

}
