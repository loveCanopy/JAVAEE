<%@ page language="java" import="java.util.*,com.control.goodsbean,com.control.goodsbeanbo" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'zxdy.jsp' starting page</title>
    
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
  <%   goodsbeanbo gbo=new goodsbeanbo();
       int pageNow=1;
       String pagenow=request.getParameter("pageNow");
       if(pagenow!=null){
         pageNow=Integer.parseInt(pagenow);
       }
       ArrayList a1=gbo.getpage(8,pageNow);
       int pageCount=gbo.pageCount(8);
     %>
  <table width="80%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="4">
    <jsp:include page="head.jsp"></jsp:include></td>
  </tr>
  <tr>
   
      <% 
         int time=0;
         for(int i=0;i<2;i++){
         for(int j=0;j<4;j++){
          goodsbean gb=new goodsbean();
         if(time>=a1.size()){
         //书不够
         gb.setGoodsid(20);
         gb.setGoodsname("空");
         gb.setGoodsintro("空");
         gb.setPhoto("876.jpg");
         gb.setGoodsprice(0);
         }else{
          gb=(goodsbean)a1.get(time);
          time++;
}
   %> <td width="250" height="250"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td width="187" rowspan="3"><img src=images/<%=gb.getPhoto() %>></td>
        <td width="31%"><%=gb.getGoodsname() %></td>
      </tr>
      <tr>
        <td><%=gb.getGoodsprice() %></td>
      </tr>
      <tr>
        <td><a href="showdetailservlet?type=show&id=<%=gb.getGoodsid() %>">详细信息</a></td>
      </tr>
    </table></td>  
        <%  }
         if(i==0){
       %>  
         <tr>
    <td colspan="4" bgcolor="#00FF66">&nbsp;</td>
  </tr>
         <% }
         
         
         }
      %>
      
 <tr>
    <td colspan="4" align="center">
    <%
    for(int i=1;i<=pageCount;i++){
    %>
    <a href="showdetailservlet?type=fenye&pageNow=<%=i%>">第<%=i %>页</a> 
   <%  
    }
    %>
    </td>
  </tr>
    
  <tr>
    <td colspan="4">
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
  </body>
</html>
