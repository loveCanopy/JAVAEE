package com.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.email.SendMailToSomeone;
//�Զ�����Ĵ���
public class orderbo {
	private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
   public orderinfo addorder(mycartbo mcb,String userid){
	   orderinfo oif=new orderinfo();
	   try {
		ct=new connDB().getconnection();
		ps=ct.prepareStatement("insert into orders(userId,isPayed,totalPrice) values(?,?,?)");//����orders����
		ps.setString(1, userid);   
		ps.setInt(2, 0);   
		ps.setFloat(3, mcb.getallprice());   
		int a=ps.executeUpdate();
		//��ӳɹ�
		if(a==1){
			//�����ﳵ��Ϣ��ӵ�orderdetail����
			ArrayList a1=mcb.showcart();
			//�õ��ո���ӵ�orders���е�ordersid
			ps=ct.prepareStatement("select max(ordersId) from orders");
			rs=ps.executeQuery();
			int ordersId=0;
			if(rs.next()){
				ordersId=rs.getInt(1);
			}
			
		    Statement	sm=ct.createStatement();
			for(int i=0;i<a1.size();i++){
				goodsbean gb=(goodsbean)a1.get(i);
				//������ӵ�orderDetail����
				sm.addBatch("insert into orderDetail(ordersId,goodsId,nums) values("+ordersId+","+gb.goodsid+","+mcb.getnumbyid(gb.getGoodsid()+"")+")");
			}
		      //����ִ��
	      	sm.executeBatch();
		//���ж���ѯ���õ���Ӧ������ֵ
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
	 
 //���ݿ�ر���Դ
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



