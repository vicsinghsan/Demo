package com.vBank.utils;

import java.time.Year;

import com.vBank.dto.UserRequest;
import com.vBank.entity.User;

public class UserUtils {
	
	
	
	
	public static User converUserReqToUser(UserRequest userRequest) {
		User user = new User();
		if(userRequest!=null) {
			if(userRequest.getFirstName()!=null) {
				user.setFirstName(userRequest.getFirstName());
			}
			if(userRequest.getLastName()!=null) {
				user.setLastName(userRequest.getLastName());
			}
			
			if(userRequest.getAddress()!=null) {
				user.setAddress(userRequest.getAddress());
			}
			if(userRequest.getPhoneNumber()!=null) {
				user.setPhoneNumber(userRequest.getPhoneNumber());
			}
			if(userRequest.getAlternatePhoneNumber()!=null) {
				user.setAlternatePhoneNumber(userRequest.getAlternatePhoneNumber());
			}
			if(userRequest.getEmail()!=null) {
				user.setEmail(userRequest.getEmail());
			}
			if(userRequest.getStateOfOrigin()!=null) {
				user.setStateOfOrigin(userRequest.getStateOfOrigin());
			}
			if(userRequest.getGender()!=null) {
				user.setGender(userRequest.getGender());
			}
			
			return user;
		}
		
		
		
		
		
		return null;
		
	}
	
	public static String generateAccountNum() {
		Year currentYear = Year.now();
		
		int min = 10000;
		int max = 99999;
		
		int randomNumber = (int) Math.floor(Math.random() *(max-min+1)+min);
	    
		String year= String.valueOf(currentYear);
		String randNum = String.valueOf(randomNumber);
		
		StringBuilder accNum = new StringBuilder();
		accNum.append(year);
		accNum.append(randNum);
		return accNum.toString();
		
	}
	

}
