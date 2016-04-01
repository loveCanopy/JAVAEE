package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DbTools;
import com.model.Buying;
import com.model.CheckBuying;
import com.model.Dessert;

public class BuyingDao {
	public List CheckOrder(int customerID) {
		String sql="select * from buying where CustomerID='" + customerID + "'";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<CheckBuying> ls=new ArrayList<CheckBuying>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				CheckBuying buy=new CheckBuying();
				buy.setCustomerID(rs.getInt("CustomerID"));
				buy.setDessertID(rs.getInt("DessertID"));
				buy.setDessertName(rs.getString("DessertName"));
				buy.setBuyingQuantity(rs.getInt("BuyingQuantity"));
				buy.setBuyingTotalAmount(rs.getInt("BuyingTotalAmount"));
				buy.setBuyingState(rs.getString("BuyingState"));
				buy.setBuyingDate(rs.getDate("BuyingDate"));
				ls.add(buy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public List CheckSelling(int DessertID) {
		String sql="select * from buying where DessertID='" + DessertID + "'";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<CheckBuying> ls=new ArrayList<CheckBuying>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				CheckBuying buy=new CheckBuying();
				buy.setCustomerID(rs.getInt("CustomerID"));
				buy.setDessertID(rs.getInt("DessertID"));
				buy.setDessertName(rs.getString("DessertName"));
				buy.setBuyingQuantity(rs.getInt("BuyingQuantity"));
				buy.setBuyingTotalAmount(rs.getInt("BuyingTotalAmount"));
				buy.setBuyingState(rs.getString("BuyingState"));
				buy.setBuyingDate(rs.getDate("BuyingDate"));
				ls.add(buy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public List CheckAllSelling() {
		String sql="select * from buying";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<CheckBuying> ls=new ArrayList<CheckBuying>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				CheckBuying buy=new CheckBuying();
				buy.setCustomerID(rs.getInt("CustomerID"));
				buy.setDessertID(rs.getInt("DessertID"));
				buy.setDessertName(rs.getString("DessertName"));
				buy.setBuyingQuantity(rs.getInt("BuyingQuantity"));
				buy.setBuyingTotalAmount(rs.getInt("BuyingTotalAmount"));
				buy.setBuyingState(rs.getString("BuyingState"));
				buy.setBuyingDate(rs.getDate("BuyingDate"));
				ls.add(buy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public int AddOrder(Buying buy) {
		int i=0;
		String sql="insert into buying (CustomerID,DessertID,DessertName,BuyingQuantity,BuyingTotalAmount,BuyingState,BuyingDate)values(?,?,?,?,?,?,?)";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, buy.getCustomerID());
			st.setInt(2, buy.getDessertID());
			st.setString(3, buy.getDessertName());
			st.setInt(4, buy.getBuyingQuantity());
			st.setInt(5, buy.getBuyingTotalAmount());
			st.setString(6, buy.getBuyingState());
			st.setDate(7, (Date) buy.getBuyingDate());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
