package com.app.bank.employee.service.impl;

import java.util.List;

import com.app.bank.account.dao.AccountDAO;
import com.app.bank.account.dao.impl.AccountDAOImpl;
import com.app.bank.employee.service.EmployeeService;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Transaction;
import com.app.bank.model.UnapprovedAccount;

public class EmployeeServiceImpl implements EmployeeService{

	AccountDAO accountDAO = new AccountDAOImpl();
	@Override
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException {
		return accountDAO.viewUnapprovedAccounts();
	}

	@Override
	public List<Account> viewAccountByCustomerId(int cusId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> pullAllTransactionData() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
