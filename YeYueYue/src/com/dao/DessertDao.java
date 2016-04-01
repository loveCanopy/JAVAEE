package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DbTools;
import com.model.Dessert;

public class DessertDao {
	public List SelectMenu() {
		String sql="select * from dessert";
		DbTools db = new DbTools();
		Connection conn = db.getConn();
		ResultSet rs=null;
		List<Dessert> ls=new ArrayList<Dessert>();
		try {
			PreparedStatement st =conn.prepareStatement(sql);
			rs=st.executeQuery(sql);
			while(rs.next()){
				Dessert des=new Dessert();
				des.setDessertID(rs.getInt("DessertID"));
				des.setDessertName(rs.getString("DessertName"));
				des.setDessertDescription(rs.getString("DessertDescription"));
				des.setDessertSellingPrice(rs.getInt("DessertSellingPrice"));
				des.setDessertPicture(rs.getString("DessertPicture"));
				des.setDessertStorage(rs.getInt("DessertStorage"));
				ls.add(des);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	
}
