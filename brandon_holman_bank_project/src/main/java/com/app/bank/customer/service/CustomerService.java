package com.app.bank.customer.service;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;

public interface CustomerService {
	
	public int createAccount(Account account) throws BusinessException;
	public double checkBalance(int cusId, int accountNum) throws BusinessException;
	public Account withdrawFunds(int cusId, int accountNum) throws BusinessException;
	public Account depositFunds(int cusId, int accountNum) throws BusinessException;
	
	//withdrawal with a deposit
	public int transferFunds(int cusId, int accountSend, int accountRec) throws BusinessException;
}
