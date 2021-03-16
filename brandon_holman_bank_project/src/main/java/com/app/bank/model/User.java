package com.app.bank.model;

import java.util.Date;

public class User {

	private int userId;
	private String name;
	private String email;
	private char userType;
	private String password;
	private int phoneNumber;
	private String country;
	private String address;
	private Date dateOfBirth;

	public User() {
		
	}
	
	public User(int userId, String name, String email, char userType, String password, int phoneNumber, String country,
			String address, Date dateOfBirth) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", userType=" + userType
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", country=" + country + ", address="
				+ address + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
