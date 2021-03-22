package com.app.bank.employee.service.impl;

import java.util.List;

import com.app.bank.account.dao.AccountDAO;
import com.app.bank.account.dao.impl.AccountDAOImpl;
import com.app.bank.employee.service.EmployeeService;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Transaction;
import com.app.bank.model.UnapprovedAccount;
import com.app.bank.model.User;
import com.app.bank.transaction.dao.TransactionDAO;
import com.app.bank.transaction.dao.impl.TransactionDAOImpl;

public class EmployeeServiceImpl implements EmployeeService{

	AccountDAO accountDAO = new AccountDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl(); 
	@Override
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException {
		return accountDAO.viewUnapprovedAccounts();
	}
	
	@Override
	public List<User> viewAllAccounts() throws BusinessException {
		return accountDAO.viewAllAccounts();
	}

	@Override
	public List<Account> viewAccountByCustomerId(int cusId) throws BusinessException {
		return accountDAO.viewAccountByCustomerId(cusId);
	}

	@Override
	public List<Transaction> pullAllTransactionData() throws BusinessException {
		return transactionDAO.pullAllTransactionData();
	}
}
