<%@page import="com.bugs.domain.Message"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>all messages</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  </head>
  
  <body>
  	<div class="container">
    	<div class="list-group list-group-flush">
			<p:forEach items="${messages }" var="message" varStatus="vs">
				<div class="list-group-item">
					<p><a href="reviewMessageServlet?messageId=${message.id }">${message.title }</a><a href="deleteMessageServlet?messageId=${message.id }" class="float-right">删除</a></p>
					<p>${message.content }</p>
				</div>
			</p:forEach>
		</div>
	</div>
  </body>
  <script src="js/bootstrap/js/jquery.js"></script>
    <!-- popper.js-->
    <script src="js/bootstrap/js/popper.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
</html>
