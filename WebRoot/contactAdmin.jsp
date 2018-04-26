<%@page import="com.bugs.domain.Message"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
boolean login=false;
if(session.getAttribute("customer")==null){
	login=false;
}else{
	login=true;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>联系管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="css/contactAdmin.css" rel="stylesheet">

  </head>
  
  <body>
    <header>
		<div class="basic_info">
			<div class="basic_info_bin">
				<div class="refrash">
					<a href="indexServlet">书店首页</a>
				</div>
				<div class="anystate">
					<div class="stateinfo">
					<%if(login){ %>
					<span>你好，<a href="customerInfoServlet">${customer.username }</a>|&nbsp;<a href="customerExitServlet">退出</a></span>
						
					<%}else{ %>
						<span>你好，游客！</span><a href="userLogin.jsp">如果已注册，请登录</a>
						
					<%} %>
					</div>
					<div class="stateinfo">
						<a href="viewMyOrdersServlet">我的订单</a>
					</div>
					<div class="stateinfo">
						<a href="importantpeople.jsp">书城会员</a>
					</div>
					<div class="stateinfo">
						<a href="contactAdmin.jsp">联系管理员</a>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="container">
			<div class="content p-4">
				<h6 class="bg-primary title"><img alt="" src="images/message.svg" width="20px">留言</h6>
				<form action="addMessageServlet" method="post" class="p-3">
				  <div class="form-group">
				    <label for="title">标题</label>
				    <input type="text" class="form-control" id="title" value="${message.title }"  placeholder="标题不超过40个字符" name="title">
				  </div>
				 
				  <div class="form-group">
				    <label for="content">留言内容</label>
				    <textarea class="form-control" id="content" rows="4"  name="content">${message.content }</textarea>
				  </div>
				  
			  	  <button class="btn btn-primary" type="submit">提交</button>
			  	  <a class="btn btn-primary" id="back" href="indexServlet">返回</a>
				  
				</form>
			</div>
		</div>
  </body>
</html>
