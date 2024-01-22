 package com.vBank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vBank.dto.AccountInfo;
import com.vBank.dto.BankResponse;
import com.vBank.dto.TransferRequest;
import com.vBank.entity.User;
import com.vBank.repository.IUserRepository;
@Service
public class InternalTransfer implements IInternalTransfer {
	
	@Autowired
	private IUserRepository userRepository ;

	@Override
	public BankResponse transfer(TransferRequest transferRequest) {
		BankResponse bankResponse = new BankResponse();
		
		if(transferRequest!=null) {
			if(validateFromAccount(transferRequest.getFromAccount())) {
				bankResponse = initiateTransfer(transferRequest);
			}else {
				bankResponse.setResponseCode("900");
				bankResponse.setResponseMessage("Account not found");
			}
		}
		
		return bankResponse;
	}

	private BankResponse initiateTransfer(TransferRequest transferRequest) {
		BankResponse bankResponse = new BankResponse();
		AccountInfo accountInfo = new AccountInfo();
		User fromAcctDetails = userRepository.findByAccountNumber(transferRequest.getFromAccount());
		BigDecimal accountBalance = fromAcctDetails.getAccountBalance();
		if(accountBalance.compareTo(transferRequest.getAmount())==-1) {
			bankResponse.setResponseCode("000");
			bankResponse.setResponseMessage("You do not have sufficient balance in your account");
			accountInfo.setAccountBalance(accountBalance);
			accountInfo.setAccountName(fromAcctDetails.getFirstName()+" "+ fromAcctDetails.getLastName());
			bankResponse.setAccountInfo(accountInfo);
			return bankResponse;
		}
		User toAccountDetails  = userRepository.findByAccountNumber(transferRequest.getToAccount());
		if(toAccountDetails==null) {
			bankResponse.setResponseCode("901");
			bankResponse.setResponseMessage("Not a valid Account");
			return bankResponse;
		}else {
			BigDecimal toactBal = toAccountDetails.getAccountBalance();
			//deducting from fromAcct
		    fromAcctDetails.setAccountBalance(fromAcctDetails.getAccountBalance().subtract(transferRequest.getAmount()));
			
		    //Adding in toAccnt
		    toAccountDetails.setAccountBalance(toactBal.add(transferRequest.getAmount()));
		    userRepository.save(toAccountDetails);
		    userRepository.save(fromAcctDetails);

		    accountInfo.setAccountBalance(fromAcctDetails.getAccountBalance());
		    accountInfo.setAccountNumber(fromAcctDetails.getAccountNumber());
		    accountInfo.setAccountName(fromAcctDetails.getFirstName()+" "+fromAcctDetails.getLastName());
		    bankResponse.setResponseCode("800");
		    bankResponse.setResponseMessage("Transfer of amount :"+ "INR " +transferRequest.getAmount()+" is succesfull");
		    bankResponse.setAccountInfo(accountInfo);
		    return bankResponse;
		}
		
	}

	private boolean validateFromAccount(String accNum) {
		User userAccnum = userRepository.findByAccountNumber(accNum);
		if(userAccnum!=null) {
			return true;
			
		}
		return false;
		
		
	}

}
