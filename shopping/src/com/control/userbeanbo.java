package com.control;

import java.sql.*;

public class userbeanbo {
	private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
	 //通过用户名得到用户对象
	public userbean getuserbyusername(String username){
		userbean ub=new userbean();
		try {
			ct=new connDB().getconnection();
			ps=ct.prepareStatement("select *from users where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				ub.setUserid(rs.getInt(1));
				ub.setUsername(rs.getString(2));
			    ub.setPassword(rs.getString(3));
			    ub.setEmail(rs.getString(4));
				ub.setAddress(rs.getString(5));
				ub.setPhone(rs.getString(6));
				ub.setRealname(rs.getString(7));
				ub.setGrade(rs.getInt(8));
				ub.setPostcode(rs.getString(9));
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.close();
			
		}
		
		return ub;
		
	}
	
	//关闭资源
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(ct!=null){
				ct.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//验证用户登录
	public boolean checkuser(String username,String password){
		boolean b=false;
		userbean ub=new userbean();
		try {
			ct=new connDB().getconnection();
			ps=ct.prepareStatement("select top 1 password from users where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			//用户存在
			if(rs.next()){
				//密码正确
				if(rs.getString(1).equals(password)){
					b=true;
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	
	
}
