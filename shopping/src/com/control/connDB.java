package com.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connDB {

	public Connection getconnection(){

		 Connection ct=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		 //¼ÓÔØÇý¶¯
		    try {
		    	  
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				try {
					ct=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=shooping","sa","246437");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		
		
		return ct;
	}
      
	
	
	
	
	
	
	
}
