package com.app.bank.account.service.impl;

import com.app.bank.account.service.AccountService;
import com.app.bank.exception.BusinessException;

public class AccountServiceImpl implements AccountService{

	@Override
	public int checkBalance(int userId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int approveAccount(int userId, int empId) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int withdrawFunds(int userId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int depositFunds(int userId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int transferFunds(int userId, int accountSend, int accountRec) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
