<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<link rel="stylesheet" type="text/css"
			href="extj/resources/css/ext-all.css">
		<script type="text/javascript" src="extj/ext-base.js"></script>
		<script type="text/javascript" src="extj/ext-all.js"></script>
		<script type="text/javascript" src="extj/DWRProxy.js"></script>
		<script type="text/javascript" src="extj/ext-lang-zh_CN.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" charset="UTF-8">
	Ext.onReady(function() {

			/**************************窗口***********************************/
			//顶部
			var top = new Ext.Panel(
					{
						frame : true,
						region : "north",
						title : "",
						height : 66,
						items : [ {
							columnWidth : 99,
							bodyStyle : 'padding-top:15px',
							html : '<h1>欢迎光临点餐系统</h1>'
						} ]
					});
			var left = new Ext.tree.TreePanel(
					{
						margins : '6 0 6 6',
						cmargins : '6 6 6 6',
						title : '菜单列表',
						region : 'west',
						width : 200,
						border : false,
						autoScroll : true,
						collapsible : true,
						split : true,
						loader : new Ext.tree.TreeLoader( {
							dataUrl : 'menu.jsp'
						}),
						root : new Ext.tree.AsyncTreeNode( {
							text : '点餐系统',
							hasChildren : true,
							id : 'root'
						}),
						listeners : {
							click : function(n) {
								var url = n.attributes.url;
								var id = n.attributes.id;
								var p = center.getItem(id);
								if (url) {
									if (p) {
										center.setActiveTab(p);
									} else {
										p = new Ext.Panel(
												{
													title : n.attributes.text,
													closable : true,
													autoScroll : true,
													layout : 'fit',
													id : id,
													items : [ {
														html : "<iframe id=\"ifmain\" width=\"100%\" height=\"100%\" scrolling=\"auto\" frameborder=\"0\" src=\""
																+ url
																+ "\" ></iframe>"
													} ]
												});
										center.add(p);
										center.setActiveTab(p);
									}
								}
							}
						}
					});
			left.root.expand();//展开根节点

			var center = window.ptab = new Ext.TabPanel(
					{
						margins : '6 6 6 0',
						region : 'center',
						id : 'main',
						enableTabScroll : true,
						autoScroll : true
					});
			new Ext.Viewport( {
				layout : 'border',
				items : [ top, center, left ]
			});
		})
</script>
	</head>

	<body>
		<!-- 
  	<h2>ExtJS Demo</h2>
  	<a href="form.jsp">添加</a >&nbsp;&nbsp;<a href="list/list.html">用户列表</a>&nbsp;&nbsp;<a href="treePanel.jsp">树</a>
    <h2>This is my JSP page.</h2> -->
	</body>
</html>
