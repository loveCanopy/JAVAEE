<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'about.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table width="80%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td><img src="images/1-14030G549203c.jpg" width="1090" height="653" /></td>
  </tr>
  <tr>
    <td>
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
  </body>
</html>
