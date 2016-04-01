<%@ page language="java" import="java.util.*,com.control.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/my.css">
        <style>a{TEXT-DECORATION:none}</style>
  </head>
  
  <body>
  <table width="100%" border="1" cellpadding="0" cellspacing="0" class="abc">
  <tr>
    <td colspan="2" bgcolor="#CCCCCC"></td>
  </tr>
  <tr>
    <td width="83%"><img src="images/1.gif" width="966" height="300" /></td>
    <td width="17%" align="center"><table width="100%" height="303" border="1" cellpadding="0" cellspacing="0">
      <tr>
       <% 
       userbean ub=(userbean)request.getSession().getAttribute("userinfo");
       if(ub!=null){
      out.println( "<td width='50%' align='center'><a href='login.jsp' >"+ub.getRealname()+"</a></td>");
       }
       else{
        out.println("<td width='50%' align='center'><a href='login.jsp' >登录</a></td>");
        }
        %>
      </tr>
      <tr>
        <td height="151" align="center"><a href="showmycartservlet?type=show" >我的购物车</a></td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td colspan="2" bgcolor="#999999">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2"><table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td width="11%">&nbsp;</td>
        <td width="11%" align="center" class="navi"><a href='index.jsp'>首页</a></td>
        <td width="11%">&nbsp;</td>
        <td width="11%" align="center" class="navi"><a href='zxdy.jsp'>最新电影</a></td>
        <td width="11%">&nbsp;</td>
        <td align="center" class="navi"><a href='jddy.jsp'>经典电影</a></td>
        <td width="11%">&nbsp;</td>
        <td align="center" class="navi"><a href='about.jsp'>关于我们</a></td>
        <td width="11%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
