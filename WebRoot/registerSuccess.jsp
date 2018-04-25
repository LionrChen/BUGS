<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register Success</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/main.js"></script>
  </head>
  
<body>
    <div class="header">

    </div>

    <div class="content">

        <div class="remind">
            <div class="img">
                <img src="images/success.jpg" alt="">
            </div>
            <div class="remind_font">
                <h2>注册成功！别忘记激活账户哦！</h2>
                <p><span id="span01">5</span>秒后自动为您跳转至首页...</p>
            </div>
        </div>
    </div>

    <div class="footer"></div>
</body>
</html>
