package com.app.bank.transaction.service.impl;

import com.app.bank.exception.BusinessException;
import com.app.bank.transaction.dao.TransactionDAO;
import com.app.bank.transaction.dao.impl.TransactionDAOImpl;
import com.app.bank.transaction.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{

	TransactionDAO transactionDAO = new TransactionDAOImpl();
	@Override
	public int WithdrawalMade(int cusId, int accountNum, double withdraw) throws BusinessException {
		return transactionDAO.WithdrawalMade(cusId, accountNum, withdraw);
	}

	@Override
	public int DepositMade(int cusId, int accountNum, double deposit) throws BusinessException {
		return transactionDAO.DepositMade(cusId, accountNum, deposit);
	}

	@Override
	public int Transfer(int userId, int accNum, int reciever, int rNum, double transferAmt) throws BusinessException{
		return transactionDAO.Transfer(userId, accNum, reciever, rNum, transferAmt);
	}
}
