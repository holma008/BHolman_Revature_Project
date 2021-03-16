package com.app.bank.account.service;

import com.app.bank.exception.BusinessException;

public interface AccountService {

	public int checkBalance(int userId, int accountNum) throws BusinessException;
	public int approveAccount(int userId, int empId) throws BusinessException;
	public int withdrawFunds(int userId, int accountNum) throws BusinessException;
	public int depositFunds(int userId, int accountNum) throws BusinessException;
	public int transferFunds(int userId, int accountSend, int accountRec) throws BusinessException;
}
