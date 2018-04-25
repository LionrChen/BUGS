<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			.form{
				background-color: #F6F4F0;
			}
			form{
				width: 768px;
				margin: 0px auto;
				
				padding: 10px;
				
				height: 800px;
			}
			form p{
				height: 35px;
				font-size: 20px;
				
			}
			form span{
				display: inline-block;
				width: 100px;
				font-size: 17px;
			}
			.form_title {
				color: #FFFFFF;
				background-color: #96B97D;
				padding: 10px;
			}
			
			.inputarea{
				height: 50px;
				margin: 5px;
				color: #96B97D;
				
			}
			.inputarea p input{
/*				display: block;*/
				line-height: 30px;
				font-size: 15px;
				border: 1px solid #96B97D;
				box-shadow: 0px 0px 3px #96B97D;
				margin-left: 10px;
				border-radius: 3px;
			}
			.inputarea_text{
				height: 120px;
				margin: 5px;
				color: #96B97D;
			}
			.inputarea_text p span{
				display: block;
				width: 100px;
				float: left;
			}
			.inputarea_text p textarea{
				line-height: 30px;
				height: 100px;
				width: 500px;
				font-size: 15px;
				border: 1px solid #96B97D;
				box-shadow: 0px 0px 3px #96B97D;
				margin-left: 10px;
				border-radius: 3px;
			}
			.submit_button{
				height: 40px;
				text-align: center;
			}
			.submit_button input{
				background-color: #96B97D;
				color: #FFFFFF;
				width: 100px;
				font-size: 15px;
				line-height: 35px;
				margin: 5px;
				border: 1px solid #96B97D;
				box-shadow: 0px 0px 3px #96B97D;
				border-radius: 3px;
			}
			.submit_button input:hover{
				box-shadow: 0px 0px 5px #000000;
			}
		</style>
	</head>
	<body>
		<div class="form">
			<form action="editCustomerByIdServlet" method="post">
				<div class="form_title">
					<p><span>用户ID</span>${customer.id}</p>
					<p><span>注册时间</span>${customer.registerTime}</p>
				</div>
				
				<div class="inputarea">
					<p><span>用户名</span><input type="text" value=${customer.username} /></p>
				</div>
				<div class="inputarea">
					<p><span>Email</span><input type="text" value=${customer.email} /></p>
				</div>
				<div class="inputarea">
					<p><span>密码</span><input type="text" value=${customer.password} /></p>
				</div>
				<div class="inputarea">
					<p><span>性别</span><input type="text" value=${customer.gender} /></p>
				</div>
				<div class="inputarea">
					<p><span>电话</span><input type="text" value=${customer.telephone} /></p>
				</div>
				<div class="inputarea_text">
					<p><span>简介</span><textarea >${customer.introduce}</textarea></p>
				</div>
				<div class="submit_button">
					<input type="submit" value="确认修改"/>
					<input type="button" value="取消"/>
				</div>
			</form>
		</div>
	</body>
</html>

