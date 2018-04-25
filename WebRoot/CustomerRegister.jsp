<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CustomerRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入jQuery的js库 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- 引入bootstrap的css库 -->
	<link href="css/bootstrap.min.css" rel="stylesheet"></link>
	<!-- 引入bootstrap的js库 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="js/ajax.js"></script>
	<script type="text/javascript">
	function getEmailBlur() {
			var email01 = document.getElementById("email01");
			//校验邮箱ajax(邮箱格式是否正确，邮箱是否为空,邮箱是否已经被注册)
			var xhr = getXMLHttpRequest();
			//监听校验过程得到校验结果进行显示页面上
			xhr.onreadystatechange = function() {
				//xhr.readyState==4：表示接收到了完整的响应体
				if (xhr.readyState == 4) {
					//是否正确响应
					if (xhr.status = 200) {
						var result = xhr.responseText;
						//查找显示元素的对象
						var emailFont = document.getElementById("emailFont");
						if (result == "邮箱可以正常使用") {
							emailFont.color = "green";
						} else {
							emailFont.color = "red";
						}
						emailFont.innerHTML = result;
					}
				}
			}
			xhr.open("get", "checkEmailServlet?email=" + email01.value);
			xhr.send();
	
	}
	
	function getUsernameBlur(){
		var username = document.getElementById("username");
		if(username == null){
			result="用户名不能为空";
		}else if(username.toString().length<6){
			result="用户名不能小于6位";
		}
		var usernameFont =document.getElementById("usernameFont");
		
	}
	
	function getPasswordBlur(){
		var password = document.getElementById("password");
		if(password == null){
			result="密码不能为空";
		}else if(password.toString().length<6){
			result="密码名不能小于6位";
		}
		var passwordFont =document.getElementById("passwordFont");
		passwordFont.innerHTML = result;
	}
	</script>


  </head>
  
  <body>
    <div class="container"  style="width:900px;">
		<form action="customerRegisterServlet" method="post" class="form-horizontal" style="margin-top:100px;">
			<h2 style="margin-bottom:50px;">用户注册</h2>
			
			<div class="form-group">
				<label for="email" class="col-md-2 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label><label id="emailFont" class="control-label"></label>
				<div class="col-lg-4">
					<input type="text" id="email01" name="email" class="form-control col-md-2" placeholder="请输入邮箱" onblur="getEmailBlur()"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="username" class="col-md-2 control-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</label><label id="usernameFont" class="control-label"></label>
				<div class="col-lg-4">
					<input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名" onblur="getUsernameBlur()"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="password" class="col-md-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label><label id="passwordFont" class="control-label"></label>
				<div class="col-lg-4">
					<input type="password" id="password"  name="password" class="form-control" placeholder="请输入密码" onblur="getPasswordBlur()"/>
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-md-2 control-label">确认密码&nbsp;:</label>
				<div class="col-lg-4">
					<input type="password" id="password1"  name="password1" class="form-control" placeholder="请输入密码"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="gender" class="col-md-2 control-label" >性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
			    <div class="radio">
			    	<label>
			    		<input name="gender" value="male" type="radio"/>男&nbsp;&nbsp;&nbsp;&nbsp;
			    	</label>
			    	<label>
			    		<input name="gender" value="famale" type="radio"/>女
			    	</label>
			    	
			    </div>
			</div>
			
			<div class="form-group">
				<label for="telephone" class="col-md-2 control-label">联系电话&nbsp;:</label>
				<div class="col-lg-4">
					<input type="text" id="telephone" name="telephone" class="form-control col-md-2" placeholder="请输入联系电话"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="introduce" class="col-md-2 control-label">个人介绍&nbsp;:</label>
				<div class="col-md-8">
					<textarea name="introduce" class="form-control" rows="3"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="telephone" class="col-md-2 control-label">验&nbsp;&nbsp;证&nbsp;&nbsp;码:</label>
				<div class="col-md-4">
					<input type="text" id="ckCode" name="ckCode" class="form-control col-md-4" placeholder="请输入验证码"/>
					<img  src="${pageContext.request.contextPath}/CheckCode"/>
				</div>
			</div>
			<p class="text-center">
				<button class="btn btn-info btn-lg" type="submit">确认注册</button>
				<button class="btn btn-info btn-lg" type="reset">重置</button>
			</p>
		</form>
	</div>
  </body>
</html>
