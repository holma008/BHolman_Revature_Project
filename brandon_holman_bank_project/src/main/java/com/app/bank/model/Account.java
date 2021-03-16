package com.app.bank.model;

import java.util.Date;

public class Account {

	private int accountNum;
	private double balance;
	private int accountHolderId;
	private boolean approved;
	private int approvedById;
	private Date opened;

	public Account() {
		
	}
	
	public Account(int accountNum, double balance, int accountHolderId, boolean approved, int approvedById,
			Date opened) {
		super();
		this.accountNum = accountNum;
		this.balance = balance;
		this.accountHolderId = accountHolderId;
		this.approved = approved;
		this.approvedById = approvedById;
		this.opened = opened;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(int accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getApprovedById() {
		return approvedById;
	}

	public void setApprovedById(int approvedById) {
		this.approvedById = approvedById;
	}

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {
		this.opened = opened;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", balance=" + balance + ", accountHolderId=" + accountHolderId
				+ ", approved=" + approved + ", approvedById=" + approvedById + ", opened=" + opened + "]";
	}
}
