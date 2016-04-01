package com.model;

import java.util.Date;

public class Buying {
	private static int BuyingID;
	private static int CustomerID;
	private static int DessertID;
	private static String DessertName;
	private static int BuyingQuantity;
	private static int BuyingTotalAmount;
	private static String BuyingState;
	private static Date BuyingDate;
	private static int DessertSellingPrice;
	
	public static int getBuyingID() {
		return BuyingID;
	}
	public void setBuyingID(int buyingID) {
		BuyingID = buyingID;
	}
	public static int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public static int getDessertID() {
		return DessertID;
	}
	public void setDessertID(int dessertID) {
		DessertID = dessertID;
	}
	public static String getDessertName() {
		return DessertName;
	}
	public void setDessertName(String dessertName) {
		DessertName = dessertName;
	}
	public static int getBuyingQuantity() {
		return BuyingQuantity;
	}
	public void setBuyingQuantity(int buyingQuantity) {
		BuyingQuantity = buyingQuantity;
	}
	public static int getBuyingTotalAmount() {
		return BuyingTotalAmount;
	}
	public void setBuyingTotalAmount(int buyingTotalAmount) {
		BuyingTotalAmount = buyingTotalAmount;
	}
	public static Date getBuyingDate() {
		return BuyingDate;
	}
	public void setBuyingDate(Date buyingDate) {
		BuyingDate = buyingDate;
	}
	public static String getBuyingState() {
		return BuyingState;
	}
	public void setBuyingState(String buyingState) {
		BuyingState = buyingState;
	}
	public static int getDessertSellingPrice() {
		return DessertSellingPrice;
	}
	public static void setDessertSellingPrice(int dessertSellingPrice) {
		DessertSellingPrice = dessertSellingPrice;
	}
}
