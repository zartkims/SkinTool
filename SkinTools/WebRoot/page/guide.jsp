<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    
    <form id="build_skin" action="startskin" method="post" onsubmit="return checkName()" >
        <p>开始制作全新皮肤
        <p>皮肤名字<input id="input_name"  name="skin_name" type="text" />
        <p><input type="submit" value="开始"/>
	</form>
    <font color="red">${error}</font>
	
	<tr height="200"/>
	<p>现有皮肤：
		 <% //java代码，显示服务器上已有的皮肤
			File f = new File("allSkins/");
			File[] list = f.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; ++i) {
					System.out.println(list[i].getName());
					out.print("<p>" + list[i].getName());
				}
			}
		%> 
	
	
	  <script type="text/javascript">
	 	function checkName() {
	 		var name = document.getElementById("input_name").value;
	 		if (name ==  null || name=="" || name.replace(/(^s*)|(s*$)/g, "").length ==0) {
	 			alert("皮肤名字不能为空 !")
	 			return false;
	 		}
	 		return true;
	 	}
  	</script>
  
  </body>
  

</html>
