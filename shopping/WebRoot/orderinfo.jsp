<%@ page language="java" import="java.util.*,com.control.*,com.model.email.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
orderinfo oif=(orderinfo)request.getAttribute("orderinfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderinfo.jsp' starting page</title>
    
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
   <table width="80%" align=center border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td><table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="9" align="center">订单详细信息</td>
        </tr>
      <tr>
        <td>订单号</td>
        <td>收货人</td>
        <td>收货地址</td>
        <td>邮编</td>
        <td>电话</td>
        <td>总价</td>
        <td>用户名</td>
        <td>电子邮件</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><%=oif.getOrdersId() %></td>
       <td><%=oif.getRealname() %></td>
       <td><%=oif.getAddress() %></td>
        <td><%=oif.getPostcode() %></td>
         <td><%=oif.getPhone() %></td>
        <td><%=oif.getTotalPrice() %></td>
         <td><%=oif.getUsername() %></td> 
         <td><%=oif.getEmail() %></td>
        <td><a href=''>查看详情</a></td>
      </tr>
      <tr>
        <td colspan="9">我们以给你的账户发送了一个邮件，请注意查收！！！！！！！</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td><jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
  </body>
</html>
