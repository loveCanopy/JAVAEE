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
		//�õ�����  ���͹����Ļ���id
		String goodsid=request.getParameter("goodsid");
		//����mycartbo�еļ��빺�ﳵ�ķ������˿�Ĭ�Ϲ���һ��
//	   mycartbo mcb=new mycartbo();
	   //ÿ�ζ���ʼ��һ�����ﳵ�����ֻ�ܱ���һ��cart
		
		//�����ж�Ҫ�Թ��ﳵ����������
	   String type=request.getParameter("type");	
	   mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); //��session��ȡֵ
	   if(mcb==null){
		   mcb=new mycartbo();
		   request.getSession().setAttribute("mycart", mcb);
	   }
	   if(type.equals("add")){
		   //���빺�ﳵ
		   if(mcb.getnumbyid(goodsid+"")!=null){
	           mcb.addcart(goodsid, 1+Integer.parseInt(mcb.getnumbyid(goodsid+""))+"");
	   }else{
		   mcb.addcart(goodsid, 1+"");
	   }
	   }
	   else if(type.equals("delete")){
		   //ɾ����Ʒ
		   mcb.deletecart(goodsid);
		   
	   }else if(type.equals("clear")){
		   
		   mcb.clearcart();
	   }else if(type.equals("update")){
		   String nums[]=request.getParameterValues("goodsnum");
		   String ids[]=request.getParameterValues("goodsid");
		   for(int i=0;i<nums.length;i++){
		   mcb.updatecart(ids[i], nums[i]);
//		   System.out.println(ids[i]+"  "+nums[i]);����һ��
		   }
	   }else if(type.equals("show")){
		   
	   }
	   
//	   //��ʾ���ﳵ����Ϣ
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
