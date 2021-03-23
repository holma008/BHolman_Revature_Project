package com.app.bank.transaction.dao.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.app.bank.dao.dbutil.PostgresConnection;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.Transaction;
import com.app.bank.transaction.dao.TransactionDAO;

public class TransactionDAOImpl implements TransactionDAO{

	@Override
	public List<Transaction> pullAllTransactionData() throws BusinessException {
		List<Transaction> transactionList = new ArrayList<>();
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "select * from bank_schema.transactions";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getInt(1));
				transaction.setUserId(resultSet.getInt(2));
				transaction.setAccountNum(resultSet.getInt(3));
				transaction.setTransactionData(resultSet.getString(4));
				transaction.setTransactionType(resultSet.getString(5));
				transaction.setTimeOfTransaction(resultSet.getTimestamp(6));
				transactionList.add(transaction);
			}
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return transactionList;
	}

	@Override
	public int WithdrawalMade(int cusId, int accountNum, double withdraw) throws BusinessException {
		int c = 0;
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "insert into bank_schema.transactions (userid, accountid, transactiondata, type, timeof) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cusId);
			preparedStatement.setInt(2, accountNum);
			preparedStatement.setString(3, "user withdrew $"+ withdraw + " from account number " + accountNum);
			preparedStatement.setString(4, "withdrawl");
			preparedStatement.setTimestamp(5, timestamp);
			c = preparedStatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int DepositMade(int cusId, int accountNum, double deposit) throws BusinessException {
		int c = 0;
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "insert into bank_schema.transactions (userid, accountid, transactiondata, type, timeof) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cusId);
			preparedStatement.setInt(2, accountNum);
			preparedStatement.setString(3, "user deposited $"+ deposit + " into account number " + accountNum);
			preparedStatement.setString(4, "deposit");
			preparedStatement.setTimestamp(5, timestamp);
			c = preparedStatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public int Transfer(int cusId, int accountNum, int reciever, int rNum, double transfer) throws BusinessException {
		int c = 0;
		Calendar calendar = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "insert into bank_schema.transactions (userid, accountid, transactiondata, type, timeof) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cusId);
			preparedStatement.setInt(2, accountNum);
			preparedStatement.setString(3, "user deposited $"+ transfer + " from their account number " + accountNum + " into customer " + reciever + " account number " + rNum);
			preparedStatement.setString(4, "transfer");
			preparedStatement.setTimestamp(5, timestamp);
			c = preparedStatement.executeUpdate();
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

}
