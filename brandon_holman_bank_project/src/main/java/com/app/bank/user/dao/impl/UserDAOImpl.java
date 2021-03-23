package com.app.bank.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.bank.dao.dbutil.PostgresConnection;
import com.app.bank.exception.BusinessException;
import com.app.bank.model.User;
import com.app.bank.user.dao.UserDAO;

public class UserDAOImpl implements UserDAO{

	@Override
	public int registerCustomer(User user) throws BusinessException {
		int c = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "insert into bank_schema.user_table(name, email, usertype, password, phonenumber, address, dateofbirth) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, "c");//force register new users as customers
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getPhoneNumber());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setDate(7, user.getDateOfBirth());
			c = preparedStatement.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		return c;
	}

	@Override
	public User getCustomerLogin(String email, String password) throws BusinessException {
		User user = null;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "select userid, name, usertype from bank_schema.user_table where email =? and password =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setName(resultSet.getString("name"));
				user.setUserType(resultSet.getString("usertype"));
			}
			if(user == null) {
				throw new BusinessException("no user found with email and password combination");
			}
		}catch(ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error");
		}
		return user;
	}

	@Override
	public int customerExists(String email) throws BusinessException {
		int r = 0;
		try(Connection connection = PostgresConnection.getConnection()){
			String sql = "select userid from bank_schema.user_table where email =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				r = resultSet.getInt("userid");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error");
		}
		return r;
	}
}
