package com.vBank.dto;

import java.math.BigDecimal;

public class AccountCreditRequest {
	
	private BigDecimal amount;
	
	private String accNum;

	public AccountCreditRequest(BigDecimal amount, String accNum) {
		super();
		this.amount = amount;
		this.accNum = accNum;
	}

	public AccountCreditRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	

}
