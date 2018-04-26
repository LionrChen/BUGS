<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.bugs.domain.Address"%>
<%@page import="com.bugs.domain.Book"%>
<%@page import="com.bugs.domain.order"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
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
List<Address> addresses = (List<Address>)session.getAttribute("addresses");
List<order> orders = (List<order>)session.getAttribute("orders");
List<Book> books = (List<Book>)session.getAttribute("books");

boolean theFirst = true;
String ordernumbers = "";
double payment = 0;

for (order order:orders){
	if(theFirst){
		ordernumbers += order.getOrdernumber();
		theFirst = false;
	}else{
		ordernumbers += ","+order.getOrdernumber();
	}
	payment +=order.getPayment();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>确认订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/header.css" rel="stylesheet">
	<link href="css/confirmOrder.css" rel="stylesheet">

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
					<span>你好，<a href="customerInfoServlet">${customer.username }</a>&nbsp;|&nbsp;<a href="customerExitServlet">退出</a></span>
						
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
			<div class="content p-3">
				<h5 class=" bg-primary"><img src="images/order.svg"  width="20px"/>确认订单</h5>
				<%for(int i=0;i<orders.size();i++) {%>
					<div class="orderInfo">
						<div>
							<img src="<%=books.get(i).getImgurl() %>" />
							<p><%=books.get(i).getName() %></p>
							<p>价格:<span><%=books.get(i).getPrice() %></span>￥</p>
							<p>购买数量:<span><%=orders.get(i).getNum() %></span></p>
							<p>总价:<span><%=orders.get(i).getPayment() %></span>￥</p>
						</div>
					</div>
				<%} %>
				<div class="submitOrder">
					<form action="confirmPaymentServlet?action=moreOrder" method="post">
						<p:forEach items='${addresses }' var='address' varStatus='vs'>
							<div class="addresses">
								<input type="radio" value="${address.id }" name="addressId" checked=True />
								<p>联系人:<span>${address.contact } ${address.id }</span></p>
								<p>电话:<span>${address.phonenumber }</span></p>
								<p>地址:<span>${address.province }</span>省<span>${address.city }</span>市
								<span>${address.area }</span><span>${address.detail }</span></p>
								<p>邮编:<span>${address.zipcode }</span></p>
								
							</div>
						</p:forEach>
						
						<input value="<%=ordernumbers %>" style="display: none;" name="ordernumber"/>
						<p>共需支付:<span><%=payment %></span>￥</p>
						<p>支付方式:</p>
						<p>
							<label for="payfun">钱包支付</label>
							<input type="radio" name="payfun" id="payfun" value="wallet" checked=True/>
						</p>
						
						<button type="submit" class="btn btn-primary">确认支付</button>
						<button class="btn bg-white border-primary">返回</button>
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
</html>
