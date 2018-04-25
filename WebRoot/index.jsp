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

<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Home</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link href="css/index.css" rel="stylesheet">
		<style>
			
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
						<a href="viewMyOrdersServlet">我的订单</a>
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
					<a href="viewMyCartServlet" >Cart-￥<span id="cartItemsPayment">-</span></a>
					<div class="num" id="cartItemsNum">-</div>
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
		<script src="js/bootstrap/js/jquery.js"></script>
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
		<script type="text/javascript" src="js/index.js" charset="gbk"></script>
	</body>
</html>

