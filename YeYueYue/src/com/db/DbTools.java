package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTools {
	Connection conn;
	public Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String DBURL= "jdbc:mysql://localhost:3306/yeyueyue";		// ·��
			String DBUSER = "root";										// �Ñ���
			String DBPASS = "root";										// �ܴa
			conn=DriverManager.getConnection(DBURL, DBUSER, DBPASS);	// ȡ�Ô������B��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
