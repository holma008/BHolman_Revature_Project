package com.app.bank.user.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceValidations {

	public static boolean isValidName(String name) {
		if(name != null && name.matches("[a-zA-Z ]{3,20}")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		if(email != null && matcher.matches()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidPassword(String password) {

		if(password != null && password.matches("^(?=.*[0-9])(?=\\S+$).{4,20}$")){
			return true;
		}else {
			return false;
		}
	}
	public static boolean isValidPhonenumber(String phonenumber) {
		if(phonenumber != null && phonenumber.matches("^([0-9]{3}[- ]?){2}[0-9]{4}$")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidAddress(String address) {
		if(address != null && address.matches("[a-zA-Z0-9]{5, 30")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidUserId(int userid) {
		if(userid < 0 && userid < 10000) {
			return true;
		}else {
			return false;
		}
	}
}
