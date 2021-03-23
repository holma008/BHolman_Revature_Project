package com.app.bank.account.dao;

import java.util.List;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.UnapprovedAccount;
import com.app.bank.model.User;

public interface AccountDAO {
	//account.service
	public int updateAccountBalance(int accNum, double balance) throws BusinessException;
	public int updateAccountStatus(int accNum, boolean approved, int approvedBy) throws BusinessException;
	public int rejectAccount(int accNum) throws BusinessException;
	public boolean accountExists(int user, int accNum) throws BusinessException;

	//customer.service
	public int createAccount(Account account) throws BusinessException;
	public double checkBalance(int cusId, int accountNum) throws BusinessException;
	
	//employee.service
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException;
	public List<User> viewAllAccounts() throws BusinessException;
	public List<Account> viewAccountByCustomerId (int cusId) throws BusinessException;
}
