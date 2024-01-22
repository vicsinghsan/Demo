package com.vBank.dto;

public class UserRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String otherName;
	
	private Character gender;
	
	private String  address;
	
	private String stateOfOrigin;
	
	private String email;
	
	private String phoneNumber;
	
	private String alternatePhoneNumber;
	
	private String status;

	public UserRequest(String firstName, String lastName, String otherName, Character gender, String address,
			String stateOfOrigin, String email, String phoneNumber, String alternatePhoneNumber, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherName = otherName;
		this.gender = gender;
		this.address = address;
		this.stateOfOrigin = stateOfOrigin;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.status = status;
	}

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStateOfOrigin() {
		return stateOfOrigin;
	}

	public void setStateOfOrigin(String stateOfOrigin) {
		this.stateOfOrigin = stateOfOrigin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
