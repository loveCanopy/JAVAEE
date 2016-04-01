<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
   <table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="#66FFCC"><img src="images/14213903987224.png" width="1107" height="137" /></td>
  </tr>
  <tr>
    <td>
    <jsp:include page="scroll.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td><img src="images/14213903987224.png" width="1107" height="137" /></td>
  </tr>
 
</table>
  </body>
</html>
