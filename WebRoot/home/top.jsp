<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>TOP</title>
<style type="text/css">
*{
	padding:0px;
	margin:0px;
}
.top{
	background-color:#96b97d;
}
.logo{
	height:70px;
	background-color:#f6f6f6;
	padding-left: 20px;
}
.logo p{
	
	font-size: 50px;
}
.logo p span{
	color: #96b97d;
}
.logo_time{
	line-height: 38px;
	margin-left: 20px;
	color: white;
}
.goout{
	float: right;
	margin-right:20px;
	color: white;
	margin-top: -30px;
}
.goout a{
	color: #FFFFFF;
}
</style>
</head>
<body>
	<div class="top">
		<div class="logo"><p><span>BOOKSTORE</span>.MS</p></div>
		<div class="logo_time">
			<script type="text/javascript">
				/*  获取系统时间 */
				var date1 = new Date();
				//获取年
				var year1 = date1.getFullYear();
				//获取月
				var month1 = date1.getMonth() + 1;
				//获取的是日期(一个月当中那一天)
				var date2 = date1.getDate();
				//获取的是周(星期)中的那一天 0-6
				var day1 = date1.getDay();
				
				var s;
				switch(day1){
				case 0:
				s="星期日";
				break;
				case 1:
				s="星期一";
				break;
				case 2:
				s="星期二";
				break;
				case 3:
				s="星期三";
				break;
				case 4:
				s="星期四";
				break;
				case 5:
				s="星期五";
				break;
				case 6:
				s="星期六";
				break;
				}
				
				document.write(year1+"年"+month1+"月"+date2+"日       "+s);
				
				
			</script>

		</div>
		<div class="goout" >
			<a href="">退出系统</a>
		</div>

	</div>

</body>
</html>
