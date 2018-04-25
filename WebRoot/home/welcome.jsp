<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style>
			.welcome{
				background-color:  #AFD1F4; 
				padding-left: 200px;
				font-size: 30px;
				color: #FFFFFF;
				background-color: #96B97D;
				line-height: 200px;
			}
			.welcom_info{
				text-align: center;
				background-color: #F6F4F0;
				line-height: 500px;
			}
			.welcom_info font{
				color: #96B97D;
			}
		</style>
	</head>

	<body>
		<p class="welcome">Welcome...</p>
		<div class="welcom_info">登录成功，欢迎
			<font>${user.username }</font>进入网上书城管理系统!
		</div>
	</body>

</html>