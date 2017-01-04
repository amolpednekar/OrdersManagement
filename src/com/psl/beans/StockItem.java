package com.psl.beans;

import java.util.Date;

public class StockItem {

	private Integer id;
	private String name;
	private String description;
	private Units unit;
	private Integer ppu;
	private Date mfgDate;
	private Date bestBefore;
	
	public StockItem() {
		// TODO Auto-generated constructor stub
	}

	
	
	public StockItem(Integer id, String name, String description, Units unit,
			Integer ppu, Date mfgDate, Date bestBefore) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.unit = unit;
		this.ppu = ppu;
		this.mfgDate = mfgDate;
		this.bestBefore = bestBefore;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}

	public Integer getPpu() {
		return ppu;
	}

	public void setPpu(Integer ppu) {
		this.ppu = ppu;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getBestBefore() {
		return bestBefore;
	}

	public void setBestBefore(Date bestBefore) {
		this.bestBefore = bestBefore;
	}



	@Override
	public String toString() {
		return "StockItem [id=" + id + ", name=" + name + ", description="
				+ description + ", unit=" + unit + ", ppu=" + ppu
				+ ", mfgDate=" + mfgDate + ", bestBefore=" + bestBefore + "]";
	}
	
	
	
}
