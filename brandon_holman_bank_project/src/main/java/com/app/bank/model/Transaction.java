package com.app.bank.model;

import java.sql.Timestamp;

public class Transaction {

	private int transactionId;
	private int userId;
	private int accountNum;
	private String transactionData;
	private String transactionType;
	private Timestamp timeOfTransaction;
	
	public Transaction() {
		
	}

	public Transaction(int transactionId, int userId, int accountNum, String transactionData, String transactionType,
			Timestamp timeOfTransaction) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.accountNum = accountNum;
		this.transactionData = transactionData;
		this.transactionType = transactionType;
		this.timeOfTransaction = timeOfTransaction;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public String getTransactionData() {
		return transactionData;
	}

	public void setTransactionData(String transactionData) {
		this.transactionData = transactionData;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Timestamp getTimeOfTransaction() {
		return timeOfTransaction;
	}

	public void setTimeOfTransaction(Timestamp timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", accountNum=" + accountNum
				+ ", transactionData=" + transactionData + ", transactionType=" + transactionType
				+ ", timeOfTransaction=" + timeOfTransaction + "]";
	}
}
