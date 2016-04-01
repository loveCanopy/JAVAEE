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
import com.model.Customer;

public class CustomerDao {
	public List CustomerData(int customerID) {
		String sql="select * from customer where CustomerID='" + customerID + "'";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Customer> ls=new ArrayList<Customer>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Customer customer=new Customer();
				customer.setCustomerID(rs.getInt("CustomerID"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setCustomerGender(rs.getString("CustomerGender"));
				customer.setCustomerAge(rs.getInt("CustomerAge"));
				customer.setCustomerPhone(rs.getString("CustomerPhone"));
				customer.setCustomerJob(rs.getString("CustomerJob"));
				customer.setCustomerAddress(rs.getString("CustomerAddress"));
				ls.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public int AddCustomer(Customer customer) {
		int i=0;
		String sql="insert into customer (CustomerName,CustomerGender,CustomerAge,CustomerPhone,CustomerJob,CustomerAddress)values(?,?,?,?,?,?)";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, customer.getCustomerName());
			st.setString(2, customer.getCustomerGender());
			st.setInt(3, customer.getCustomerAge());
			st.setString(4, customer.getCustomerPhone());
			st.setString(5, customer.getCustomerJob());
			st.setString(6, customer.getCustomerAddress());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
	
	public int UpdateCustomer(Customer customer) {
		int i=0;
		String sql="update customer set CustomerName=?,CustomerGender=?,CustomerAge=?,CustomerPhone=?,CustomerJob=?,CustomerAddress=? where CustomerName=?";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, customer.getCustomerName());
			st.setString(2, customer.getCustomerGender());
			st.setInt(3, customer.getCustomerAge());
			st.setString(4, customer.getCustomerPhone());
			st.setString(5, customer.getCustomerJob());
			st.setString(6, customer.getCustomerAddress());
			st.setString(7, customer.getCustomerName());
			i=st.executeUpdate();
			st.close();
			conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return i;
	}
}
