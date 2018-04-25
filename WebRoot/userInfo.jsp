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
			<div class="card rounded-0 border-left-0 border-right-0" style="">
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
						    <tr>
						      <% for(int i=0;i<shoppingCarts.size();i++){ %>
							 	<tr>
							      <td scope="row"><%=books.get(i).name %></td>
							      <td><img src="<%=books.get(i).imgurl %>" width="100px" /></td>
							      <td><%=shoppingCarts.get(i).num %></td>
							      <td><%=books.get(i).price %></td>
							      <td><input type="checkbox"  /></td>
							    </tr>
							 <%} %>
						    </tr>
						    
						  </tbody>
						</table>
			    </div>
			</div>
			<div class="card rounded-0 border-left-0 border-right-0" style="">
				<div class="card-body">
					<h5 class="card-title bg-primary"><img src="images/order.svg" width="20px"/>待支付订单</h5>
					<table class="table">
						  <tbody>
						  	<thead>
							    <tr>
							      <th scope="col">订单号</th>
							      <th scope="col">商品</th>
							      <th scope="col">数量</th>
							      <th scope="col">合计</th>
							      <th scope="col">是否支付</th>
							      <th scope="col">地址</th>
							      <th scope="col"></th>
							    </tr>
							 </thead>
						    <% for(int i=0;i<toBePaidOrders.size();i++){ %>
						    <tr>
							      <td scope="row"><%=toBePaidOrders.get(i).ordernumber %></td>
							      <td><p><%=toBePaidOrderBooks.get(i).name %></p><img src="<%=toBePaidOrderBooks.get(i).imgurl %>" width="100px" /></td>
							      <td><%=toBePaidOrders.get(i).num %></td>
							      <td><%=toBePaidOrders.get(i).payment %></td>
							      <td>
							      	<%if(toBePaidOrders.get(i).paymentState == 0){%>
							      		未支付
							      	<%}else{ %>
							      		已支付
							      	<%}%>
							      </td>
							      <td>
							      	<%if(toBePaidOrders.get(i).position == 0){%>
							      		无地址
							      	<%}else{ %>
							      		<%=toBePaidOrders.get(i).position %>
							      	<%}%>
							      </td>
							      <td><input type="checkbox"></td>    
						    	</tr>
						    <%} %>
						  </tbody>
						</table>
			    </div>
			</div>
			<div class="card rounded-0 border-left-0 border-right-0" style="">
				<div class="card-body">
					<h5 class="card-title bg-primary"><img src="images/order_time.svg" width="20px"/>所有订单</h5>
					<table class="table">
						  <tbody>
						  	<thead>
							    <tr>
							      <th scope="col">订单号</th>
							      <th scope="col">商品</th>
							      <th scope="col">数量</th>
							      <th scope="col">合计</th>
							      <th scope="col">是否支付</th>
							      <th scope="col">地址</th>
							      <th scope="col"></th>
							    </tr>
							 </thead>
						     <% for(int i=0;i<paidOrders.size();i++){ %>
						    	<tr>
							      <td scope="row"><%=paidOrders.get(i).ordernumber %></td>
							      <td><p><%=paidOrderBooks.get(i).name %></p><img src="<%=paidOrderBooks.get(i).imgurl %>" width="100px" /></td>
							      <td><%=paidOrders.get(i).num %></td>
							      <td><%=paidOrders.get(i).payment %></td>
							      <td>
							      	<%if(paidOrders.get(i).paymentState == 0){%>
							      		未支付
							      	<%}else{ %>
							      		已支付
							      	<%}%>
							      </td>
							      <td>
							      	<%if(paidOrders.get(i).position == 0){%>
							      		无地址
							      	<%}else{ %>
							      		<%=paidOrders.get(i).position %>
							      	<%}%>
							      </td>
							      <td><input type="checkbox"></td>
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
