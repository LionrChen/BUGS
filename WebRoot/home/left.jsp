<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LEFT</title>
		<style type="text/css">
			*{
				padding:0px;
				margin: 0px;
			}
			body{
				padding:0px;
				margin:0px;
				user-select:none;
			}
			a{
				text-decoration: none;
				
			}
			.left{
				background-color: #F6F6F6;
				min-width: 200px;
				padding: 5px;
				
			}
			.login_info{
				padding-top: 20px;
				font-size: 13px;
				padding-bottom: 25px;
				background-color:#FFFFFF;
				border-bottom: 2px solid #96B97D;
			}
			.login_info p{
				margin-top: 10px;
				margin-left: 10px;
			}
			.login_info p:first-of-type{
				margin-top: 0px;
			}
			.login_info p a {
				float: right;
				margin-right: 10px;
				color: #96B97D;
			}
			.login_info p a:hover{
				text-decoration: underline;
			}
			.manu{
				background-color:#FFFFFF;
				min-height: 688px;
				padding: 5px;
			}
			.list{
			}
			.list span{
				display:block;
				height:35px;
				margin: 5px 0px;
				overflow: hidden;
				background-color: #f6f4f0;	
				transition:height ease 0.5s;
				box-shadow: 0px 0px 2px #eeeeee ;
			}
			
			.list span:hover{
			}
			.list span p{
				background-color: #f6f4f0;
				line-height: 30px;
				
				text-align: center;
				border-bottom: 1px solid #FFFFFF;
			}
			.list span p:first-of-type{
				display: block;
				text-align: left;
				line-height: 35px;
				padding-left: 5px;
			}	
			.list span p:first-of-type:hover{
				background-color:#96B97D;
				color: #FFFFFF;
			}
			.list span p a{
				color: #96B97D;
			}
			.list span p:hover{
				background-color:#FFFFFF;
				color:#FFFFFF;
			}
			.list span p a:hover{
				color:#000;
			}
		</style>
		
	</head>
	<body>
		
		<div class="left">
			<div class="login_info">
				<p>欢迎${user.username}&nbsp;${user.gender=='male'?'先生':'女士'}</p>
				<p>注册时间：</p>
				<p>${user.registerTime}</p>
				<p><a href="${pageContext.request.contextPath}/exitServlet">退出登录</a></p>
			</div>
			<div class="manu">
				<div class="list" id="navSet">
					<span id="nav">
						<p id="nav_p">商品管理</p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/queryBookServlet" target="rightContent">书籍展示</a></p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/product/add.jsp" target="rightContent">添加书籍</a></p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/product/queryByCondition.jsp" target="rightContent">查询书籍</a></p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/product/viewOfProduct.jsp" target="rightContent">列表展示</a></p>
					</span>
					<span id="nav">
						<p id="nav_p">用户管理</p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/queryVIPCustomerListServlet" target="rightContent">会员用户列表</a></p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/queryCommonCustomerListServlet" target="rightContent">普通用户列表</a></p>
					</span>
					<span id="nav">
						<p id="nav_p">管理员管理</p>
						<p id="nav_p"><a href="${pageContext.request.contextPath}/queryUserListServlet" target="rightContent">管理员用户列表</a></p>
						<p id="nav_p"><a href="modifypwd.jsp" target="rightContent">管理员信息管理</a></p>
					</span>
					<span id="nav">
						<p id="nav_p">主页推荐</p>
						<p id="nav_p"><a href="#" target="rightContent">精品推荐</a></p>
						<p id="nav_p"><a href="#" target="rightContent">热销推荐</a></p>
					</span>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../js/left.js"></script>
	</body>
	
</html>
