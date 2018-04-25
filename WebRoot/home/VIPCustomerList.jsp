<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
		table{
			width: 100%;
			background-color: #F6F4F0;
		}
		caption{
			height: 40px;
			font-size: 20px;
		}
		thead{
			font-size: 15px;
			color: #FFFFFF;
			background-color: #96B97D;
			
		}
		thead tr th{
			height: 35px;
		}
		tbody tr th{
			height: 35px;
			border-bottom: 1px solid #FFFFFF;
		}
		tbody tr{
			border-bottom: 1px solid #FFFFFF;
		}
		tbody tr:hover{
			background-color: #96B97D;
			color:#ffffff;
		}
		.checked{
			background-color: #;
			color:red;
		}
		.actions{
			height: auto;
			width: 100%;
			border-top: 1px solid #96B97D;
			padding: 10px;
		}
		.actions span{
			display: block;
			line-height: 35px;
			width: 50px;
			background-color: #96B97D;
			color: #FFFFFF;
			float: right;
			text-align: center;
			margin-right: 10px;
			border-radius: 3px;
			cursor: pointer;
		}
		.actions span:hover{
			box-shadow: 0px 0px 2px #000000;
		}
		
	</style>
</head>
<body>
	<table class="table table-hover">
	<caption>会员列表</caption>
	<thead>
		<tr>
			<th>ID</th>
			<th>会员名</th>
			<th>邮箱</th>
			<th>密码</th>
			<th>性别</th>
			<th>电话</th>
			<th>简介</th>
			<th>注册时间</th>
		</tr>
	</thead>
	<tbody>
		<p:forEach items="${VIPList }" var="customer" varStatus="vs">
			<tr>
				<th>${customer.id}</th>
				<th>${customer.username }</th>
				<th>${customer.email }</th>
				<th>${customer.password}</th>
				<th>${customer.gender}</th>
				<th>${customer.telephone}</th>
				<th>${customer.introduce}</th>
				<th>${customer.registerTime}</th>
			</tr>
		</p:forEach>
	</tbody>
</table>
<div class="actions">
		<span id="modify">修改</span>
		<span id="delete">删除</span>
	</div>
<script type="text/javascript">
		//列表选中
		var tbody=document.getElementsByTagName("tbody");
		var trs=tbody[0].getElementsByTagName("tr");
		//修改删除按钮
		var modify=document.getElementById("modify");
		var dele=document.getElementById("delete");
		//选中操作
		for(var i=0;i<trs.length;i++){
			trs[i].checked=false;
			trs[i].onclick=function(){
				if(this.checked){
					this.style.backgroundColor="";
					this.style.color="";
					this.checked=false;
				}else{
					this.style.backgroundColor="#96B97D";
					this.style.color="#ffffff";
					this.checked=true;
				}
//				if(this.doubleclick>1){
//					var ths=this.getElementsByTagName("th");
//					var id=ths[0].innerHTML;
//					window.location.href="${pageContext.request.contextPath}/queryCustomerByIdServlet?id="+id;
//				}
			}
		}
		//修改操作
		modify.onclick=function(){
			var sum=0;
			var id;
			for(var i=0;i<trs.length;i++){
				if(trs[i].checked){
					sum++;
					var ths=trs[i].getElementsByTagName("th");
					
					var id=ths[0].innerHTML;
					
				}
			}
			if(sum==0){
				alert("请选择用户");
			}
			if(sum>1){
				alert("只能同时修改一个用户的信息！！！");
			}else{
				window.location.href="${pageContext.request.contextPath}/queryCustomerByIdServlet?id="+id;
			}
		}
		dele.onclick=function(){
			var ids_sql="";
			var sum=0;
			for(var i=0;i<trs.length;i++){
				if(trs[i].checked){
					sum++;
					var ths=trs[i].getElementsByTagName("th");
					var id=ths[0].innerHTML;
					if(i>1){
						ids_sql+=",";
					}
					ids_sql+=id;
				}
			}
			if(sum==0){
				alert("请选择用户");
			}
			if(window.confirm("确定删除ID为"+ids_sql+" 的用户信息吗？")){
				window.location.href="${pageContext.request.contextPath}/deleteCustomerByBatchServlet?ids="+ids_sql;
			}
		}
	</script>
</body>
</html>
