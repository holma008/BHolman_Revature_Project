package com.app.bank.account.service;

import com.app.bank.exception.BusinessException;

public interface AccountService {

	public int updateAccountBalance(int accNum, double balance) throws BusinessException;
	public int updateAccountStatus(int accNum, boolean approved, int approvedBy) throws BusinessException;
	public int rejectAccount(int accNum) throws BusinessException;
	public boolean accountExists(int user, int accNum) throws BusinessException;
}
