<%@page import="com.cpl.bean.INIFile.INIProperty"%>
<%@page import="com.cpl.constants.INICode"%>
<%@page import="com.cpl.constants.NetworkConstants"%>
<%@ page language="java" import="java.util.*" 
	import="com.cpl.bean.*" 
	import="com.cpl.tool.manager.*" 
	pageEncoding="UTF-8"%>
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
	退出该页面前记得保存点保存哦！！否则将不生效
	
	<br>

		<% 
			//HttpSession session = request.getSession();
			Object nameObj = session.getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
  			String skinName = nameObj != null ? (String) nameObj : "";
  			System.out.println("skinName : " + skinName);
			
			INIFile skinINI = (INIFile)session.getAttribute(NetworkConstants.SESSION_SKIN_INI);
	
		%>
		
		<form name="upload" action="/SkinTools/uploadFonts" method="post"
			enctype="multipart/form-data">
		<p><input type="submit"	value="保存" height="300" />
		<h3>普通字母按键font</h3>
		<p>普通状态<input type="file" name="normalFont"> 之前使用文件为:<%=skinINI.getStringProperty(INICode.SECTION_TEXT_DEF_DOUBLE_STYLE, INICode.TYPEFACE)%>
		
		<h3>额外iconFont时间关系暂不支持</h3>
		
	</form>
</body>
</html>
