<%@ page language="java" import="java.util.*,com.control.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
userbean ub=(userbean)request.getSession().getAttribute("userinfo");
ArrayList a1=(ArrayList)request.getAttribute("mycartinfo");
mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myinfo.jsp' starting page</title>
    
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
   <table width="80%" height="412" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="50"> 
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td height="201"><table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" align="center">个人信息</td>
        </tr>
      <tr>
        <td width="50%" align="center">用户名id</td>
        <td width="50%"><%=ub.getUserid() %></td>
      </tr>
      <tr>
        <td align="center" valign="middle">用户名</td>
        <td><%=ub.getUsername() %></td>
      </tr>
      <tr>
        <td align="center">邮箱</td>
        <td><%=ub.getEmail() %></td>
      </tr>
      <tr>
        <td align="center">地址</td>
        <td><%=ub.getAddress() %></td>
      </tr>
      <tr>
        <td align="center">电话</td>
        <td><%=ub.getPhone() %></td>
      </tr>
      <tr>
        <td align="center">真实姓名</td>
        <td><%=ub.getRealname() %></td>
      </tr>
      <tr>
        <td align="center">会员级别</td>
        <td><%=ub.getGrade() %></td>
      </tr>
      <tr>
        <td height="15" align="center">邮编</td>
        <td><%=ub.getPostcode() %></td>
      </tr>
      <tr>
        <td height="71" colspan="2" bgcolor="#66FFCC">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="129"><table width="100%" height="128" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td height="31" colspan="4" align="center">我的购物信息</td>
        </tr>
      <tr>
        <td height="37">编号</td>
        <td>名字</td>
        <td>数量</td>
        <td>价格</td>
      </tr>
       <% 
         for(int i=0;i<a1.size();i++){
              goodsbean gb=( goodsbean)a1.get(i);
         %>
      <tr>
        <td height="53"><%=gb.getGoodsid() %></td>
        <td><%=gb.getGoodsname() %></td>
        <td><%=mcb.getnumbyid(gb.getGoodsid()+"") %>;</td>
        <td><%=gb.getGoodsprice() %></td>
      </tr>
       <% 
      
    }  %>
    </table></td>
  </tr>
  <tr><td align='right'>您一共购买了<%=mcb.getallprice() %>的商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="showmycartservlet?type=show" >修改购物车信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="orderclservlet">生成订单</a></td>
  </tr>
  <tr>
    <td>
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
  </body>
</html>
