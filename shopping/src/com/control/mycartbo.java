package com.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//对购物车的一些操作
public class mycartbo {
	 private Connection ct=null;
	 private PreparedStatement ps=null;
	 private ResultSet rs=null;
	 
	HashMap hm=new HashMap<String ,String>();
	
	//加入购物车
	public void addcart(String goodsid,String goodsnum){
		hm.put(goodsid, goodsnum);	
	}

	//清空购物车
	public void clearcart(){
		
		hm.clear();
	}
	
	
	//修改购物车
	public void updatecart(String goodsid,String goodsnum){
		hm.put(goodsid, goodsnum);
	}
	
	//删除货物
	public void deletecart(String goodsid){
		hm.remove(goodsid);
	}
	
	//显示购物车的信息    用ArrayList 保存
	
	public ArrayList showcart(){
		ArrayList a1=new ArrayList();
		try {
			ct=new connDB().getconnection();
			String sql="select * from goods where goodsid in";
			//利用迭代器取数据
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
	
	
	//通过货物ID得到货物的数量
	public String getnumbyid(String goodsid){
		
		return (String)hm.get(goodsid);
		
		
	}
	// 得到商品的总价
	float allprice=0.0f;
	public float getallprice(){
		 return allprice;
	}
	
}
