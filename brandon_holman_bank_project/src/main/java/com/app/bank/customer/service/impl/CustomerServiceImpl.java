package com.app.bank.customer.service.impl;


import com.app.bank.account.dao.AccountDAO;
import com.app.bank.account.dao.impl.AccountDAOImpl;
import com.app.bank.customer.service.CustomerService;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;

public class CustomerServiceImpl implements CustomerService{

	private AccountDAO accountDAO = new AccountDAOImpl();
	
	@Override
	public int createAccount(Account account) throws BusinessException {
		if(!CustomerServiceValidations.isBalanceValid(account.getBalance())) {
			throw new BusinessException("starting balance of " + account.getBalance() + " is invalid");
		}
		return accountDAO.createAccount(account);
	}
	
	@Override
	public double checkBalance(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account withdrawFunds(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account depositFunds(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int transferFunds(int cusId, int accountSend, int accountRec) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}
}
