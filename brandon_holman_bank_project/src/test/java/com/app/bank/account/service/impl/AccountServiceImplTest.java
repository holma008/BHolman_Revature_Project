package com.app.bank.account.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.bank.account.service.AccountService;
import com.app.bank.exception.BusinessException;

public class AccountServiceImplTest {

	AccountService accountService = new AccountServiceImpl();
	
	@Test
	public void testAccountExistsInvalidUserId(){
		try {
			assertEquals(false, accountService.accountExists(100, 22));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAccountExistsInvalidAccount(){
		try {
			assertEquals(false, accountService.accountExists(9, 1524));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAccountExistsValid() {
		try {
			assertEquals(true, accountService.accountExists(9, 18));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
