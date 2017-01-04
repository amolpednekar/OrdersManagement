package com.psl.dao;

import java.sql.Connection;

public interface ConnectionManager {

	Connection getDBConnection(String url, String user, String pwd);
	void closeConnection();

}
