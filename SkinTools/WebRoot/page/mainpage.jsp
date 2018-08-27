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
			response.sendRedirect("start");
  		}
  		System.out.println("the skin : " +skinName);
  	%>
	<%
		boolean showClose = (boolean)request.getAttribute(NetworkConstants.PARAMS_KEY_NOTI_CLOSE_LAST_SKIN);         // 获取错误属性
			if(showClose) {
	%>
		<script type="text/javascript" language="javascript">
			alert("请先结束当前皮肤的编辑，点击结束编辑按钮即可"); // 弹出错误信息
		</script>
	<%
		}
	%>
	<h2>当前皮肤为 : <%=skinName %></h2>
    <p><a href="page/fonts.jsp">替换iconfont和字体</a>
    <p><a href="fonts/backgrounds.jsp">替换各种背景</a>
    <p><a href="fonts/foregrounds.jsp">替换各种前景</a>
    <p><a href="fonts/colors.jsp">配置色系</a>
    <tr height="100">
    	<p> <a href="finishEdit">结束 <%=skinName %> 编辑</a>
    </tr>
  </body>
</html>
