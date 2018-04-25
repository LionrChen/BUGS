<%@page import="com.bugs.domain.Customer"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Customer customer=new Customer();
customer=(Customer)session.getAttribute("customer");
boolean login=false;
if(session.getAttribute("customer")==null){
	login=false;
}else{
	login=true;
}

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		
		<style>
			*{
				margin: 0px;
				padding: 0px;
			}
			body{
				font-family: "微软雅黑";
			}
			a{
				text-decoration: none;
				
			}
			input{
				outline: none;
			}
			header{
				height: auto;
				width: auto;
				font-family: "微软雅黑";
			}
			.clearfix:after{
				content:'\20';
				display: block;
				clear: both;
			}
			.basic_info{
				height: 40px;
				width: 100%;
				background-color: #e3e4e5;
				border-bottom: 1px solid #dddddd;
			}
			.basic_info .basic_info_bin{
				width: 1210px;
				margin: 0px auto;
				color: #999999;
				font-size: 14px;
			}
			.basic_info .basic_info_bin a{
				color: #999999;
				line-height: 40px;
			}
			.basic_info .basic_info_bin a:hover{
				color: #5A88CA;
			}
			.basic_info .basic_info_bin .refrash{
				display: inline-block;
				float: left;
				background: url(images/home_icon.png) no-repeat 0px 12px;
			}
			.basic_info .basic_info_bin .refrash a{
				margin-left: 20px;
			}
			.basic_info .basic_info_bin .refrash a:hover{
				color: #5a88ca;
			}
			.basic_info .basic_info_bin .anystate{
				display: inline-block;
				float: right;
			}
			.basic_info .basic_info_bin .anystate .stateinfo{
				display: inline-block;
				padding-right: 10px;
				padding-left: 10px;
				background: url(images/bg_icon.png)no-repeat right;
			}
			.basic_info .basic_info_bin .anystate .stateinfo:last-of-type{
				background:url();
			}
			.logo{
				
				height: 120px;
				width: 1210px;
				margin: 0px auto;
				padding-top: 60px;
			}
			.logo .logo_img{
				display: inline-block;
				height: 42px;
				width: 109px;
				float: left;
				text-indent: -99999px;
				background: url(images/logo.png) no-repeat;
			}
			.logo .cart{
				position: relative;
				display: inline-block;
				float: right;
				width: 170px;
				height: 66px;
				border: 1px solid #dddddd;
				text-align: center;
			}
			.logo .cart a{
				display: block;
				height: 66px;
				line-height: 66px;
				width: 170px;
				font-weight: bold;
				
				color: #666666;
			}
			.logo .cart a:hover{
				box-shadow: 0px 0px 5px #DDDDDD;
				text-decoration: underline;
			}
			.logo .cart a span{
				color: #5A88CA;
			}
			.logo .cart .num{
				min-width: 20px;
				max-width: 25px;
				min-height: 20px;
				max-height: 25px;
				position: absolute;
				right: -10px;
				top: -10px;
				background-color: #5A88CA;
				color: #FFFFFF;
				border-radius: 100%;
			}
			.nav_section{
				height: auto;
				background-color: #e3e4e5;
				/*border-bottom: 1px solid #dddddd;
				border-top: 1px solid #dddddd;*/
			}
			.nav_section .nav_bin{
				height: 60px;
				width: 1210px;
				margin: 0px auto;
			}
			.nav_section .nav_bin .nav{
				
				width: 600px;
				display: inline-block;
				float: left;
				
			}
			.nav_section .nav_bin .nav a{
				display: inline-block;
				width: 90px;
				color: #6e6a6a;
				height: 60px;
				line-height: 60px;
				text-align: center;
			}
			.nav_section .nav_bin .nav a:first-of-type{
				width: 180px;
				background-color: #5A88CA;
				color: #FFFFFF;
				text-align: left;
			}
			
			.nav_section .nav_bin .nav a:hover{
				background-color: #5A88CA;
				color: #FFFFFF;
				
			}
			.nav_section .nav_bin .search{
				height: 60px;
				line-height: 60px;
				width: 400px;
				display: inline-block;
				float: right;
			}
			
			.nav_section .nav_bin .banner{
				display: none;
			}
			
			
			.content_section{
				background-size: 100% 362px;
			}
			.content_section .content_bin{
				width: 1210px;
				margin: 0px auto;
			}
			.content_section .content_bin .classify_nav{
				height: 680px;
				background-color: #69a2f4;
				width: 180px;
				float: left;
			}
			.classify_nav p{
				height: 40px;
				width: 180px;
				border-bottom: 1px solid #5A88CA;
			}
			.classify_nav p a{
				display: block;
				color: #FFFFFF;
				height: 40px;
				margin-left: 20px;
			
				line-height: 40px;
			}
			.classify_nav p a:hover{
				text-decoration: underline;
			}
			.banner{
				height: 362px;
				width: 1000px;
				overflow: hidden;
			}
			.banner .imgs{
				
			}
			.banner .imgs img{
				opacity: 1;
				transition: opacity 2s;
			}
			.banner .imgs input{
				outline: none;
			}
			.banner .imgs input:focus{
				outline: none;
			}
			.banner .imgs .fowardimg{
				display:none;
				border: 0px;
				width: 35px;
				height: 59px;
				background: url(images/forward.png) no-repeat left;
				position: absolute;
				top: 50%;
				left: 200px;
			}
			.banner .imgs .nextimg{
				display:none;
				border: 0px;
				width: 35px;
				height: 59px;
				background: url(images/next.png) no-repeat right;
				position: absolute;
				top: 50%;
				right: 200px;
			}
			.banner .imgs .fowardimg:hover,.fowardimg:focus{
				outline: none;
				background: url(images/forwardhover.png) no-repeat left;
				border: 0px;
			}
			.banner .imgs .nextimg:hover,.nextimg:focus{
				outline: none;
				background: url(images/nexthover.png) no-repeat right;
				border: 0px;
			}
			.banner .imgs_checkd{
				height: 50px;
				width: 150px;
				position: relative;
				top: -50px;
				left: 320px;
			}
			.banner .imgs_checkd .newcheckbox{
				width:14px;
				height: 14px;
				margin: 20px 8px;
				float: left;
				position: relative;
				border-radius: 100%;
				
				
			}
			.newcheckbox input{
				display: none;
				
			}
			.newcheckbox label{
				display: block;
				width:14px;
				height: 14px;
				border-radius: 100%;
				transition: all .3s ease;
				cursor: pointer;
				position: absolute;
				top: px;
				left: px;
				z-index: 1;
				background: #DDDDDD;
			}
			
			.newcheckbox input[type=radio]:checked + label{
				background: #5A88CA;
			}
			
			.containers{
				background-color: #f8f8f8;
			}
			.container_bin{
				width: 1210px;
				height: auto;
				margin: 0px auto;
			}
			.container{
				
			}
			.container .container_list:first-of-type{
				height: 290px;
				width: 1000px;
				margin-left: 190px;
				margin-top: 10px;
			}
			.container .container_list{
				height: 290px;
				width: 100%;
			}
			.container_list h1{
				line-height: 50px;
			}
			.book_list{
				background-color: #FFFFFF;
			}
			.book_list .books{
				height: 240px;
				width: 179px;
				float: left;
				padding: 10px;
				border-right: 1px solid #E3E4E5;
			}
			.book_list .books img{
				width: 158px;
				height: 158px;
			}
			.book_list .books p:first-of-type{
				height: 40px;
				line-height: 20px;
				font-size: 10px;
				margin: 0px 7px;
				overflow: hidden;
			}
			.book_list .books p:last-of-type{
				color: #5A88CA;
				font-size: 20px;
				margin: 0px 7px;
				
			}
			.book_list .books a{
				color:#000000;
			}
			.book_list .books a:hover{
				text-decoration: underline;
				color:#5A88CA;
			}
			.search{
				position:relative;
			}
			.search input{
				
				height:2em;
				border:0px;
			}
			.search img{
				position:absolute;
				top:18px;
				left:145px;
			}
			.search img:hover{
				cursor:pointer;
			}
		</style>
	</head>
	<body>
		<header>
		<div class="basic_info">
			<div class="basic_info_bin">
				<div class="refrash">
					<a href="indexServlet">书店首页</a>
				</div>
				<div class="anystate">
					<div class="stateinfo">
					<%if(login){ %>
					<span>你好，<a href="customerInfoServlet">${customer.username }</a>|&nbsp;<a href="customerExitServlet">退出</a></span>
						
					<%}else{ %>
						<span>你好，游客！</span><a href="userLogin.jsp">如果已注册，请登录</a>
					<%} %>
					</div>
					<div class="stateinfo">
						<a href="myorder.jsp">我的订单</a>
					</div>
					<div class="stateinfo">
						<a href="importantpeople.jsp">书城会员</a>
					</div>
					<div class="stateinfo">
						<a href="communicateadmin.jsp">联系管理员</a>
					</div>
				</div>
			</div>
		</div>
		<div class="logo">
				<div class="logo_img">
					Store
				</div>
				<div class="cart">
					<a href="mycart.jsp">Cart-<span>￥100</span></a>
					<div class="num">5</div>
				</div>
				
			</div>
		</header>
		<section class="nav_section">
			<div class="nav_bin">
				<div class="nav">
					<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;图书分类</a>
					<a href="#">书城首页</a>
					<a href="#">精品推荐</a>
					<a href="#">特价书籍</a>
					<a href="#">热销排行</a>
				</div>
				<div class="search">
					<input type="text" ></input>
					<img src="images/search_icon.png" width="24px" />
				</div>
				
			</div>
		</section>
		<section class="content_section" id="content_section">
			<div class="content_bin">
				<div class="classify_nav">
					<p><a href="#">小说</a></p>
					<p><a href="#">文艺</a></p>
					<p><a href="#">生活</a></p>
					<p><a href="#">社会科学</a></p>
					<p><a href="#">人文</a></p>
					<p><a href="#">励志</a></p>
					<p><a href="#">管理</a></p>
					<p><a href="#">科学</a></p>
					<p><a href="#">教育</a></p>
					<p><a href="#">工具书</a></p>
					<p><a href="#">更多</a></p>
				</div>
				<div class="banner">
					<div class="imgs">
						<a><img src="images/ad_01.png" id="ad_img" /></a>
						<button class="fowardimg" id="forward"></button>
						<button class="nextimg" id="next"></button>
					</div>
					<div class="imgs_checkd" id="img_list">
						<div class="newcheckbox">
							<input type="radio" name="adimg" checked="true" id="checkbox_new_1" value="images/ad_01.png"/>
							<label for="checkbox_new_1"></label>
						</div>
						<div class="newcheckbox">
							<input type="radio" name="adimg" id="checkbox_new_2" value="images/ad_02.png"/>
							<label for="checkbox_new_2"></label>
						</div>
						<div class="newcheckbox">
							<input type="radio" name="adimg" id="checkbox_new_3" value="images/ad_03.png"/>
							<label for="checkbox_new_3"></label>
						</div>
						<div class="newcheckbox">
							<input type="radio" name="adimg" id="checkbox_new_4" value="images/ad_04.png"/>
							<label for="checkbox_new_4"></label>
						</div>
						<div class="newcheckbox">
							<input type="radio" name="adimg" id="checkbox_new_5" value="images/ad_05.png"/>
							<label for="checkbox_new_5"></label>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="containers">
			<div class="container_bin">
				<div class="container">
					<div class="container_list clearfix">
						<h1>每日推荐</h1>
						<div class="book_list clearfix" id="recommendbook">
							<%-- <p:forEach items="${recommendbooklist }" var="book" varStatus="vs">
								<div class="books">
									<img src="${book.imgurl }" />
									<p>${book.name }</p>
									<p>￥${book.price }</p>
								</div>
							</p:forEach> --%>
						</div>
					</div>
					<div class="container_list clearfix">
						<h1>热销推荐</h1>
						<div class="book_list">
							<p:forEach items="${allBooks }" var="book" varStatus="vs">
								<div class="books">
									<img src="${book.imgurl }" />
									<a href='bookDetailServlet?bookId=${book.id }'>
										<p>${book.name }</p>
										<p>￥${book.price }</p>
									</a>
								</div>
							</p:forEach>
							
						</div>
					</div>
					
				<div class="rendpreasure">
					<div class="book_card">
						<img src="#" />
						<p>${preasurebook.name}</p>
						<p>${preasurebook.price}</p>
					</div>
				</div>
			</div>
		</section>
		
		<footer>
			
		</footer>
		<script type="text/javascript" src="js/ajax.js" ></script>
		<script src="js/rgbaster.js"></script>
		<script type="text/javascript">
			var forward_button=document.getElementById('forward');
			var next_button=document.getElementById('next');
			var img_radio_1=document.getElementById('checkbox_new_1');
			var img_radio_2=document.getElementById('checkbox_new_2');
			var img_radio_3=document.getElementById('checkbox_new_3');
			var img_radio_4=document.getElementById('checkbox_new_4');
			var img_radio_5=document.getElementById('checkbox_new_5');
			var img_div = document.getElementById('img_list');
			var ad_img=document.getElementById("ad_img");
			var img_radio=document.getElementsByName('adimg');
			
			var content_section=document.getElementById("content_section");
			forward_button.onclick=function(){
				var len=img_radio.length;
				for(i=len-1;i>=0;i--){
					if(img_radio[i].checked==true){
						if(i==0){
							ad_img.src=img_radio[len-1].value;
							img_radio[len-1].checked=true;
							setBgColor();
							
							
						}else{
							img_radio[(--i)%(len)].checked=true;
							ad_img.src=img_radio[i%(len)].value;
							setBgColor();
						}
						
					}
				}
			}
			
			
			next_button.onclick=function(){
				var len=img_radio.length;
				for(i=0;i<len;i++){
					if(img_radio[i].checked==true){
						img_radio[(++i)%(len)].checked=true;
						ad_img.src=img_radio[(i)%(len)].value;
						setBgColor();
					}
				}
			}
			
			img_radio_1.onclick=function(){
				ad_img.src=img_radio_1.value;
				setBgColor();
			}
			img_radio_2.onclick=function(){
				ad_img.src=img_radio_2.value;
				setBgColor();
			}
			img_radio_3.onclick=function(){
				ad_img.src=img_radio_3.value;
				setBgColor();
			}
			img_radio_4.onclick=function(){
				ad_img.src=img_radio_4.value;
				setBgColor();
			}
			img_radio_5.onclick=function(){
				ad_img.src=img_radio_5.value;
				setBgColor();
			}
			
			
			//设置背景色
			function setBgColor(){
				RGBaster.colors(ad_img, {
				  success: function(payload) {
				   content_section.style.setProperty("background-color",payload.dominant);
				  }
				});
			}
			function autoDisplay(){
				var len=img_radio.length;
				for(i=0;i<len;i++){
					if(img_radio[i].checked==true){
						img_radio[(++i)%(len)].checked=true;
						ad_img.src=img_radio[(i)%(len)].value;
						setBgColor();
					}
				}
			}
			var timer=setInterval(autoDisplay,6000);

			window.onload=function(){
				setBgColor();
				var xhr = getXMLHttpRequest();
				xhr.onreadystatechange = function() {
					//xhr.readyState==4：表示接收到了完整的响应体
					if (xhr.readyState == 4) {
						//是否正确响应
						if (xhr.status = 200) {
							var result = xhr.responseText;
							document.getElementById("recommendbook").innerHTML+="<p:forEach items='${recommendbooklist }' var='book' varStatus='vs'><div class='books'><img src='${book.imgurl }' /><a href='bookDetailServlet?bookId=${book.id }'><p>${book.name }</p><p>￥${book.price }</p></a></div></p:forEach>";
						}
					}
				};
				xhr.open("get", "getRecommendBookServlet");
				xhr.send();
			};
			
		</script>
	</body>
</html>

