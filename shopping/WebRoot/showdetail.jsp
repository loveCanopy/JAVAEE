<%@ page language="java" import="java.util.*,com.control.goodsbean" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 goodsbean gb=(goodsbean)request.getAttribute("goodsinfo");
   %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showdetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script>
    
    function returnhall(){
    window.open("index.jsp","_self");
    
    }
    function showid(goodsid){
    
    window.open("showmycartservlet?type=add&goodsid="+goodsid,"_self");
    }
    
    </script>
  </head>
  
  <body>
  <table width="80%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="26" colspan="2">
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td width="21%" style="width: 186px; "><img src="images/<%=gb.getPhoto() %>" width="187" height="250" /></td>
    <td width="79%"><%=gb.getGoodsintro() %></td>
  </tr>
  <tr>
    <td height="31" colspan="2"><input type="submit" onclick="showid(<%=gb.getGoodsid() %>)" name="Submit" value="购买" />
    <input type="submit"  onclick='returnhall();' name="Submit2" value="返回购物大厅" /></td>
  </tr>
  <tr>
    <td colspan="2">
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
  </body>
</html>
