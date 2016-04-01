package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.mycartbo;
import com.control.userbean;
import com.control.userbeanbo;

public class loginclservlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String values=request.getParameter("radiobutton");
		userbeanbo ubb=new userbeanbo();
		if(ubb.checkuser(username, password)){
			//�û���¼��֤�ɹ�
			
			//ͨ���û����õ��û�����
		   userbean ub=(userbean)ubb.getuserbyusername(username);
			//���û���Ϣ����session��
			request.getSession().setAttribute("userinfo", ub);
			//ͬʱ�ѹ��ﳵ����Ϣȡ����  ��������һҳ��
			 mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); 
			 ArrayList a1=mcb.showcart();
			 request.setAttribute("mycartinfo", a1);
			request.getRequestDispatcher("myinfo.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
