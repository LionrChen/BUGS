<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
function getFocus() {
		//查找填写邮箱的输入框
		var email01 = document.getElementById("email01");
		//得到焦点的时候默认焦点颜色去掉
		email01.style.border = "1px solid red";
	}
function getBlur() {
		var email01 = document.getElementById("email01");
		email01.style.border = "1px solid  green";
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
						alert("1111111111");
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
</script>
</head>
<body bgcolor="white">

	<form action="registerServlet" method="post">
         <!-- 注释： 以下表格是注册使用的最简单的个人信息 -->
		<table align="center"  width="70%" style="margin-top:200px;">
			<tr style="border:1px solid red;">
				<td style="" width="20.5%"><h2>新会员注册</h2></td>
			</tr>
			<tr>
				<td style="text-align:right;"><font color="red">会员邮箱:&nbsp;</font>
				</td>
				<!-- td单元格的宽度 style="width:20%"-->

				<td style="" width="10%"><input type="text" name="email" onFocus="getFocus()" onblur="getBlur()" id="email01"/></td>

				<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;<span id="emailFont"></span></td>
			</tr>

			<tr>
				<td style="text-align:right;"><font color="red">会员名:&nbsp;</font>
				</td>

				<td style=""><input type="text" name="username" /></td>

				<td style="">&nbsp;&nbsp;&nbsp;&nbsp;请输入有效的会员名称</td>
			</tr>
			<tr>
				<td style="text-align:right;">密码:&nbsp;</td>

				<td><input type="password" name="password" /></td>

				<td>&nbsp;&nbsp;&nbsp;&nbsp;请输入正确的密码格式</td>
			</tr>
			<tr>
				<td style="text-align:right;">确认密码:&nbsp;</td>

				<td><input type="password" name="password1" /></td>

				<td>&nbsp;&nbsp;&nbsp;&nbsp;请输入正确的密码格式</td>
			</tr>



			<tr>
				<td style="text-align:right;">性别:&nbsp;</td>
				<td colspan="2"><input type="radio" value="male" name="gender" />男&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="radio" value="famale" name="gender" />女</td>
			<tr>
				<td style="text-align:right;">联系电话:&nbsp;</td>

				<td colspan="2" style=""><input type="text" name="telephone" /></td>

			</tr>
			<tr>
				<td style="text-align:right;">个人介绍:&nbsp;</td>

				<td colspan="2"><textarea name="introduce"></textarea></td>

			</tr>

		</table>
		
		  <!-- 注释： 以下表格是注册使用的中文验证码 -->
		<table align="center" width="70%" >
		
			<tr style="border:1px solid red;">
				<td><h2>注册校验</h2></td>
			</tr>
			
			<tr>
				<td style="text-align:right;" width="20.5%" >输入校验码:&nbsp;</td>

				<td  colspan="2" style="width:90%;"><input type="text" name="ckCode" /></td>
			</tr>
			
			 <tr><td></td><td style=""><img  src="${pageContext.request.contextPath}/CheckCode"/></td></tr>
			 
			<!-- 图片按钮  src路径-->
			<tr><td></td><td  colspan="2"><input type="image"  src="images/signup.gif" />   </td> </tr>
</table>
	</form>
</body>
</html>