package com.psl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.psl.beans.OrderItem;
import com.psl.beans.PurchaseOrder;

public class PurchaseOrderManagerDb {
	static Connection conn = null;
	static String url="jdbc:mysql://localhost:3306/ordermgmtdb";
	static String uname="root";
	static String pass="root";
	
	public Boolean insertOrderItem(OrderItem orderItem) {
		
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		String sql = "INSERT INTO ORDEREDITEM_DETAILS VALUES(?,?,?)";
		int status = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, null);
			ps.setInt(2, orderItem.getStockId());
			ps.setInt(3, orderItem.getQty());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		if(status==1){
			return true;
		}
		else{
			return false;
		}
	}
	public Boolean insertPurchaseOrder(PurchaseOrder purchaseOrder){
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		String sql = "INSERT INTO PURCHASEORDER_DETAILS VALUES(?,?,?)";
		int status = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, null);
			//System.out.println(purchaseOrder.getOrderDate().getTime());
			java.sql.Date sqlOrderDate =  new java.sql.Date(purchaseOrder.getOrderDate().getTime());
			ps.setDate(2, sqlOrderDate);
			java.sql.Date sqlShippedDate =  new java.sql.Date(purchaseOrder.getShipdate().getTime());
			ps.setDate(3, sqlShippedDate);
			status = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		if(status==1){
			return true;
		}
		else{
			return false;
		}
		
	}
	public Boolean insertcustomerOrder(int customer_no,int order_no){
		Connection conn=null;
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		String sql = "INSERT INTO CUSTOMER_ORDER_DETAILS VALUES(?,?)";
		int status = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, customer_no);
			ps.setInt(2, order_no);
			status = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		if(status==1){
			return true;
		}
		else{
			return false;
		}
	}

	public ArrayList<PurchaseOrder> getAllOrdersByCustomer(int cust_id){
		
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		Statement stmt = null;
		String query = "select * from purchaseorder_details where orderno in (select od.orderno from ordereditem_details od,customer_order_details cd where od.orderno=cd.order_no AND cd.customer_no="+cust_id+")";
		ResultSet rs;
		PurchaseOrder purchaseOrder;
		ArrayList<PurchaseOrder> pList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){
				purchaseOrder = new PurchaseOrder();
				purchaseOrder.setOrderNo(rs.getInt(1));
				purchaseOrder.setOrderDate(rs.getDate(2));
				purchaseOrder.setShipdate(rs.getDate(3));
				pList.add(purchaseOrder);
			}
			/*for(PurchaseOrder p:pList){
				System.out.println(p);
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Return all orders placed by customer
		return pList;
	}
	
	public ArrayList<OrderItem> returnOrderItems(int cust_id){
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		Statement stmt = null;
		/*String query = "select od.orderno,od.id,od.quantity from ordereditem_details od,purchaseorder_details pd "
				+ "where od.orderno=pd.orderno;";*/
		String query = "select od.orderno,od.id,od.quantity from ordereditem_details od where od.orderno =(select order_no from customer_order_details where customer_no="+cust_id+")";
		ResultSet rs;
		OrderItem oitem = null;
		ArrayList<OrderItem> ordersList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				oitem = new OrderItem();
				oitem.setOrderno(rs.getInt(1));
				oitem.setQty(rs.getInt(3));
				oitem.setStockId(rs.getInt(2));
				ordersList.add(oitem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		for(OrderItem o:ordersList){
			System.out.println("inside func");
			System.out.println(o);
		}*/
		return ordersList;
		
	}
	
	public ArrayList<PurchaseOrder> ordersToBeShipped(Date fromdate, Date todate){
		ConnectionManagerImpl db = new ConnectionManagerImpl();
		conn = db.getDBConnection(url,uname,pass);
		String query = "SELECT * from PURCHASEORDER_DETAILS";
		Date shippedDate;
		ArrayList<PurchaseOrder> posl = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				PurchaseOrder po = new PurchaseOrder();
				po.setOrderNo(rs.getInt(1));
				po.setOrderDate(rs.getDate(2));
				po.setShipdate(rs.getDate(3));
				ResultSet rs2 = stmt2.executeQuery("select od.orderno,od.id,od.quantity from ordereditem_details od"
						+ ", purchaseorder_details pd where pd.orderno="+po.getOrderNo()+" AND od.orderno="+po.getOrderNo());
				List<OrderItem> olist = new ArrayList<>();
				while(rs2.next()){
					OrderItem o = new OrderItem();
					o.setOrderno(rs2.getInt(1));
					o.setStockId(rs2.getInt(2));
					o.setQty(rs2.getInt(3));
					olist.add(o);
					//System.out.println(o);
				}
				po.setOrdList(olist);
				shippedDate = po.getShipdate();
				if(shippedDate.after(fromdate) && shippedDate.before(todate)){
					posl.add(po);
				}
			}
			for(PurchaseOrder p:posl){
				System.out.println(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return posl;
		//Return all orders to be shipped in specified duration.
	}
	
	public void deletePurchaseOrder(int orderno) {
	//delete purchase orders
		ConnectionManager db = new ConnectionManagerImpl();
		conn = db.getDBConnection(url, uname, pass);
		String query = "DELETE FROM PURCHASEORDER_DETAILS WHERE orderno="+orderno;
		try {
			Statement stmt = conn.createStatement();
			int status = stmt.executeUpdate(query);
			System.out.println("Stauts: "+status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteOrderItem(int orderno){
	//delete OrderItems
		ConnectionManager db = new ConnectionManagerImpl();
		conn = db.getDBConnection(url, uname, pass);
		String query = "DELETE FROM ordereditem_details WHERE orderno="+orderno;
		try {
			Statement stmt = conn.createStatement();
			int status = stmt.executeUpdate(query);
			System.out.println("Stauts: "+status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void insertShippedOrders(int cust_no,List<PurchaseOrder> purchaseOrder,
			List<OrderItem> orderItem,String PersonName){
	//Inserts data into shipped_order_details
		ConnectionManager db = new ConnectionManagerImpl();
		conn = db.getDBConnection(url, uname, pass);
		String insertQuery = "Insert into shipped_order_details values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(insertQuery);
			int itr = purchaseOrder.size();
			int index=0;
			while(index<itr){
				PurchaseOrder po;
				OrderItem oi;
				po = purchaseOrder.get(index);
				oi = orderItem.get(index);
				
				stmt.setInt(1, cust_no);
				stmt.setInt(2,po.getOrderNo());
				java.sql.Date orderDate = new java.sql.Date(po.getOrderDate().getTime());
				stmt.setDate(3, orderDate);
				java.sql.Date shipDate = new java.sql.Date(po.getShipdate().getTime());
				stmt.setDate(4, shipDate);
				stmt.setInt(5, oi.getStockId());
				stmt.setInt(6, oi.getQty());
				stmt.setString(7, PersonName);
				
				int status = stmt.executeUpdate();
				System.out.println(status + " rows affected");
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
