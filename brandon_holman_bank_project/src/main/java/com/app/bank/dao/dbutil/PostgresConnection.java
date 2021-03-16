package com.app.bank.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

	private static Connection connection;

	private PostgresConnection() {
		// disable constructor by making it private
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//if instance already created, return it
		//if (connection == null) {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "PSiaacog301";
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		//} else {
			//return connection;
		//}
	}
}