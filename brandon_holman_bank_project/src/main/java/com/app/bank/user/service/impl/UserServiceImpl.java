package com.app.bank.user.service.impl;

import com.app.bank.exception.BusinessException;
import com.app.bank.user.dao.UserDAO;
import com.app.bank.user.dao.impl.UserDAOImpl;
import com.app.bank.user.service.UserService;

import com.app.bank.model.User;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO = new UserDAOImpl();
	@Override
	public int registerCustomer(User user) throws BusinessException {
		if(!UserServiceValidations.isValidName(user.getName())) {
			throw new BusinessException("Entered name '" + user.getName() + "' is invalid");
		}
		if(!UserServiceValidations.isValidEmail(user.getEmail())) {
			throw new BusinessException("Entered email '" + user.getEmail() + "' is invalid");
		}
		if(!UserServiceValidations.isValidPassword(user.getPassword())) {
			throw new BusinessException("Entered password '" + user.getPassword() + "' is invalid");
		}
		if(!UserServiceValidations.isValidPhonenumber(user.getPhoneNumber())) {
			throw new BusinessException("Entered phone number '" + user.getPhoneNumber() + "' is invalid");
		}
		return userDAO.registerCustomer(user);
	}

	@Override
	public User logIn(String email, String password) throws BusinessException {
		User user = null;
		if(!UserServiceValidations.isValidEmail(email)){
			throw new BusinessException("Entered email '" + email + "' is invalid");
		}
		if(!UserServiceValidations.isValidPassword(password)) {
			throw new BusinessException("Entered password '" + password + "' is invalid");
		}
		user = userDAO.getCustomerLogin(email, password);
		return user;
	}
}
