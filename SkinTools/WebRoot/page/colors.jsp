<%@page import="com.cpl.constants.INICode"%>
<%@page import="com.cpl.bean.INIFile.INIProperty"%>
<%@page import="com.cpl.constants.NetworkConstants"%>
<%@ page language="java" import="java.util.*"
	import="com.cpl.bean.*" 
	import="com.cpl.tool.manager.*"
	import="com.cpl.constants.NetworkConstants" pageEncoding="UTF-8"%>
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
		
		INIFile colorINI = (INIFile)session.getAttribute(NetworkConstants.SESSION_COLORS_INI);
		
  	%>
  	
	 <tr height="100">
    	<p> <a href="page/mainpage.jsp">返回主页面</a>
    </tr>
    
	<h2>当前皮肤为 : <%=skinName %></h2>
	
   	<form action="colors" method="post">
  		<p><input type="submit"	value="保存并返回" height="300" />
   	
  	 	<p>主色系：# <input type="text" name="<%=INICode.COLOR_FIR %>" onkeyup="value=value.replace(/[^\da-f|A-F]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
   			之前颜色为 ：<%=colorINI.getStringProperty(INICode.COLORS, INICode.COLOR_FIR)%>
 		<p>工具条Tab选中颜色：# <input type="text" name="<%=INICode.COLOR_TAB_SELECT %>" onkeyup="value=value.replace(/[^\da-f|A-F]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
 		   	之前颜色为 ：<%=colorINI.getStringProperty(INICode.COLORS, INICode.COLOR_TAB_SELECT)%>
 		
 		<p>候选高亮颜色：# <input type="text" name="<%=INICode.COLOR_CANDS_HL %>" onkeyup="value=value.replace(/[^\da-f|A-F]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
 		   	之前颜色为 ：<%=colorINI.getStringProperty(INICode.COLORS, INICode.COLOR_CANDS_HL)%>
 		
    	
    	<p><input type="submit"	value="保存并返回" height="300" />
    </form>
    
  
  </body>
</html>
