package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.mycartbo;

public class showmycartservlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//得到购物  传送过来的货物id
		String goodsid=request.getParameter("goodsid");
		//调用mycartbo中的加入购物车的方法，此刻默认购买一本
//	   mycartbo mcb=new mycartbo();
	   //每次都初始化一个购物车会造成只能保留一个cart
		
		//首先判断要对购物车操作的类型
	   String type=request.getParameter("type");	
	   mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); //从session中取值
	   if(mcb==null){
		   mcb=new mycartbo();
		   request.getSession().setAttribute("mycart", mcb);
	   }
	   if(type.equals("add")){
		   //加入购物车
		   if(mcb.getnumbyid(goodsid+"")!=null){
	           mcb.addcart(goodsid, 1+Integer.parseInt(mcb.getnumbyid(goodsid+""))+"");
	   }else{
		   mcb.addcart(goodsid, 1+"");
	   }
	   }
	   else if(type.equals("delete")){
		   //删除商品
		   mcb.deletecart(goodsid);
		   
	   }else if(type.equals("clear")){
		   
		   mcb.clearcart();
	   }else if(type.equals("update")){
		   String nums[]=request.getParameterValues("goodsnum");
		   String ids[]=request.getParameterValues("goodsid");
		   for(int i=0;i<nums.length;i++){
		   mcb.updatecart(ids[i], nums[i]);
//		   System.out.println(ids[i]+"  "+nums[i]);测试一下
		   }
	   }else if(type.equals("show")){
		   
	   }
	   
//	   //显示购物车的信息
//	   ArrayList a1=mcb.showcart();
//	   request.setAttribute("mycartinfo", a1);
//	  request.getRequestDispatcher("showmycart.jsp").forward(request, response);	
	   response.sendRedirect("gotocartservlet");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  this.doGet(request, response);
	}

}
