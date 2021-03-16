package com.app.bank.transaction.dao.impl;

import java.util.List;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Transaction;
import com.app.bank.transaction.dao.TransactionDAO;

public class TransactionDAOImpl implements TransactionDAO{

	@Override
	public List<Transaction> pullAllTransactionData() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> pullTransactionDataByAccount(int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> pullTransactionDataByUser(int userNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
