<%@page import="com.cpl.constants.INICode"%>
<%@ page language="java" import="java.util.*" import="com.cpl.constants.NetworkConstants" pageEncoding="UTF-8"%>
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
  	<% 
		Object nameObj = request.getSession().getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
  		String skinName = nameObj != null ? (String) nameObj : "";
  		if (null == skinName || "".equals(skinName)) {
  			System.out.println("skin is null");
			response.sendRedirect("start");
  		} 
  	%>
  	
  	<% 
  		Object needNotifyObj = request.getAttribute(NetworkConstants.PARAMS_KEY_NOTI_CLOSE_LAST_SKIN);
  		System.out.println("needNotifyObj : " + needNotifyObj);
  		if (needNotifyObj != null && ((boolean)(needNotifyObj))) {
 				System.out.println("need notify close last");
 	%>
	 	<script type="text/javascript" language="javascript">
			alert("请先结束之前"+"的编辑 !")
		</script>	
 			
 	<% 
  		}
  	%>
	 <tr height="100">
    	<p> <a href="page/mainpage.jsp">返回主页面</a>
    </tr>
    
	<h2>当前皮肤为 : <%=skinName %></h2>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_KEY%>">替换 字母 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_FUNC_KEY%>">替换 功能 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_SPACE_KEY%>">替换 空格 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_ENTER_KEY%>">替换 回车 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_BACK_SAPCE_KEY%>">替换 删除 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_SHIFT_KEY%>">替换 shift 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_123_KEY%>">替换 ？123 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_PERIOD_KEY%>">替换 逗号 按键背景</a>
    <p><a href="page/keyDetail.jsp?keySectionName=<%=INICode.SECTION_DOT_KEY%>">替换 句号 按键背景</a>
    
   
  </body>
</html>
