<%@page import="com.bugs.domain.Book"%>
<%@page import="com.bugs.domain.order"%>
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
List<Book> toBePaidOrderBooks = (List<Book>)session.getAttribute("toBePaidOrderBooks");
List<Book> paidOrderBooks = (List<Book>)session.getAttribute("paidOrderBooks");
List<order> toBePaidOrders = (List<order>)session.getAttribute("toBePaidOrders");
List<order> paidOrders = (List<order>)session.getAttribute("paidOrders");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>所有订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	

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
			<div class="content p-3">
			<div class="card rounded-0 border-left-0 border-right-0" style="">
					<div class="card-body">
						<nav aria-label="breadcrumb">
						  <ol class="breadcrumb">
						    <li class="breadcrumb-item active" aria-current="page"><img src="images/order.svg" width="20px"/>待支付订单</li>
						  </ol>
						</nav>
						
						<table class="table">
							  <tbody>
							  	<thead>
								    <tr>
								      <th scope="col" style="width:5rem;"></th>
								      <th scope="col">订单号</th>
								      <th scope="col">商品</th>
								      <th scope="col">数量</th>
								      <th scope="col">合计</th>
								      <th scope="col">是否支付</th>
								      <th scope="col">地址</th>
								      <th scope="col" style="width:5rem;">操作</th>
								    </tr>
								    <tr>
								      <th scope="col"><input type="checkbox"  class="selectall" id="selectall"><label for="selectall">全选</label></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col"></th>
								      <th scope="col"></th>
								      <th scope="col"><a href="javascript:;"  id="deletechecked" >删除</a></th>
								    </tr>
								 </thead>
							    <% for(int i=0;i<toBePaidOrders.size();i++){ %>
							    <tr>
							          <td><input type="checkbox"  class="select" value="<%=toBePaidOrders.get(i).id %>"></td>
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
								      
								      <td>
								        <a class="deleteLink" href="customerDeleteOrderServlet?action=deid&orderId=<%=toBePaidOrders.get(i).id %>">删除</a>
								        <a class="deleteLink" href="continueBuyServlet?orderId=<%=toBePaidOrders.get(i).id %>">去支付</a>
								      </td>    
							    	</tr>
							    <%} %>
							  </tbody>
							</table>
				    </div>
				</div>
				<div class="card rounded-0 border-left-0 border-right-0" style="">
					<div class="card-body">
						<nav aria-label="breadcrumb">
						  <ol class="breadcrumb">
						    <li class="breadcrumb-item active" aria-current="page"><img src="images/order.svg" width="20px"/>所有订单</li>
						  </ol>
						</nav>
						<table class="table">
							  <tbody>
							  	<thead>
								    <tr>
								      <tr>
								      <th scope="col" style="width:5rem;"></th>
								      <th scope="col">订单号</th>
								      <th scope="col">商品</th>
								      <th scope="col">数量</th>
								      <th scope="col">合计</th>
								      <th scope="col">是否支付</th>
								      <th scope="col">地址</th>
								      <th scope="col" style="width:5rem;">操作</th>
								    </tr>
								    <tr>
								      <th scope="col"><input type="checkbox"  class="selectall" id="selectall"><label for="selectall">全选</label></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col" ></th>
								      <th scope="col"></th>
								      <th scope="col"></th>
								      <th scope="col"><a href="javascript:;"  id="deletechecked" >删除</a></th>
								    </tr>
								    </tr>
								 </thead>
							     <% for(int i=0;i<paidOrders.size();i++){ %>
							    	<tr>
							    	  <td><input type="checkbox"  class="selectOfAllOrder" value="<%=paidOrders.get(i).id %>"></td>
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
								      <td><a class="deleteLink" href="customerDeleteOrderServlet?orderId=<%=paidOrders.get(i).id %>">删除</a></td>
							    </tr>
							   <%} %>
							  </tbody>
							</table>
				    </div>
				</div>
			</div>
		</div>
  </body>
  <script src="js/bootstrap/js/jquery.js"></script>
    <!-- popper.js-->
    <script src="js/bootstrap/js/popper.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap/js/bootstrap.js"></script>
    <script src="js/selectmore.js"></script>
</html>
