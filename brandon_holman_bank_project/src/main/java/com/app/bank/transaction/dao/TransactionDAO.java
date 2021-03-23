package com.app.bank.transaction.dao;

import java.util.List;

import com.app.bank.exception.BusinessException;
import com.app.bank.model.Transaction;

public interface TransactionDAO {

	public List<Transaction> pullAllTransactionData() throws BusinessException;
	public int WithdrawalMade(int cusId, int accountNum, double withdraw)throws BusinessException;
	public int DepositMade(int cusId, int accountNum, double deposit) throws BusinessException;
	public int Transfer(int cusId, int accountNum, int reciever, int rNum, double transfer) throws BusinessException;
}