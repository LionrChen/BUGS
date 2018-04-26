<%@page import="com.bugs.domain.Customer"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Customer customer=new Customer();
customer=(Customer)session.getAttribute("customer");
boolean login=false;
if(session.getAttribute("customer")==null){
	login=false;
}else{
	login=true;
}

%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title>${type }</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="css/index.css" rel="stylesheet">
		<style>
			
		</style>
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
		<div class="logo">
				<div class="logo_img">
					Store
				</div>
				<div class="cart">
					<a href="viewMyCartServlet" >Cart-￥<span id="cartItemsPayment">-</span></a>
					<div class="num" id="cartItemsNum">-</div>
				</div>
				
			</div>
		</header>
		<div class="container">
			<div class="content p-4">
				<nav aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="indexServlet">图书分类</a></li>
				    <li class="breadcrumb-item active" aria-current="page">${type }</li>
				  </ol>
				</nav>
				<p:forEach items="${books }" var="book" varStatus="vs">
					<div class="card" style="width: 18rem;">
					  <img class="card-img-top" src="${book.imgurl }" alt="Card image cap">
					  <div class="card-body">
					    <a class="card-text" href="bookDetailServlet?bookId=${book.id }">${book.name }</a>
					  </div>
					</div>
				</p:forEach>
			</div>
		</div>
		
		<footer>
			
		</footer>
		<script src="js/bootstrap/js/jquery.js"></script>
		<script type="text/javascript" src="js/ajax.js" ></script>
		<script src="js/rgbaster.js"></script>
		
		<script type="text/javascript" src="js/index.js" charset="gbk"></script>
	</body>
</html>

