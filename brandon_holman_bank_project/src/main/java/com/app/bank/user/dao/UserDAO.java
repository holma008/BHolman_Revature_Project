package com.app.bank.user.dao;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.User;

public interface UserDAO {

	public int registerCustomer(User user) throws BusinessException;
	public User getCustomerLogin(String email, String password) throws BusinessException;
	public int customerExists(String email) throws BusinessException;
}
