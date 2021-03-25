package com.app.bank.customer.service.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.app.bank.customer.service.CustomerService;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;

public class CustomerServiceImplTest {

	private CustomerService customerService = new CustomerServiceImpl();
	private Account account;
	
	@Before
	public void setUp(){
		account = new Account();
		account.setAccountHolderId(9);//valid customer id
		account.setBalance(-100);
	}
	
	@After
	public void tearDown(){
		account = null;
	}
	
	@Test
	public void testCreateAccountInvalidAmount(){
		try {
			assertEquals(0, customerService.createAccount(account));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckBalanceInvalidCustomerId() {
		try {
			assertEquals(-1, customerService.checkBalance(1000, 20), 1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckBalanceInvalidAccountId() {
		try {
			assertEquals(-1, customerService.checkBalance(9, 1000), 1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckBalanceValidInputs() {
		try {
			assertEquals(1520.20, customerService.checkBalance(16, 21), 1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
