package com.vBank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vBank.dto.AccountCreditRequest;
import com.vBank.dto.AccountDebitRequest;
import com.vBank.dto.AccountInfo;
import com.vBank.dto.BankResponse;
import com.vBank.dto.EmailDetails;
import com.vBank.dto.UserRequest;
import com.vBank.entity.User;
import com.vBank.enums.UserStatus;
import com.vBank.repository.IUserRepository;
import com.vBank.utils.UserUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Override
	public BankResponse createAccount(UserRequest userRequest) {

		BankResponse bankResponse = new BankResponse();
		User fetchedUser = userRepository.findByEmail(userRequest.getEmail());
		if (fetchedUser != null) {
			AccountInfo accountInfo = new AccountInfo();
			bankResponse.setResponseCode("300");
			bankResponse.setResponseMessage("An user with this email is already registered with us ");
			accountInfo.setAccountNumber(fetchedUser.getAccountNumber());
			accountInfo.setAccountBalance(fetchedUser.getAccountBalance());
			accountInfo.setAccountName(fetchedUser.getFirstName()+" "+fetchedUser.getLastName());
			bankResponse.setAccountInfo(accountInfo);
			return bankResponse;
		}
		User user = UserUtils.converUserReqToUser(userRequest);
		user.setAccountNumber(UserUtils.generateAccountNum());
		user.setAccountBalance(BigDecimal.ZERO);
		user.setStatus(UserStatus.ACTIVE.toString());

		if (user != null) {
			User savedUser = userRepository.save(user);
			EmailDetails emailDetails = new EmailDetails();
			emailDetails.setRecipient(savedUser.getEmail());
			emailDetails.setSubject("Account Creation At vBank");
			emailDetails.setMessageBody("Congratulation your account has been created " + "Your account number is: "
					+ savedUser.getAccountNumber());
			emailService.sentEmailAlert(emailDetails);
			AccountInfo accountInfo = new AccountInfo();
			bankResponse.setResponseCode("200");
			bankResponse.setResponseMessage("Account created Successfully");
			accountInfo.setAccountBalance(savedUser.getAccountBalance());
			accountInfo.setAccountName(savedUser.getFirstName() + savedUser.getLastName());
			accountInfo.setAccountNumber(savedUser.getAccountNumber());
			bankResponse.setAccountInfo(accountInfo);
		}
		return bankResponse;

	}

	@Override
	public BankResponse creditAccount(AccountCreditRequest accountCreditRequest) {
		BankResponse bankResponse = new BankResponse();
		AccountInfo accountInfo = new AccountInfo();

		boolean isAccountValid = userRepository.existsByAccountNumber(accountCreditRequest.getAccNum());
		if (isAccountValid) {
			User user = userRepository.findByAccountNumber(accountCreditRequest.getAccNum());
			if (user != null) {
				user.setAccountBalance(user.getAccountBalance().add(accountCreditRequest.getAmount()));
				;
				userRepository.save(user);
				bankResponse.setResponseCode("001");
				bankResponse.setResponseMessage("Balance credited Successfully");
				accountInfo.setAccountBalance(user.getAccountBalance());
				accountInfo.setAccountName(user.getFirstName() + " " + user.getLastName());
				accountInfo.setAccountNumber(user.getAccountNumber());
				bankResponse.setAccountInfo(accountInfo);
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setRecipient(user.getEmail());
				emailDetails.setSubject("Account Credited");
				emailDetails.setMessageBody("An amount of INR" + " " + user.getAccountBalance()
						+ " is credited in your account number ending with " + user.getAccountNumber().substring(5, 9));
				emailService.sentEmailAlert(emailDetails);
			}
		} else {
			bankResponse.setResponseCode("404");
			bankResponse.setResponseMessage("Account no found");
		}

		return bankResponse;
	}

	@Override
	public BankResponse accountInquiry(AccountCreditRequest userRequest) {
		User user = userRepository.findByAccountNumber(userRequest.getAccNum());
		BankResponse bankResponse = new BankResponse();
		if (user != null) {

			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setAccountBalance(user.getAccountBalance());
			accountInfo.setAccountName(user.getFirstName() + " " + user.getLastName());
			accountInfo.setAccountNumber(user.getAccountNumber());
			bankResponse.setAccountInfo(accountInfo);
			bankResponse.setResponseCode("007");
			bankResponse.setResponseMessage("Here is your account info");

			EmailDetails emailDetails = new EmailDetails();
			emailDetails.setRecipient(user.getEmail());
			emailDetails.setSubject("Account Details");
			emailDetails.setMessageBody("Account Number : " + accountInfo.getAccountNumber()+"\n" + "Account Holder Name : "
					+ accountInfo.getAccountName() + "\n"+"Available Balalnce : " + accountInfo.getAccountBalance());
			emailService.sentEmailAlert(emailDetails);

			return bankResponse;
		}
		bankResponse.setResponseCode("009");
		bankResponse.setResponseMessage("Account doesnot exist");
		return bankResponse;
	}

	@Override
	public BankResponse debitAccount(AccountDebitRequest accountdebitreq) {
		User user = userRepository.findByAccountNumber(accountdebitreq.getAccNum());
		BankResponse bankResponse = new BankResponse();
		AccountInfo accountInfo = new AccountInfo();
		if(user!=null) {
			if (user.getAccountBalance() != null) {
				BigDecimal accountBalance = user.getAccountBalance();
			         if(accountBalance.compareTo(accountdebitreq.getAmount())>=1) {
			        	 user.setAccountBalance(user.getAccountBalance().subtract(accountdebitreq.getAmount()));
			        	 userRepository.save(user);
			        	 
			        	 accountInfo.setAccountBalance(accountBalance);
			        	 accountInfo.setAccountName(user.getFirstName()+" " + user.getLastName());
			        	 accountInfo.setAccountNumber(user.getAccountNumber());
			        	 bankResponse.setAccountInfo(accountInfo);
			        	 bankResponse.setResponseCode("700");
			        	 bankResponse.setResponseMessage("INR "+accountdebitreq.getAmount()+"is debited from your account");
			        	 return bankResponse;
			         }else {
			        	 accountInfo.setAccountBalance(accountBalance);
			        	 accountInfo.setAccountName(user.getFirstName()+" " + user.getLastName());
			        	 accountInfo.setAccountNumber(user.getAccountNumber());
			        	 bankResponse.setAccountInfo(accountInfo);
			        	 bankResponse.setResponseCode("900");
			        	 bankResponse.setResponseMessage("you do not have sufficient Account blance ");
			        	 return bankResponse;
			         }
			}
		}
		bankResponse.setResponseCode("104");
		bankResponse.setResponseMessage("Account not found");
		return bankResponse;
	}


}
