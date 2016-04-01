package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTools {
	Connection conn;
	public Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String DBURL= "jdbc:mysql://localhost:3306/yeyueyue";		// 路徑
			String DBUSER = "root";										// 用戶名
			String DBPASS = "root";										// 密碼
			conn=DriverManager.getConnection(DBURL, DBUSER, DBPASS);	// 取得數據庫連接
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
