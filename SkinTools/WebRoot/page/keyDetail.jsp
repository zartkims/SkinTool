<%@page import="com.cpl.constants.NetworkConstants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'keyDetail.jsp' starting page</title>

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
	This is my JSP page.
	<br>
	<form name="upload" action="/SkinTools/uploadfiles" method="post"
		enctype="multipart/form-data">
		<% 
			String keyName = (String)request.getAttribute(NetworkConstants.PARAMS_KEY_SECTION_NAME);
			if ("".equals(keyName)) return;//go back
			
		%>
		<h1><%=keyName %> 按键</h1>
		<h3>1080</h3>
		<p>普通状态<input type="file" name="1080"> 当前使用文件为:
		<p>按下状态<input type="file" name="1080"> 
		<p>不可点状态(非必须)<input type="file" name="1080"> 
		
		<h3>720</h3>
		<p>普通状态<input type="file" name="720"> 
		<p>按下状态<input type="file" name="720"> 
		<p>不可点状态(非必须)<input type="file" name="720">
		 
				
		<h3>480</h3>
		<p>普通状态<input type="file" name="480"> 
		<p>按下状态<input type="file" name="480"> 
		<p>不可点状态(非必须)<input type="file" name="480">
		
		<p><input type="submit"	value="保存" />
	</form>
</body>
</html>
