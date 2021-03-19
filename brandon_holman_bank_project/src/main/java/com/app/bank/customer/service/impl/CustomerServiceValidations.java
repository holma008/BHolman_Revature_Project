package com.app.bank.customer.service.impl;


public class CustomerServiceValidations {

	public static boolean isBalanceValid(Double balance) {
		if(balance <= 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
}
