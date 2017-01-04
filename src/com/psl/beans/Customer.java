package com.psl.beans;

import java.util.List;

public class Customer {

	private Integer id;
	private String name;
	private Address address;
	private String email;
	private List<PurchaseOrder> poList;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer id, String name, Address address, String email,
			List<PurchaseOrder> poList) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.poList = poList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getStringAddress() {
		return address.toString();
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PurchaseOrder> getPoList() {
		return poList;
	}

	public void setPoList(List<PurchaseOrder> poList) {
		this.poList = poList;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", email=" + email + ", poList=" + poList + "]";
	}
	
	
}
