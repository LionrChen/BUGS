<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%-- <!--直接访问类的对象的属性是不能访问的；  
    ${user.username }:等价于java代码的   ：
   
    
    ${user}等价于 User user =(User)session.getAttribute("user");
    ${user.username}     等价于((User)session.getAttribute("user")).getUserName();
    
     -->
欢迎${user.username }来到主页面 --%>
<!-- 1.frameset框架标签和body不能同时出现 
     2. 在frameset标签中按照以前的请求转发或者重定向页面跳转，只能执行内部页面-->
<frameset rows="108,*" frameborder="no">
<frame src="top.jsp" name="top" scrolling="NO"></frame>

<frameset cols="290,*" frameborder="no">
<frame src="left.jsp" name="leftContent" style="overflow:hidden;"></frame>
<!-- 一进入程序要显示的默认页面 -->
<frame src="welcome.jsp" name="rightContent"></frame>
</frameset>

</frameset>

</html>