<%@page import="com.bugs.domain.Book"%>
<%@page import="com.bugs.domain.ShoppingCart"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
boolean login=false;
if(session.getAttribute("customer")==null){
	login=false;
}else{
	login=true;
}
List<ShoppingCart> shoppingCarts = (List<ShoppingCart> )session.getAttribute("shoppingCarts");
List<Book> books = (List<Book>)session.getAttribute("books");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="css/shoppingCart.css" rel="stylesheet">

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
						<a href="communicateadmin.jsp">联系管理员</a>
					</div>
				</div>
			</div>
		</div>
		</header>
		<div class="container">
		<div class="card rounded-0 border-left-0 border-right-0 content" >
				<div class="card-body">
					<h5 class="card-title bg-primary"><img src="images/cart.svg"width="20px" />我的购物车</h5>
						<table class="table">
						  <tbody>
						  	<thead>
							    <tr>
							      <th scope="col" class="w-25"></th>
							      <th scope="col" class="w-25"></th>
							      <th scope="col">数量</th>
							      <th scope="col">合计</th>
							      <th scope="col"></th>
							    </tr>
							 </thead>
							<% for(int i=0;i<shoppingCarts.size();i++){ %>
							 	<tr>
							      <td scope="row"><%=books.get(i).name %></td>
							      <td><img src="<%=books.get(i).imgurl %>" width="100px" /></td>
							      <td><%=shoppingCarts.get(i).num %></td>
							      <td><%=books.get(i).price %></td>
							      <td><input type="checkbox"  /></td>
							    </tr>
							 <%} %>
						  </tbody>	
						</table>
			    </div>
			</div>
			</div>
  </body>
  <script src="js/bootstrap/js/jquery.js"></script>
    <!-- popper.js-->
    <script src="js/bootstrap/js/popper.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
</html>
