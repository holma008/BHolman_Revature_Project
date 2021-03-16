package com.app.bank.transaction.dao;

import java.util.List;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Transaction;

public interface TransactionDAO {
	public List<Transaction> pullAllTransactionData() throws BusinessException;
	public List<Transaction> pullTransactionDataByAccount(int accountNum) throws BusinessException;
	public List<Transaction> pullTransactionDataByUser(int userNum) throws BusinessException;
}
