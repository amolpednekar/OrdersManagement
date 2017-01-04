package com.psl.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.psl.beans.Address;
import com.psl.beans.Customer;
import com.psl.beans.OrderItem;
import com.psl.beans.PurchaseOrder;
import com.psl.beans.StockItem;
import com.psl.beans.Units;
import com.psl.dao.PurchaseOrderManagerDb;

public class PurchaseOrderManagerImpl implements PurchaseOrderManager{

	@Override
	public List<Customer> populateCustomers() {
		
		Customer c;
		Address a ;
		List<Customer> customerList = new ArrayList<Customer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("customers.txt"));
			String str;
			String [] splits;
			while((str=br.readLine())!=null){
				c = new Customer();
				a = new Address();
				splits = str.split(",");
				c.setId(Integer.valueOf(splits[0]));
				c.setName(splits[1]);
				a.setStreet(splits[2]);
				a.setCity(splits[3]);
				a.setState(splits[4]);
				a.setZip(Integer.valueOf(splits[5]));
				c.setAddress(a);
				c.setEmail(splits[6]);
				customerList.add(c);
			}
			
			for(Customer cust:customerList){
				System.out.println(cust);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return customerList;
	}

	@Override
	public List<StockItem> populateStoreItems() {
		List<StockItem> stockList = new ArrayList<StockItem>();
		StockItem s;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			//1237,Earphone,moto,nos,110,2016/01/11,2017/10/12
			BufferedReader br = new BufferedReader(new FileReader("stockitems.txt"));
			String str;
			String [] splits;
			while((str=br.readLine())!=null){
				s = new StockItem();
				splits = str.split(",");
				s.setId(Integer.valueOf(splits[0]));
				s.setName(splits[1]);
				s.setDescription(splits[2]);
				s.setUnit(Units.valueOf(splits[3]));
				s.setPpu(Integer.valueOf(splits[4]));
				Date mfgDate = sf.parse(splits[5]);
				s.setMfgDate(mfgDate);
				Date expDate = sf.parse(splits[6]);
				s.setBestBefore(expDate);
				stockList.add(s);
			}
			br.close();
			for(StockItem temp:stockList){
				System.out.println(temp);
			}
		}catch (IOException | ParseException e) {
			e.printStackTrace();
		}
			
		return stockList;
	}

	@Override
	public void createOrder(int cust_id, ArrayList<OrderItem> OrderedItems,
			Date ship_date) {
		/*-	Purchase order for customer will be generated for current date 
		and shipping date will set as per customer’s request. 
		If customer does not specify then set the date after 6 days of order date*/
		
		PurchaseOrderManagerDb db = new PurchaseOrderManagerDb();
		PurchaseOrder po;
		
		for(OrderItem oi:OrderedItems){
			db.insertOrderItem(oi);
		}
		
		for(OrderItem oi:OrderedItems){
			po = new PurchaseOrder();
			po.setOrderNo(oi.getOrderno());
			po.setOrderDate(new Date());
			po.setShipdate(calculateShippedDate(ship_date));
			po.setOrdList(OrderedItems);
			po.setPurchOrder(null);
			db.insertPurchaseOrder(po);
		}
	
		for(OrderItem oi:OrderedItems){
			db.insertcustomerOrder(cust_id, oi.getOrderno());
		}
		
	}

	private Date calculateShippedDate(Date ship_date) {
		Date todayDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DAY_OF_MONTH, 6);
		if(ship_date==null){
			ship_date = cal.getTime();
		}
		return ship_date; 
		//System.out.println("Date to be shipped: " + ship_date);
	}

	@Override
	public void storePurchaseOrder() {
		// TODO Auto-generated method stub
		//Store PurchaseOrder Object into File. Don’t store Stock Items details 
		List<PurchaseOrder> purchaseList = new ArrayList<PurchaseOrder>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PoNo_99.ser"));
			PurchaseOrder readObj,writeObj = null;
			
			readObj = new PurchaseOrder();
			while((readObj=(PurchaseOrder) ois.readObject())!=null){
				writeObj = new PurchaseOrder();
				writeObj.setOrderDate(readObj.getOrderDate());
				writeObj.setOrderNo(readObj.getOrderNo());
				writeObj.setOrdList(readObj.getOrdList());
				writeObj.setPurchOrder(readObj.getPurchOrder());
				writeObj.setShipdate(readObj.getShipdate());
				purchaseList.add(writeObj);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}finally{
			for(PurchaseOrder p:purchaseList){
				System.out.println(p);
			}
		}
		
	}

	@Override
	public void shipOrders(int cust_id) {
		// TODO Auto-generated method stub
		
		PurchaseOrderManagerDb pdb = new PurchaseOrderManagerDb();
		
		ArrayList<PurchaseOrder> plist = new ArrayList<>();
		ArrayList<OrderItem> olist = new ArrayList<>();
		plist = pdb.getAllOrdersByCustomer(1005);
		olist = pdb.returnOrderItems(1005);
		//System.out.println(plist.size());
		for(PurchaseOrder p:plist){
			System.out.println(p);
		}
		for(OrderItem o:olist){
			System.out.println(o);
		}
		olist = pdb.returnOrderItems(1005);
		
		//pdb.insertShippedOrders(cust_id,plist,olist,"Nilesh"); Works! Commented to avoid PK conflicts
		
		File file = new File("bill.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			PrintWriter p = new PrintWriter(file);
			p.println("CustomerId:" + cust_id);
			p.println("Customer Name: Nilesh");
			
			for(PurchaseOrder po:plist){
				p.println("Order Date: " + sdf.format(po.getOrderDate()));
				p.println("Shipped Date: " + sdf.format(po.getShipdate()));
				for(OrderItem oi:olist){
					p.println("OrderNo\tQuantity");
					p.println(oi.getOrderno()+"\t"+oi.getQty());
				}
			}
				
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Void removeExpiredItems() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Void showItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void applyDiscountOnItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Customer, ArrayList<PurchaseOrder>> getOrdersByCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void displayDiscountedItemsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
