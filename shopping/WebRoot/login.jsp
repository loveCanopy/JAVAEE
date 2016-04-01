<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    <td><table width="100%" border="1" cellspacing="0" cellpadding="0">
    <form action="loginclservlet" method='post'>
      <tr>
        <td align="right"><span class="STYLE1">用户名：</span></td>
        <td><label>
          <input name="username" type="text" size="25" />
        </label></td>
      </tr>
      <tr>
        <td align="right"><span class="STYLE1">密&nbsp;码：</span></td>
        <td><label>
          <input name="password" type="password" size="25" />
        </label></td>
      </tr>
      <tr align="center">
        <td colspan="2" align="left" valign="middle"> <label>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radiobutton" value="radiobutton" />
        </label>
          <span class="STYLE1">
         是否保存用户名和密码</span></td>
        </tr>
      <tr>
        <td align="right"><label>
          <input name="Submit" type="submit" class="STYLE1" value="登录" />
        </label></td>
        <td><label>
          <input name="Submit2" type="reset" class="STYLE1" value="重置" />
        </label></td>
      </tr>
      </form>
    </table></td>
  </tr>
  <tr>
    <td>
    <jsp:include page="tail.jsp"></jsp:include></td>
  </tr>
</table>
   
   
  </body>
</html>
