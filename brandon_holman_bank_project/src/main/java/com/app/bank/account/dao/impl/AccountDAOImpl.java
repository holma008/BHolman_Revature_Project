package com.app.bank.account.dao.impl;

import com.app.bank.account.dao.AccountDAO;
import com.app.bank.exception.BusinessException;

public class AccountDAOImpl implements AccountDAO{

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
