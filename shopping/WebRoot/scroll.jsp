<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /> 
<title>图片滚动</title> 
</head> 
<body> 
<style type="text/css"> 
<!-- 
#demo { 
background: #FFF; 
overflow:hidden; 
border: 1px dashed #CCC; 
width: 1100px; 
} 
#demo img { 
border: 3px solid #F2F2F2; 
} 
#indemo { 
float: left; 
width: 800%; 
} 
#demo1 { 
float: left; 
} 
#demo2 { 
float: left; 
} 
--> 
</style> 
<div id="demo" width=100%> 
<div id="indemo"> 
<div id="demo1"> 
<a href="#"><img src="images/scroll/1.jpg" border="0" /></a> 
<a href="#"><img src="images/scroll/2.jpg" border="0" /></a> 
<a href="#"><img src="images/scroll/3.jpg" border="0" /></a> 
<a href="#"><img src="images/scroll/4.jpg" border="0" /></a> 
<a href="#"><img src="images/scroll/5.jpg" border="0" /></a> 
<a href="#"><img src="images/scroll/6.jpg" border="0" /></a> 
</div> 
<div id="demo2"></div> 
</div> 
</div> 
<script> 
<!-- 
var speed=10; 
var tab=document.getElementById("demo"); 
var tab1=document.getElementById("demo1"); 
var tab2=document.getElementById("demo2"); 
tab2.innerHTML=tab1.innerHTML; 
function Marquee(){ 
if(tab2.offsetWidth-tab.scrollLeft<=0) 
tab.scrollLeft-=tab1.offsetWidth 
else{ 
tab.scrollLeft++; 
} 
} 
var MyMar=setInterval(Marquee,speed); 
tab.onmouseover=function() {clearInterval(MyMar)}; 
tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)}; 
--> 
</script> 
</body> 
</html> 

