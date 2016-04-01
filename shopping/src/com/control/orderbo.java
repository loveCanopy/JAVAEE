package com.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.email.SendMailToSomeone;
//对订单表的处理
public class orderbo {
	private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
   public orderinfo addorder(mycartbo mcb,String userid){
	   orderinfo oif=new orderinfo();
	   try {
		ct=new connDB().getconnection();
		ps=ct.prepareStatement("insert into orders(userId,isPayed,totalPrice) values(?,?,?)");//加入orders表中
		ps.setString(1, userid);   
		ps.setInt(2, 0);   
		ps.setFloat(3, mcb.getallprice());   
		int a=ps.executeUpdate();
		//添加成功
		if(a==1){
			//将购物车信息添加到orderdetail表中
			ArrayList a1=mcb.showcart();
			//得到刚刚添加到orders表中的ordersid
			ps=ct.prepareStatement("select max(ordersId) from orders");
			rs=ps.executeQuery();
			int ordersId=0;
			if(rs.next()){
				ordersId=rs.getInt(1);
			}
			
		    Statement	sm=ct.createStatement();
			for(int i=0;i<a1.size();i++){
				goodsbean gb=(goodsbean)a1.get(i);
				//批量添加到orderDetail表中
				sm.addBatch("insert into orderDetail(ordersId,goodsId,nums) values("+ordersId+","+gb.goodsid+","+mcb.getnumbyid(gb.getGoodsid()+"")+")");
			}
		      //批量执行
	      	sm.executeBatch();
		//进行多表查询，得到相应的数据值
//	      	int ordersId;
//	    	String realname;
//	        String address;
//	        String postcode;
//	        String phone;
//	        float totalPrice;
//	        String username;
//	        String email;
	      	String sql="select ordersId,realname,address,postcode,phone,totalPrice,username,email from users,orders where ordersId=? and users.userid = (select orders.userId from orders where ordersId=?)";
	          ps=ct.prepareStatement(sql);
	          ps.setInt(1,ordersId );
	          ps.setInt(2, ordersId);
	         rs=ps.executeQuery();
	         if(rs.next()){
	        	 oif.setOrdersId(rs.getInt(1));
	        	 oif.setRealname(rs.getString(2));
	        	 oif.setAddress(rs.getString(3));
	        	 oif.setPostcode(rs.getString(4));
	        	 oif.setPhone(rs.getString(5));
	        	 oif.setTotalPrice(rs.getFloat(6));
	        	 oif.setUsername(rs.getString(7));
	        	 oif.setEmail(rs.getString(8));
	         }
	      	
		
		
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	}finally{
		
		this.close();
		
	}
	   if(oif!=null){

		   return oif;
		   
	   }else{
		   return null;
	   }
	  
   }	 
	 
 //数据库关闭资源
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
}



