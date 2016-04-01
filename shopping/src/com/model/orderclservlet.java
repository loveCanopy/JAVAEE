package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.mycartbo;
import com.control.orderbo;
import com.control.orderinfo;
import com.control.userbean;
import com.model.email.SendMailToSomeone;

public class orderclservlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
        orderbo obo=new orderbo();
        //得到购物车信息和用户信息
        userbean ub=(userbean)request.getSession().getAttribute("userinfo");
        mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); 
        orderinfo oif=obo.addorder(mcb,ub.getUserid()+"");
       if(oif!=null){
    	     try {
    	    	 SendMailToSomeone smts=new SendMailToSomeone();
    	 		   smts.send("aa", "hello", "1039431583@qq.com",
    	 		    "993026523@qq.com", "www.baidu.com", "smtp.qq.com");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	   request.setAttribute("orderinfo", oif);
    	   request.getRequestDispatcher("orderinfo.jsp").forward(request, response);
    	   
       }else{
    	   
    	   request.getRequestDispatcher("myinfo.jsp").forward(request, response);
       }

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
