package com.vBank.dto;

import java.math.BigDecimal;

public class TransferRequest {
	private BigDecimal amount;
	
	private String fromAccount;
	
	private String toAccount;

	public TransferRequest(BigDecimal amount, String fromAccount, String toacnt) {
		super();
		this.amount = amount;
		this.fromAccount = fromAccount;
		this.toAccount = toacnt;
	}

	public TransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	

}
