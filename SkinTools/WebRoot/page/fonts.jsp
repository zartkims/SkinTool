<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传、下载文件</title>
<style type="text/css">
#filedrag {
	display: none;
	font-weight: bold;
	text-align: center;
	padding: 1em 0;
	margin: 1em 0;
	color: #555;
	border: 2px dashed #555;
	border-radius: 7px;
	cursor: default;
}
 
#filedrag.hover {
	color: #f00;
	border-color: #f00;
	border-style: solid;
	box-shadow: inset 0 3px 4px #888;
}
</style>
</head>
<body>
  	<% String skinName = request.getParameter("skin_name"); 
  		System.out.println("2222the skin : " +skinName);
  	%>
  	<h2>当前皮肤为 : <%=skinName %></h2>

	<form id="upload" action="/SkinTools/upload_iconfont?skin_name=<%=skinName %>" enctype="multipart/form-data"
		method="post" onsubmit="return upLoad();">
		<p>
			<label for="fileselect">file name:</label><input multiple="true"
				type="file" id="fileselect" name="fileselect[]" />
		<div id="filedrag">或者将文件拖拽到这里</div>
		<div id="submitbutton">
			<input type="submit" value="提交">
		</div>
		
	</form>
	<div id="messages">		
	</div>
 	<%-- <% //java代码，显示服务器上可以供下载的文件
		File f = new File("temp/");
		File[] list = f.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; ++i) {
				System.out.println(list[i].getName());
				out.print("<a href='FontServerlet?filename="
						+ list[i].getName() + "'>" + list[i].getName()
						+ "</a><br/>");
			}
		}
	%>  --%>
	<script type="text/javascript">
		var upfiles = new Array();
		// getElementById
		function $id(id) {
			return document.getElementById(id);
		}
 
		// output information
		function Output(msg) {
			var m = $id("messages");
			m.innerHTML = msg + m.innerHTML;
		}
 
		// file drag hover
		function FileDragHover(e) {
			e.stopPropagation();
			e.preventDefault();
			e.target.className = (e.type == "dragover" ? "hover" : "");
		}
 
		// file selection
		function FileSelectHandler(e) {
 
			// cancel event and hover styling
			FileDragHover(e);
 
			// fetch FileList object
			var files = e.target.files || e.dataTransfer.files;
 
			// process all File objects
			for ( var i = 0, f; f = files[i]; i++) {
				ParseFile(f);
				upfiles.push(f);
			}
				
 
		}
 
		// output file information
		function ParseFile(file) {
 
			Output("<p>文件信息: <strong>" + file.name
					+ "</strong> 类型: <strong>" + file.type
					+ "</strong> 大小: <strong>" + file.size
					+ "</strong> bytes</p>");
		}
		function upLoad() {
			if (upfiles[0]) {
				var xhr = new XMLHttpRequest();   //Ajax异步传输数据
				xhr.open("POST", "/SkinTools/upload_iconfont", true);
				var formData = new FormData();
				for ( var i = 0, f; f = upfiles[i]; i++) {
					formData.append('myfile', f);
				}
				alert("send!")
				xhr.send(formData);
				xhr.onreadystatechange=function(e){
					history.go(0);  //由于这个页面还要显示可以下载的文件，所以需要刷新下页面
				}				
				return false;
			}
		}
		// initialize
		function Init() {
 
			var fileselect = $id("fileselect"), filedrag = $id("filedrag"), submitbutton = $id("submitbutton");
 
			// file select
			fileselect.addEventListener("change", FileSelectHandler, false);
 
			// is XHR2 available?
			var xhr = new XMLHttpRequest();
			if (xhr.upload) {
 
				// file drop
				filedrag.addEventListener("dragover", FileDragHover, false);
				filedrag.addEventListener("dragleave", FileDragHover, false);
				filedrag.addEventListener("drop", FileSelectHandler, false);
				filedrag.style.display = "block";
				// remove submit button
				//submitbutton.style.display = "none";
			}
 
		}
 
		// call initialization file
		if (window.File && window.FileList && window.FileReader) {
			Init();
		}
	</script>
</body>
</html>
