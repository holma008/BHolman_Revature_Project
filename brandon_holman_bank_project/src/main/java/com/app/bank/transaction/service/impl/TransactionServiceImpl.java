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
	public int TransferInitiated(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int TransferAccepted(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
