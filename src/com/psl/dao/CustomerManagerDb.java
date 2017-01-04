package com.psl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.psl.beans.Address;
import com.psl.beans.Customer;

public class CustomerManagerDb {
	Connection conn;
	public void insertCustomers(List<Customer> customerList){
		//Inserts Customers details into database
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");

		try {
			String prepStmt = "INSERT INTO CUSTOMER_DETAILS VALUES(?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(prepStmt);
			for(Customer c:customerList){
				Address a;
				a = c.getAddress();
				ps.setInt(1, c.getId());
				ps.setString(2, c.getName());
				ps.setString(3, a.getStreet()+","+a.getCity()+","+a.getState()+","+a.getZip());
				ps.setString(4, c.getEmail());
				int status  = ps.executeUpdate();
				System.out.println(status + "rows affectied!");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
	}
}
