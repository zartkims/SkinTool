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
			String keyName = (String)request.getParameter(NetworkConstants.PARAMS_KEY_SECTION_NAME);
			System.out.println("keyName : " + keyName);
			if ("".equals(keyName)) return;//go back
			
			INIFile img1080INI = (INIFile)session.getAttribute(NetworkConstants.SESSION_1080_IMAGE);
			INIFile img720INI = (INIFile)session.getAttribute(NetworkConstants.SESSION_720_IMAGE);
			INIFile img480INI = (INIFile)session.getAttribute(NetworkConstants.SESSION_480_IMAGE);
			
			System.out.println("img 1080 ini : " + img1080INI);
			/* if (img1080INI != null && img1080INI.getAllSectionNames() != null) {
				for(String section : img1080INI.getAllSectionNames()) {
					System.out.println("1080 section " + section);
					for (String key : img1080INI.getProperties(section).keySet()) {
						INIProperty pro = img1080INI.getProperties(section).get(key);
						System.out.println(key + " ~~"+pro.getPropName()+"="+pro.getPropValue());
						
					}
				}
				
			} */
			//if (img1080INI == null) img1080INI
			//session.getAttribute(sessionKey) == null ? new INIFile(path) : (INIFile) session.getAttribute(sessionKey);
		%>
		
		<form name="upload" action="/SkinTools/uploadkeybgs?keySectionName=<%=keyName %>" method="post"
			enctype="multipart/form-data" onsubmit="return setTheScale()">
		<p><input type="button" onclick="setTheScale()"	value="保存并返回" height="300" />
		<h1><%=keyName %> 按键</h1>
		<p>ps：如果是需要拉伸的.9图则需要配置拉伸区域，拉伸区域配置格式为 [左,右,上,下] 例如 10,20,0,30
		<h3>1080</h3>
		<p>普通状态<input type="file" name="1080">  
		拉伸区域：<input type="text" id="N1080" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))"> 
		之前使用文件为:<%=img1080INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.N)%>
		<p>按下状态<input type="file" name="1080">
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_P_1080 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))"> 
		 之前使用文件为:<%=img1080INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.P)%>
		<p>不可点状态(非必须)<input type="file" name="1080"> 
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_D_1080 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))"> 
		之前使用文件为:<%=img1080INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.D)%>
		
		<h3>720</h3>
		<p>普通状态<input type="file" name="720"> 
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_N_720 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img720INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.N)%>
		<p>按下状态<input type="file" name="720"> 
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_P_720 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img720INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.P)%>
		<p>不可点状态(非必须)<input type="file" name="720">
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_D_720 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img720INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.D)%>
		 
				
		<h3>480</h3>
		<p>普通状态<input type="file" name="480"> 
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_N_480 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img480INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.N)%>
		<p>按下状态<input type="file" name="480"> 
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_P_480 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img480INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.P)%>
		<p>不可点状态(非必须)<input type="file" name="480">
		拉伸区域：<input type="text" id="<%=NetworkConstants.PARAMS_D_480 %>" onkeyup="value=value.replace(/[^,\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^,\d]/g,''))">
		之前使用文件为:<%=img480INI.getStringProperty(INICode.SECTION_IMG_PRE + keyName, INICode.D)%>
		
		
		<p><input type="submit"	value="保存并返回" height="300" />
		
		
		
		
	</form>
	
	<script type="text/javascript">
	 	function setTheScale() {
	 <%-- 		<%
	 			String name = (String) request.getSession().getAttribute(NetworkConstants.SESSION_KEY_CUR_SKIN);
				System.out.println("in here !!! name~~ : " + name);
				if (name != null && "".equals(name)) {
					%>
					alert("请先结束之前"+name+"的编辑 !")
	 				return false;
					<%
				}
			%> --%>
	 		var N1080 = document.getElementById("N1080").value;
	 		var P1080 = document.getElementById("P1080").value;
	 		var D1080 = document.getElementById("D1080").value;
	 		
	 		var N720 = document.getElementById("N720").value;
	 		var P720 = document.getElementById("P720").value;
	 		var D720 = document.getElementById("D720").value;
	 		
	 		var N480 = document.getElementById("N480").value;
	 		var P480 = document.getElementById("P480").value;
	 		var D480 = document.getElementById("D480").value;
	 		
	 		
	 		var action = "/SkinTools/uploadkeybgs?keySectionName=<%=keyName %>";
	 		action += "&N1080=" + N1080;
	 		action += "&P1080=" + P1080;
	 		action += "&D1080=" + D1080;
	 		action += "&N720=" + N720;
	 		action += "&P720=" + P720;
	 		action += "&D720=" + D720;
	 		action += "&N480=" + N480;
	 		action += "&P480=" + P480;
	 		action += "&D480=" + D480;
	 		
	 		document.upload.action=action;      
   			document.upload.submit();	
	 		return false;
	 	}
  	</script>
</body>
</html>
