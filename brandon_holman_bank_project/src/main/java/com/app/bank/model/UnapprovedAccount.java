package com.app.bank.model;

public class UnapprovedAccount {

	private int accountNum;
	private String applicantName;
	private Double balance;
	
	public UnapprovedAccount() {
		
	}
	
	public UnapprovedAccount(int accountNum, String applicantName, Double balance) {
		super();
		this.accountNum = accountNum;
		this.applicantName = applicantName;
		this.balance = balance;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountId) {
		this.accountNum = accountId;
	}
	
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UnapprovedAccount [accountNum=" + accountNum + ", applicantName=" + applicantName + ", balance=" + balance
				+ "]";
	}
}
