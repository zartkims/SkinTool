<%@page import="com.cpl.bean.INIFile.INIProperty"%>
<%@page import="com.cpl.constants.INICode"%>
<%@page import="com.cpl.constants.NetworkConstants"%>
<%@ page language="java" 
	import="java.util.*" 
	import="java.io.*" 
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
			
			INIFile img1080INI = (INIFile)session.getAttribute(NetworkConstants.SESSION_1080_IMAGE);
			
			String preview = SkinFolders.get_SKIN_ROOT_PREVIEW(skinName);
			File file = new File(preview);
			boolean isHasPreview = file.exists();
			String prePreview = isHasPreview ? "已经有一个预览图了" : "目前没有任何预览图请务必上传一个";
		%>
		
		<form name="upload" action="/SkinTools/uploadKbBg" method="post"
			enctype="multipart/form-data">
		<p><input type="submit"	value="保存" height="300" />
		<h3>背景</h3>
		<p>键盘背景<input type="file" name="keyboardBg"> 之前使用文件为:<%=img1080INI.getStringProperty(INICode.SECTION_KB_BG, INICode.IMAGE)%>
		<p>候选背景<input type="file" name="candBg"> 之前使用文件为:<%=img1080INI.getStringProperty(INICode.SECTION_BG_CANDS, INICode.IMAGE)%>
		<p>预览图背景<input type="file" name="previewBg"> <%=prePreview%>
		
		
	</form>
</body>
</html>
