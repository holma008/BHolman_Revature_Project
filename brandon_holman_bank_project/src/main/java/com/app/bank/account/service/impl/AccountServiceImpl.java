package com.app.bank.account.service.impl;

import com.app.bank.account.dao.AccountDAO;
import com.app.bank.account.dao.impl.AccountDAOImpl;
import com.app.bank.account.service.AccountService;
import com.app.bank.exception.BusinessException;

public class AccountServiceImpl implements AccountService {

	AccountDAO accountDAO = new AccountDAOImpl();

	@Override
	public int updateAccountBalance(int accNum, double balance) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccountStatus(int accNum, boolean approved, int approvedBy) throws BusinessException {
		return accountDAO.updateAccountStatus(accNum, approved, approvedBy);
	}

	@Override
	public int rejectAccount(int accNum) throws BusinessException {
		// delete row from table
		return accountDAO.rejectAccount(accNum);
	}
}
