package com.psl.beans;

public class Address {

	private String street;
	private String city;
	private String state;
	private Integer zip;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Address(String street, String city, String state, Integer zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getZip() {
		return zip;
	}


	public void setZip(Integer zip) {
		this.zip = zip;
	}


	@Override
	public String toString() {
		return street + "," + city + "," + state + "," + zip ;
	}
	
	
}
