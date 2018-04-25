<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script language="javascript">
        var http_request=false;
        function createRequest(url){
            http_request=false;
            if(windows.XMLHttpRequest){
                http_request=new XMLHttpRequest();
                if(http_request.overrideMimeType){
                    http_request.overrideMimeType('text/xml');
                }
            }else if(windows.ActiveXObject){
                try{
                    http_request=new ActiveXObject("Msxm12.XMLHTTP");
                }catch(e){
                    try{
                        http_request=new ActiveXObject("Microsoft.XMLHTTP");
                    }catch(e){

                    }
                }
            }
            if(!http_request){
                alert("不能创建XMLHttpRquest对象实例！");
                return false;
            }
            http_request.onreadystatechange = getRequest;
            http_request.open('POST', url, true);
            http_request.send();
        }
        function getRequest(){
            if(http_request.readyState==4){
                if(http_request.status==200){
                    document.getElementById("error").innerHTML=xmlhttp.responseText;
                }else{
                    alert("您所请求的页面有错误！");
                }
            }
        }
        function userCheck() {
            //var uname = document.myform.username.value;
            //var psw = document.myform.password.value;
            var x=document.forms["login"]["username"].value;
            var y=document.forms["login"]["password"].value;
            if(x==null||x=="") {
                alert("用户名不能为空。");
                //document.myform.uname.focus();
                return false;
            }
            if(y==null||y==""){
                alert("密码不能为空。");
                //document.myform.uname.focus();
                return false;
            }

        }
    </script>
</head>
<body>
	<div class="header">
		 <div class="login_logo">
                    <h2>User Login！</h2>
         </div>
	</div>
	<div class="container">
    <div class="content">
        <div class="loginbox">

            <div class="login-content">
                
               
                <div class="loginbox-title">
                    <h3>&nbsp;&nbsp;管理员登录</h3>
                </div>
                <form name="login" action="loginServlet" method="post"  onsubmit="userCheck()" >
                    <div class="row login-error" id="error_msg">${error_msg }</div>
                    <div class="row">
                        <label class="field">用户名</label>
                        <input type="text" class="input-text-user input-click" name="username" id="username">
                    	<span class="error_msg" id="error_msg"></span>
                    </div>
                    <div class="row">
                        <label class="field">密&nbsp;&nbsp;&nbsp;码</label>
                        <input type="password" class="input-text-password input-click" name="password" id="password">
                    	<span class="error_msg" id="error_msg"></span>
                    </div>
                    <div class="btnArea">
                        <input type="submit" class="lobox"value="登录" id="logsub"/>
                    </div>
                    <div class="row tips">
                        <a href="forget.jsp" class="link">忘记密码</a>
                    </div>
                </form>
            </div>
            <div class="go-regist">
                还没有账号？请
                <a href="register.jsp">立即注册</a>
            </div>
        </div>
        <!-- end .content --></div>

    <!-- end .container --></div>
 </body>
 <div class="footer">
 
 </div>
 </html>