package com.psl.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.psl.beans.Address;
import com.psl.beans.Customer;
import com.psl.beans.OrderItem;
import com.psl.beans.PurchaseOrder;
import com.psl.beans.StockItem;
import com.psl.dao.ConnectionManager;
import com.psl.dao.ConnectionManagerImpl;
import com.psl.dao.CustomerManagerDb;
import com.psl.dao.PurchaseOrderManagerDb;
import com.psl.dao.StockItemManagerDB;
import com.psl.utility.PurchaseOrderManagerImpl;


public class Client {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*ConnectionManager clientService = new ConnectionManagerImpl();
		clientService.getDBConnection("jdbc:mysql://localhost:3306/ordermgmtdb","root","root");
		clientService.closeConnection();
		*/
		
		//populateCustomerDetailsInDB();
		//populateOrderItemsInDB();
		
		//populatePurchaseOrderInDB();
		//pomd.insertcustomerOrder(1002,2015);
		//pomd.getAllOrdersByCustomer(1002);
		//getOrdersToBeShipped();
		//insertShippedOrdersInDB(pomd);
		//pomd.deletePurchaseOrder(2018);
		//pomd.deleteOrderItem(2015);

		PurchaseOrderManagerDb pomd = new PurchaseOrderManagerDb();
		
		PurchaseOrderManagerImpl client = new PurchaseOrderManagerImpl();
		/*client.populateCustomers();
		client.populateStoreItems();*/
		
		/*OrderItem o = new OrderItem();
		o.setOrderno(2009);
		o.setQty(5);
		o.setStockId(1235);
		ArrayList<OrderItem> list = new ArrayList<>();
		list.add(o);
		Date shipDate = new Date(2015, 12, 12);
		Date shipDateNull = null;
		client.createOrder(1007, list, shipDate);
		client.createOrder(1008, list, shipDateNull);
		*/
		
		//client.shipOrders(1005);
	//	pomd.getAllOrdersByCustomer(1005);
		//client.storePurchaseOrder();
		ArrayList<StockItem> stockList;
		StockItemManagerDB stockDB = new StockItemManagerDB();
		stockList = stockDB.getStockItems();
		
	}

	private static void insertShippedOrdersInDB(PurchaseOrderManagerDb pomd) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		List<PurchaseOrder> polist= new ArrayList<PurchaseOrder>();
		List<OrderItem> olist = new ArrayList<OrderItem>();
		PurchaseOrder po;
		OrderItem oi ;
		try {
			po = new PurchaseOrder(2009,(Date)(sf.parse("2015-01-25")),(Date)(sf.parse("2016-12-10")));
			oi = new OrderItem(2009,1200,25);
			polist.add(po);
			olist.add(oi);
			po = new PurchaseOrder(2010,(Date)(sf.parse("2017-01-25")),(Date)(sf.parse("2015-12-10")));
			oi = new OrderItem(2010,1205,25);
			polist.add(po);
			olist.add(oi);
			
			pomd.insertShippedOrders(1021, polist, olist, "Amol_03");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getOrdersToBeShipped() {
		PurchaseOrderManagerDb pomd = new PurchaseOrderManagerDb();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date fromDate = sf.parse("2016-11-10");
			Date toDate = sf.parse("2016-12-09");
			pomd.ordersToBeShipped(fromDate, toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void populatePurchaseOrderInDB() {
		PurchaseOrderManagerDb pomd = new PurchaseOrderManagerDb();
		PurchaseOrder po = new PurchaseOrder();
		
		Date orderDate = null;
		Date shippedDate = null;
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			orderDate = sf.parse("2016-10-10");
			shippedDate = sf.parse("2016-11-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		po.setOrderNo(2015);
		po.setOrderDate(orderDate);
		po.setShipdate(shippedDate);
		
		pomd.insertPurchaseOrder(po);
	}

	private static void populateOrderItemsInDB() {
		PurchaseOrderManagerDb pomd = new PurchaseOrderManagerDb();
		OrderItem o = new OrderItem();
		o.setOrderno(2009);
		o.setStockId(1236);
		o.setQty(15);
		pomd.insertOrderItem(o);
	}

	private static void populateCustomerDetailsInDB() {
		List<Customer> customerList = new ArrayList<>();
		Customer customer = new Customer();
		Address address = new Address();
		
		address.setCity("Panjim");
		address.setState("Goa");
		address.setStreet("Baker St.");
		address.setZip(1023456);
		customer.setId(1009);
		customer.setName("Vivek");
		customer.setAddress(address);
		customer.setEmail("xyz@gmail.com");
		customerList.add(customer);
		
		customer = new Customer();
		address = new Address();
		address.setCity("Mapusa");
		address.setState("Mahar");
		address.setStreet("Baker St. II");
		address.setZip(1023456);
		customer.setId(1010);
		customer.setName("Aaron");
		customer.setAddress(address);
		customer.setEmail("12xyz@gmail.com");
		customerList.add(customer);
	
		System.out.println(customerList);
		
		
		CustomerManagerDb cmdb = new CustomerManagerDb();
		cmdb.insertCustomers(customerList);
	}

}
