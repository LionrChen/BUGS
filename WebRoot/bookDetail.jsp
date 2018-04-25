<%@page import="com.bugs.domain.Book"%>
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
Book book = (Book)session.getAttribute("book");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${book.name }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bookDetail.css" rel="stylesheet">

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
						<a href="myorder.jsp">我的订单</a>
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
			<div class="row content p-3">
				<div class="col-5">
					<div class="pt-3">
						<img src="${book.imgurl }"  class="img-fluid"/>
					</div>
				</div>
				<div class="col-7 bookInfo pt-5">
					<h5>${book.name }</h5>
					<p>${book.description }</p>
					<p>分类:<span>${book.category }</span></p>
					<p>剩余库存:<span>${book.pnum }</span></p>
					<p>价格:<span>${book.price }￥</span></p>
					
					<form action="directPurchaseServlet" method="post">
						<input style="display: none;" value="${book.id }" name="bookId" id="bookId"/>
						<label for="pnum">数量:</label>
						<input type="text" value="1" id="pnum" name="pnum"/>
						<p>
							<button type="submit" class="btn btn-primary">立刻购买</button>
							<a  class="btn bg-white border-primary"  id ="addToCart">加入购物车</a>
						</p>
						
					</form>
					
				</div>
				
			</div>
			
		</div>
  </body>
  <script src="js/bootstrap/js/jquery.js"></script>
    <!-- popper.js-->
    <script src="js/bootstrap/js/popper.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script src="js/bookDetail.js"></script>
</html>
