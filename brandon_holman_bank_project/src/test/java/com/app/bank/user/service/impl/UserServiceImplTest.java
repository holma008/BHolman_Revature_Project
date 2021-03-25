package com.app.bank.user.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.bank.exception.BusinessException;
import com.app.bank.user.service.UserService;

public class UserServiceImplTest {

	private UserService userService = new UserServiceImpl();
	
	@Test
	public void testLogInInvalidEmail() {
		try {
			assertEquals(false, userService.logIn("brandon", "password1"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testLogInInvalidPassword() {
		try {
			assertEquals(false, userService.logIn("brandon@bank.com", "password"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testLoginValid() {
		try {
			//id of user 'brandon' in db
			assertEquals(9, 
					userService.logIn("brandon@bank.com", "password1").getUserId());
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCustomerExistsInvalidEmail(){
		try {
			assertEquals(false, userService.customerExists("brandon"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomerExistsValidEmailNotInDB() {
		try {
			assertEquals(0, userService.customerExists("bran@bank.com"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCustomerExistsValidEmailInDB() {
		try {
			assertEquals(9, userService.customerExists("brandon@bank.com"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
