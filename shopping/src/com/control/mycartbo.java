package com.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//�Թ��ﳵ��һЩ����
public class mycartbo {
	 private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
	 
	HashMap hm=new HashMap<String ,String>();
	
	//���빺�ﳵ
	public void addcart(String goodsid,String goodsnum){
		hm.put(goodsid, goodsnum);	
	}

	//��չ��ﳵ
	public void clearcart(){
		
		hm.clear();
	}
	
	
	//�޸Ĺ��ﳵ
	public void updatecart(String goodsid,String goodsnum){
		hm.put(goodsid, goodsnum);
	}
	
	//ɾ������
	public void deletecart(String goodsid){
		hm.remove(goodsid);
	}
	
	//��ʾ���ﳵ����Ϣ    ��ArrayList ����
	
	public ArrayList showcart(){
		ArrayList a1=new ArrayList();
		try {
			ct=new connDB().getconnection();
			String sql="select * from goods where goodsid in";
			//���õ�����ȡ����
			Iterator it=hm.keySet().iterator();
			String sub="(";
			while(it.hasNext()){
				String goodsid=(String)it.next();
				
				if(it.hasNext()){
					
					sub+=goodsid+",";
					
				}else{
					
					sub+=goodsid+")";
					
				}
			}
			String sql1=sql+sub;
			ps=ct.prepareStatement(sql1);
			rs=ps.executeQuery();
			this.allprice=0.0f;
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
				this.allprice=this.allprice+rs.getFloat(4)*Integer.parseInt(this.getnumbyid(rs.getInt(1)+""));
				a1.add(gb);
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		finally{
			this.close();
		}
		
		return a1;
		
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
	
	
	//ͨ������ID�õ����������
	public String getnumbyid(String goodsid){
		
		return (String)hm.get(goodsid);
		
		
	}
	// �õ���Ʒ���ܼ�
	float allprice=0.0f;
	public float getallprice(){
		 return allprice;
	}
	
}
