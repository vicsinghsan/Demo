package com.vBank.dto;

public class BankResponse {

	private String responseCode;
	
	private String responseMessage;
	
	private AccountInfo accountInfo;

	public BankResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.accountInfo = accountInfo;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	
}
