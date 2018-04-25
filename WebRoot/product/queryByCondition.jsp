<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="margin:0px;">
	<h1
		style="background-color:  #AFD1F4;text-align: center; margin: 0px 0px 0px 0px;padding: 20px 0px 20px 0px;">条件查询</h1>
	<!-- 表格 -->
	<form action="${pageContext.request.contextPath}/queryBookByManyConditionServlet" method="post">
	<table border="1px solid gray" align="center" cellspacing="0"
		style="margin-top:70px;width:900px;">
		<tr>
			<td colspan="4" style="text-align:center;color:red;">${error_book_msg}</td>
		</tr>
		<tr>
			<td
				style="background-color:  #AFD1F4;padding:10px;text-align:center;">商品编号：</td>
			<td><input type="text" name="id" style="margin:5px;" /></td>
			<td
				style="background-color:  #AFD1F4;padding:10px;text-align:center;">商品类别：</td>
			<td><select name="category">
					<option value=""></option>
					<option value="文学">文学</option>
					<option value="计算机">计算机</option>
					<option value="艺术">艺术</option>
					<option value="体育">体育</option>
					<option value="社会学">社会学</option>
					<option value="经济学">经济学</option>
			</select></td>
		</tr>
		<tr>
			<td
				style="background-color:  #AFD1F4;padding:10px;text-align:center;">商品名称：</td>
			<td><input type="text" name="name" style="margin:5px;"   /></td>
			<td
				style="background-color:  #AFD1F4;padding:10px;text-align:center;">商品价格：</td>
			<td><input type="text" name="minprice" style="margin:5px;" />--<input
				type="text" name="maxprice" style="margin:5px;" /></td>

		</tr>

		<tr>
			<td colspan="4"
				style="background-color:  #AFD1F4;
				height:100px;text-align:center;">

				<input type="submit" value="确定"
				style="border-radius: 10px; 
			box-shadow: 5px 5px 5px #888888;padding: 5px 20px 5px 20px;
			background-color:#FCFDEF; border: 1px solid gray;color: black; " />&nbsp;&nbsp;&nbsp;
				<input type="reset" value="重置"
				style="border-radius: 10px; 
			box-shadow: 5px 5px 5px #888888;padding: 5px 20px 5px 20px;
			background-color:#FCFDEF; border: 1px solid gray;color: black; " />&nbsp;&nbsp;&nbsp;
			</td>
		</tr>


	</table>
	</form>
</body>
</html>