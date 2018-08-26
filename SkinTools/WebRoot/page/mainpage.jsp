<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mainpage.jsp' starting page</title>
    
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
  	<% String skinName = request.getParameter("cur_skin_name"); 
  		System.out.println("the skin : " +skinName);
  	%>
  	<h2>当前皮肤为 : ${requestScope.cur_skin_name}</h2>
    <p><a href="page/fonts.jsp?skin_name=${requestScope.cur_skin_name}">替换iconfont和字体</a>
    <p><a href="fonts/backgrounds.jsp">替换各种背景</a>
    <p><a href="fonts/foregrounds.jsp">替换各种前景</a>
    <p><a href="fonts/colors.jsp">配置色系</a>
  </body>
</html>
