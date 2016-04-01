<%@ page language="java" import="java.util.*,com.control.goodsbean,com.control.mycartbo" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList a1=(ArrayList)request.getAttribute("mycartinfo");
mycartbo mcb=(mycartbo)request.getSession().getAttribute("mycart"); 

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showmycart.jsp' starting page</title>
    
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
  <form action='showmycartservlet?type=update' method='post'>
  <table width="80%" height="209" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="39">
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
    <td height="128"><table width="100%" height="141" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td height="42"><table width="100%" border="1" cellspacing="0" cellpadding="0">
          <tr>
            <td width="17%" height="37">编号</td>
            <td width="16%">名称</td>
            <td width="17%">单价</td>
            <td width="50%">数量</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="35"><table width="100%" border="1" cellspacing="0" cellpadding="0">
         <% 
         for(int i=0;i<a1.size();i++){
              goodsbean gb=( goodsbean)a1.get(i);
         %>
         
         <tr>
            <td width="17%" height="57"><%=gb.getGoodsid() %></td>
            <td width="16%"><%=gb.getGoodsname() %></td>
            <td width="17%"><%=gb.getGoodsprice() %></td>
            <td width="16%"><input type='hidden' name='goodsid' value=<%=gb.getGoodsid() %>><input type='text' name='goodsnum' value=<%=mcb.getnumbyid(gb.getGoodsid()+"") %>></td>
            <td width="18%"><a href='showmycartservlet?type=delete&goodsid=<%=gb.getGoodsid() %>'>删除</a></td>
            <td width="16%"><a href='showdetailservlet?type=show&id=<%=gb.getGoodsid()%>'>查看</a></td>
          </tr>
         
       <%  
         }
         %>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" height="34" border="1" cellpadding="0" cellspacing="0">
          <tr>
            <td align='right' height="32"><a href='showmycartservlet?type=clear'>删除所有书籍</td>
            <td><input type='submit'  value='修改数量' ></td>
          </tr>
        </table></td>
      </tr>
     <table align='right'> <tr>
        <a>你一共购买了<%=mcb.getallprice() %>元的商品</a>
        <td height="22"><a href="zxdy.jsp">继续购买</a></td>
        <td height="22"><a href="shoppingservlet">下一步</a></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
</form>
  </body>
</html>
