package com.app.bank.transaction.service;

import com.app.bank.exception.BusinessException;

public interface TransactionService {

	public int WithdrawalMade(int cusId, int accountNum, double withdraw)throws BusinessException;
	public int DepositMade(int cusId, int accountNum, double deposit) throws BusinessException;
	public int TransferInitiated(int cusId, int accountNum) throws BusinessException;
	public int TransferAccepted(int cusId, int accountNum) throws BusinessException;
}
