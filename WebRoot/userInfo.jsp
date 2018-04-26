<%@page import="com.bugs.domain.Book"%>
<%@page import="com.bugs.domain.ShoppingCart"%>
<%@page import="com.bugs.domain.order"%>
<%@page import="com.bugs.domain.Wallet"%>
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
Wallet wallet = (Wallet)session.getAttribute("wallet");
List<ShoppingCart> shoppingCarts = (List<ShoppingCart> )session.getAttribute("shoppingCarts");
List<Book> books = (List<Book>)session.getAttribute("cartBooks");
List<Book> toBePaidOrderBooks = (List<Book>)session.getAttribute("toBePaidOrderBooks");
List<Book> paidOrderBooks = (List<Book>)session.getAttribute("paidOrderBooks");
List<order> toBePaidOrders = (List<order>)session.getAttribute("toBePaidOrders");
List<order> paidOrders = (List<order>)session.getAttribute("paidOrders");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/userinfo.css" rel="stylesheet">
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
					<span>你好，<a href="userInfo.jsp">${customer.username }</a></span>
						
					<%}else{ %>
						<span>你好，游客！</span><a href="userLogin.jsp">如果已注册，请登录</a>
						|&nbsp;<a href="customerExitServlet">退出</a>
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
	<div class="container content ">
			<div class="row p-3">
				<div class="col-md-3 headerImg">
					<img src="images/headerImg.svg"  width="80px" class="bg-primary rounded-circle align-middle"/>
				</div>
				<div class="col-md-9 userInfo">
					<p>${customer.username }</p>
					<p>这个人很懒，什么都没留下...</p>
					<p>会员:<span>还不是会员<a href="javascript:;">加入会员</a></span></p>
					<p>钱包余额:<span>${wallet.balance }</span>￥
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
						  充值
						</button>
						
						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="exampleModalLabel">钱包充值</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						        
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
						        <button type="button" class="btn btn-primary">确认</button>
						      </div>
						    </div>
						  </div>
						</div>
					</p>
				</div>
			</div>
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item" aria-current="page">
			    	<a href="viewMyCartServlet">
						<img src="images/cart.svg"width="20px" />我的购物车
					</a>
				</li>
			  </ol>
			</nav>
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item" aria-current="page">
			    	<a href="viewMyOrdersServlet">
					<img src="images/order.svg" width="20px"/>我的订单
					</a>
				</li>
			  </ol>
			</nav>
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item" aria-current="page">
			    	<a href="viewMyMessageServlet">
					<img src="images/order.svg" width="20px"/>我的留言
					</a>
			    </div>
				</li>
			  </ol>
			</nav>
			
		</div>
  </body>
  <script src="js/bootstrap/js/jquery.js"></script>
    <!-- popper.js-->
    <script src="js/bootstrap/js/popper.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
</html>
