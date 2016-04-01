package com.model;

import java.util.Date;

public class CheckBuying {
	private int BuyingID;
	private int CustomerID;
	private int DessertID;
	private String DessertName;
	private int BuyingQuantity;
	private int BuyingTotalAmount;
	private String BuyingState;
	private Date BuyingDate;
	private int DessertSellingPrice;
	
	public int getBuyingID() {
		return BuyingID;
	}
	public void setBuyingID(int buyingID) {
		BuyingID = buyingID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getDessertID() {
		return DessertID;
	}
	public void setDessertID(int dessertID) {
		DessertID = dessertID;
	}
	public String getDessertName() {
		return DessertName;
	}
	public void setDessertName(String dessertName) {
		DessertName = dessertName;
	}
	public int getBuyingQuantity() {
		return BuyingQuantity;
	}
	public void setBuyingQuantity(int buyingQuantity) {
		BuyingQuantity = buyingQuantity;
	}
	public int getBuyingTotalAmount() {
		return BuyingTotalAmount;
	}
	public void setBuyingTotalAmount(int buyingTotalAmount) {
		BuyingTotalAmount = buyingTotalAmount;
	}
	public String getBuyingState() {
		return BuyingState;
	}
	public void setBuyingState(String buyingState) {
		BuyingState = buyingState;
	}
	public Date getBuyingDate() {
		return BuyingDate;
	}
	public void setBuyingDate(Date buyingDate) {
		BuyingDate = buyingDate;
	}
	public int getDessertSellingPrice() {
		return DessertSellingPrice;
	}
	public void setDessertSellingPrice(int dessertSellingPrice) {
		DessertSellingPrice = dessertSellingPrice;
	}
}
