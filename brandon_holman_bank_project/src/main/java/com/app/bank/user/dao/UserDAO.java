package com.app.bank.user.dao;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.Employee;
import com.app.bank.model.User;

public interface UserDAO {

	public int registerUser(User user) throws BusinessException;
	public int registerEmployee(Employee employee) throws BusinessException;
	public int createAccount(Account account) throws BusinessException;
}
