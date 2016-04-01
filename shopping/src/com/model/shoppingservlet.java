package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.mycartbo;
import com.control.userbean;

public class shoppingservlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		userbean ub=(userbean)request.getSession().getAttribute("userinfo");
		//如果用户登录过
		if(ub==null)
		{
		//如果没有登录过
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}else{
			//同时把购物车的信息取出来  ，传入下一页面
			 mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); 
			 ArrayList a1=mcb.showcart();
			 request.setAttribute("mycartinfo", a1);		
			request.getRequestDispatcher("myinfo.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
