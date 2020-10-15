package com.demo.bean;

import java.io.Serializable;
import java.sql.Date;

public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private double price;
	private String status;
	private Date updateDate;
	private Category category;
	
	public Goods() {
	}
	public Goods(long id, String name, double price, String status, Date updateDate, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.updateDate = updateDate;
		this.category = category;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", status=" + status + ", updateDate="
				+ updateDate + ", category=" + category + "]";
	}
	
}
