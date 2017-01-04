package com.psl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager {
	
	
	Connection conn=null;
	@Override
	public Connection getDBConnection(String url, String user, String pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			if(!conn.isClosed()){
				System.out.println("Connected.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void closeConnection() {
		try {
			if(!conn.isClosed()){
				conn.close();
				System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
