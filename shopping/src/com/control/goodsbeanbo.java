package com.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class goodsbeanbo {

	 private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
	 //通过货物ID得到货物
	public goodsbean getgb(String goodsid){
		
		goodsbean gb=new goodsbean();
		
		try {
			ct=new connDB().getconnection();
			ps=ct.prepareStatement("select * from goods where goodsid=?");
			ps.setString(1, goodsid);
			rs=ps.executeQuery();
			if(rs.next()){
				gb.setGoodsid(rs.getInt(1));
				gb.setGoodsname(rs.getString(2));
				gb.setGoodsintro(rs.getString(3));
				gb.setGoodsprice(rs.getFloat(4));
				gb.setGoodsnum(rs.getInt(5));
				gb.setPhoto(rs.getString(7));
				gb.setPublisher(rs.getString(6));
				gb.setType(rs.getString(8));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			this.close();
			
		}
	  
		return gb;
	}
	
	
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
	
	//分页 
	public ArrayList getpage(int pageSize,int pageNow)
	{
		ArrayList a1=new ArrayList();
		
		try {
		ct=new connDB().getconnection();
		ps=ct.prepareStatement("select top "+pageSize+" * from goods where goodsid not in(select top "+pageSize*(pageNow-1)+"goodsid from goods)");	
		rs=ps.executeQuery();
		while(rs.next()){
			goodsbean gb=new goodsbean();
			gb.setGoodsid(rs.getInt(1));
			gb.setGoodsname(rs.getString(2));
			gb.setGoodsintro(rs.getString(3));
			gb.setGoodsprice(rs.getFloat(4));
			gb.setGoodsnum(rs.getInt(5));
			gb.setPhoto(rs.getString(7));
			gb.setPublisher(rs.getString(6));
			gb.setType(rs.getString(8));
			a1.add(gb);
			
			
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			
			this.close();
			
		}
		return a1;
	}
	
	//得到共有多少页
	
	
	public int pageCount(int pageSize){
		int rowCount=1;
		int pageCount=1;
		try {
			ct=new connDB().getconnection();
			ps=ct.prepareStatement("select count(*) from goods");
			rs=ps.executeQuery();
			if(rs.next()){
				rowCount=rs.getInt(1);
			}
			if(rowCount%pageSize==0){
				
				pageCount=rowCount%pageSize;
				
			}
			else{
				pageCount=rowCount%pageSize+1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			this.close();
		}
		
		return pageCount;
		
		
		
	}
	
	
	
}
