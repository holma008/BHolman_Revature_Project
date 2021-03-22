package com.app.bank.account.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
import com.app.bank.model.User;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public int updateAccountBalance(int accNum, double balance) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "update bank_schema.accounts set balance =? where accountnum =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, balance);
			preparedStatement.setInt(2, accNum);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int updateAccountStatus(int accNum, boolean approved, int approvedBy) throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Update bank_schema.accounts set approved =?, approvedBy =? where accountnum =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, approved);
			preparedStatement.setInt(2, approvedBy);
			preparedStatement.setInt(3, accNum);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int rejectAccount(int accNum) throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Delete from bank_schema.accounts where accountnum =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accNum);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int createAccount(Account account) throws BusinessException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Insert into bank_schema.accounts(balance, accountholder, approved, opened) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, account.getBalance());
			preparedStatement.setInt(2, account.getAccountHolderId());
			preparedStatement.setBoolean(3, false);
			preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public double checkBalance(int cusId, int accountNum) throws BusinessException {
		Double balance = null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select balance from bank_schema.accounts where accountholder =? and accountnum=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cusId);
			preparedStatement.setInt(2, accountNum);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				balance = resultSet.getDouble("balance");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return balance;
	}

	@Override
	public int transferFunds(int cusId, int accountSend, int accountRec) throws BusinessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UnapprovedAccount> viewUnapprovedAccounts() throws BusinessException {
		List<UnapprovedAccount> unapprovedAccountList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select u.name, a.accountnum, a.balance from bank_schema.user_table u join bank_schema.accounts a on u.userid = a.accountholder where a.approved = false";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UnapprovedAccount unapprovedAccount = new UnapprovedAccount();
				unapprovedAccount.setApplicantName(resultSet.getString("name"));
				unapprovedAccount.setAccountNum(resultSet.getInt("accountnum"));
				unapprovedAccount.setBalance(resultSet.getDouble("balance"));
				unapprovedAccountList.add(unapprovedAccount);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return unapprovedAccountList;
	}

	@Override
	public List<User> viewAllAccounts() throws BusinessException {
		List<User> customerList = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select userid, name from bank_schema.user_table where usertype ='c'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User customer = new User();
				customer.setUserId(resultSet.getInt("userid"));
				customer.setName(resultSet.getString("name"));
				customerList.add(customer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return customerList;
	}

	@Override
	public List<Account> viewAccountByCustomerId(int cusId) throws BusinessException {
		List<Account> customerAccounts = new ArrayList<>();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "Select accountnum, balance, opened from bank_schema.accounts where accountholder =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cusId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setAccountNum(resultSet.getInt("accountnum"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setOpened(resultSet.getDate("opened"));
				customerAccounts.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return customerAccounts;
	}
}
