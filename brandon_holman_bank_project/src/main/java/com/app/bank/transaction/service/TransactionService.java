package com.app.bank.transaction.service;

import com.app.bank.exception.BusinessException;

public interface TransactionService {

	public int WithdrawalMade(int cusId, int accountNum, double withdraw)throws BusinessException;
	public int DepositMade(int cusId, int accountNum, double deposit) throws BusinessException;
	public int Transfer(int userId, int accNum, int reciever, int rNum, double transferAmt) throws BusinessException;
}
