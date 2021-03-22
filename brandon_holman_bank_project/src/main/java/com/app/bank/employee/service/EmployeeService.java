package com.app.bank.employee.service;

import java.util.List;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Transaction;
import com.app.bank.model.UnapprovedAccount;
import com.app.bank.model.User;

public interface EmployeeService {
	
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException;
	public List<User> viewAllAccounts() throws BusinessException;
	public List<Account> viewAccountByCustomerId (int cusId) throws BusinessException;
	public List<Transaction> pullAllTransactionData() throws BusinessException;
}
