<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Insert title here</title>
<!-- 引入jQuery的js库 -->
<script type="text/javascript" src="../js/jquery.min.js"></script>
<!-- 引入bootstrap的css库 -->
<link href="../css/bootstrap.min.css" rel="stylesheet"></link>
<!-- 引入bootstrap的js库 -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="margin-top: 30px;">
		<!--一行  -->
		<div class="row">
			<p:forEach items="${booklist }" var="book" varStatus="vs">
				<!--  列:col-md-4:占四列-->
				<div class="col-md-4">
				 <a href="/queryBookByIdServlet?id=${book.id }" class="thumbnail img-responsive">
					<img src="${book.imgurl }"  /></a>
					<div class="caption">
						<h3>${book.name }</h3>
						<h5>${book.description }</h5>
					</div>
				</div>
			</p:forEach>
		</div>


	</div>
</body>
</html>
