package com.app.bank.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.bank.account.dao.AccountDAO;
import com.app.bank.dao.dbutil.PostgresConnection;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Account;
import com.app.bank.model.UnapprovedAccount;

public class AccountDAOImpl implements AccountDAO{

	@Override
	public int updateAccountBalance(int accNum, double balance) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccountStatus(int accNum, boolean approved, int approvedBy) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "Update bank_schema.accounts set approved =?, approvedBy =? where accountnum =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, approved);
			preparedStatement.setInt(2, approvedBy);
			preparedStatement.setInt(3, accNum);
			c = preparedStatement.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int rejectAccount(int accNum) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "Delete from bank_schema.accounts where accountnum =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accNum);
			c = preparedStatement.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int createAccount(Account account) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "Insert into bank_schema.accounts(balance, accountholder, approved) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, account.getBalance());
			preparedStatement.setInt(2, account.getAccountHolderId());
			preparedStatement.setBoolean(3, false);
			c = preparedStatement.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public double checkBalance(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account withdrawFunds(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account depositFunds(int cusId, int accountNum) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int transferFunds(int cusId, int accountSend, int accountRec) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException {
		List<UnapprovedAccount> unapprovedAccountList = new ArrayList<>();
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "Select u.name, a.accountnum, a.balance from bank_schema.user_table u join bank_schema.accounts a on u.userid = a.accountholder where a.approved = false";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UnapprovedAccount unapprovedAccount = new UnapprovedAccount();
				unapprovedAccount.setApplicantName(resultSet.getString("name"));
				unapprovedAccount.setAccountNum(resultSet.getInt("accountnum"));
				unapprovedAccount.setBalance(resultSet.getDouble("balance"));
				unapprovedAccountList.add(unapprovedAccount);
			}
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return unapprovedAccountList;
	}

	@Override
	public List<Account> viewAccountByCustomerId(int cusId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
