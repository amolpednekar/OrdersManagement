package com.psl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.psl.beans.StockItem;
import com.psl.beans.Units;

public class StockItemManagerDB {
	
	Connection conn;
	static String url="jdbc:mysql://localhost:3306/ordermgmtdb";
	static String uname="root";
	static String pass="root";
	void insertStockItems(ArrayList<StockItem> stockItem){
	//Inserts Stock Items details into data base
	}
	
	public ArrayList<StockItem> getStockItems(){
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection(url, uname, pass);
		Statement st;
		String query = "SELECT * FROM stockitem_details";
		StockItem stock = null;
		ArrayList<StockItem> list = new ArrayList<StockItem>();
		try {
			st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(query);
			
			
			while(rs.next()){
				stock = new StockItem();
				stock.setId(rs.getInt(1));
				stock.setName(rs.getString(2));
				stock.setDescription(rs.getString(3));
				stock.setUnit(Units.valueOf(rs.getString(4)));
				stock.setPpu(rs.getInt(5));
				stock.setMfgDate(rs.getDate(6));
				stock.setBestBefore(rs.getDate(7));
				list.add(stock);
			}
			for(StockItem s:list){
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	//Return StockItems List
	}
	void deleteStockItems(int no){ 
	//	delete stockItems
	}

}
