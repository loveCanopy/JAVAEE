package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.DbTools;
import com.model.Dessert;
import com.model.Staff;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
	public int StaffLogin(String StaffName, String StaffPassWord) {
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		List<Staff> ls=new ArrayList<Staff>();
		try {
			String sql = "select * from staff where StaffName='" + StaffName + "' and StaffPassWord='" + StaffPassWord+"'";
			PreparedStatement st =conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return 1;
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}
}
