<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--prefix:前缀,调用库里面的内容必须以此前缀开头，该值自己定义 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//鼠标移入事件
function MouseOver(tr1){
tr1.style.backgroundColor="#AFD1F4";
}
//鼠标移出事件
function MouseOut(tr1){
tr1.style.backgroundColor="#ffffff";
}
function DelByClick(tr1){
   //使用title属性存储id值,再使用当前对象获取指定的属性值
  var s = tr1.getAttribute("title");
 if(window.confirm("确认要删除这个用户吗？")){
     //删除动作，需要跳转到servlet
   window.location.href="${pageContext.request.contextPath}/deleteUserServlet?id="+s;
  }

}
</script>
</head>
<body style="margin: 0px 0px 0px 0px;">
	<h1
		style="background-color:  #AFD1F4;text-align: center; margin: 0px 0px 0px 0px;padding: 20px 0px 20px 0px;">用户列表</h1>
	
	  <div style="text-align: center;font-size:20px;color: red;margin-top:20px;">${error_msg01}</div>
	<!-- cellspacing:边框的间隙-->
	<table border="1px solid gray" align="center" cellspacing="0px" width="90%" 
	style="margin-top: 20px;">
		<tr  bgcolor="#AFD1F4"  >
			<td width="18%" align="center">用户编号</td>
			<td width="18%" align="center">用户名</td>
			<td width="12%" align="center">密码</td>
			<td width="18%" align="center">邮箱</td>
			<td width="12%" align="center">激活状态</td>
			<td width="12%" align="center">电话</td>
			<td width="12%" align="center">删除</td>
		</tr>
<!--items表示要遍历的集合  ，var值表明的是每一次遍历出来的对象进行存储 -->
     <p:forEach  var="user"   items="${list}">
     <!-- title属性不再界面显示， 暂存一个数据 -->
        <tr onmouseout="MouseOut(this)" onmouseover="MouseOver(this)" onclick="DelByClick(this)" title="${user.id}">
			<td align="center">${user.id}</td>
			<td align="center">${user.username }</td>
			<td align="center">${user.password }</td>
			<td align="center">${user.email }</td>
			<td align="center">${user.state==0?"<font color='red'>未激活</font>":'已激活'}</td>
			<td align="center">${user.telephone }</td>
			<td align="center"><img src="images/i_del.gif"/></td>
		</tr>
		</p:forEach>
	</table>
</body>
</html>