package com.app.bank.user.service;

import com.app.bank.exception.BusinessException;

import com.app.bank.model.User;

public interface UserService {

	public int registerCustomer(User user) throws BusinessException;
	public User logIn(String email, String password) throws BusinessException;
}
