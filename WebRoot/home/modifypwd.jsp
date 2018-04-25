<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="margin: 0px 0px 0px 0px;">
<form action="${pageContext.request.contextPath}/updatePwdServlet" method="post">
<h1 style="background-color:  #AFD1F4;text-align: center; margin: 0px 0px 0px 0px;padding: 20px 0px 20px 0px;">修改密码</h1>
<table align="center" width="650px" style="margin-top:70px;">
			<tr>
				<td style="text-align:right;width: 35%">会员名:&nbsp;</td>

				<td width="30%"><input type="text" name="username"  value="${user.username }"/></td>

				<td><font color="red">用户名至少设置6位</font></td>
			</tr>
			<tr>
				<td style="text-align:right;padding-top: 10px;width: 35%">新密码:&nbsp;</td>

				<td width="30%"><input type="password" name="password" value="${user.password }" /></td>

				<td><font color="red"></font></td>
			</tr>
			<tr>
				<td style="text-align:right;padding-top: 10px;width: 35%">确认密码:&nbsp;</td>

				<td width="30%"><input type="password" name="confirmpassword" value="${confirmpassword }" /></td>

				<td><font color="red">${error_msg }</font></td>
			</tr>
			<tr>
				<td colspan="3" align="center" style="padding-top:70px; "><input
					type="submit"
					style="border-radius: 5px; 
			box-shadow: 0px 0px 8px 0 #888888;padding: 10px 60px 10px 60px;
			background-color:#FCFDEF; border:#D8D8D8 1px solid;color: black; "
					value="登录" /></td>
			</tr>
		</table>
</form>
</body>
</html>