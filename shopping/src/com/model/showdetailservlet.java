package com.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.goodsbean;
import com.control.goodsbeanbo;

public class showdetailservlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//得到商品的ID号
		String type=request.getParameter("type");
		 goodsbeanbo gbo=new goodsbeanbo();
		if(type.equals("show")){
        String goodsid=request.getParameter("id");       
        goodsbean gb=gbo.getgb(goodsid);
        request.setAttribute("goodsinfo", gb);
        request.getRequestDispatcher("showdetail.jsp").forward(request, response);
	}
		else if(type.equals("fenye")){
			String spagenow=request.getParameter("pageNow");
			request.setAttribute("pageNow", spagenow);
			request.getRequestDispatcher("zxdy.jsp").forward(request, response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		this.doGet(request, response);
	}

}
