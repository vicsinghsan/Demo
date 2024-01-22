package com.vBank.service;

import com.vBank.dto.BankResponse;
import com.vBank.dto.TransferRequest;

public interface IInternalTransfer {
	
	public BankResponse transfer(TransferRequest transferRequest);

}
