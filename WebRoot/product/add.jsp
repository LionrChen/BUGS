<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
	.title{
		background-color:  #96B97D;
		color: #FFFFFF;
		padding: 20px 0px 20px 20px;
	}
	*{
		margin: 0px;
		padding: 0px;
	}
	.addbook{
		margin-top:5px;
		height: auto;
		width: auto;
		background-color: #F6F4F0;
	}
	.bookinfo{
		width: 768px;
		min-height: 710px;
		margin: 0px auto;
		padding: 20px;
		margin-top:10px;
		margin-bottom: 10px;
		border-left: 1px solid #96B97D;
	}
	.bookinfo .inputinfo{
		height: 50px;
		margin: 10px;
	}
	.inputinfo span{
		display: inline-block;
		width: 100px;
		color: #96B97D;
		font-size: 17px;
		
	}
	.inputinfo input{
		outline: none;
		line-height: 30px;
		border: 1px solid #96B97D;
		border-radius: 3px;
	}
	.inputinfo input:hover,input:focus,select:focus{
		box-shadow: 0px 0px 4px #96B97D;
	}
	.inputinfo #name{
		width: 400px;
	}
	.inputinfo #price{
		width: 100px;
	}
	.inputinfo #pnum{
		width: 100px;
	}
	.inputinfo select{
		outline: none;
		height: 30px;
		width: 100px;
		border: 1px solid #96B97D;
		border-radius: 3px;
	}
	.choiceFile{
		height: 50px;
		margin: 10px;
	}
	.choiceFile span{
		display: inline-block;
		width: 100px;
		color: #96B97D;
		font-size: 17px;
	}
	.choiceFile input{
		outline: none;
		line-height: 30px;
		border: 0px;
		border-radius: 0px;
		
	}
	.choiceFile input:focus{
		box-shadow: 0px;
	}
	.textinfo{
		height: 120px;
		margin: 5px;
		color: #96B97D;
	}
	.textinfo span{
		display: block;
		width: 100px;
		float: left;
		font-size: 17px;
		margin-left: 5px;
	}
	.textinfo textarea{
		outline: none;
		line-height: 30px;
		height: 100px;
		width: 500px;
		font-size: 15px;
		border: 1px solid #96B97D;
		box-shadow: 0px 0px 3px #96B97D;
		
		border-radius: 3px;
	}
	.textinfo textarea:focus textarea:hover{
		box-shadow: 0px 0px 4px #96B97D;
	}
	.buttons{
		padding-top: 100px;
		height: 40px;
		text-align: center;
	}
	.buttons input{
		
		background-color: #96B97D;
		color: #FFFFFF;
		width: 100px;
		font-size: 15px;
		line-height: 35px;
		margin: 5px;
		border: 1px solid #96B97D;
		box-shadow: 0px 0px 3px #96B97D;
		border-radius: 3px;
	}
	.buttons input:hover{
		box-shadow: 0px 0px 5px #96B97D;
	}
</style>
</head>
<body>
	<div class="addbook">
		<div class="title">
			<p>商品添加</p>
		</div>
		<div class="bookinfo">
			<form action="${pageContext.request.contextPath}/addBookServlet" method="post" enctype="multipart/form-data">
				<div class="inputinfo">
					<span>商品名称</span>
					<input type="text" id="name" name="name"  value=${book.name } ></input>
				</div>
				<div class="inputinfo">
					<span>商品价格</span>
					<input type="text" id="price" name="price" value=${book.price } ></input> ￥
				</div>
				<div class="inputinfo">
					<span>商品数量</span>
					<input type="text" id="pnum" name="pnum"  value=${book.pnum } ></input> 本
				</div>
				<div class="inputinfo">
					<span>商品类别</span>
					<select name="category">
							<option>${book.category ==null?'--选择商品类别--': book.category}</option>
							<option  value="小说">小说</option>
							<option  value="文学">文学</option>
							<option  value="计算机">计算机</option>
							<option  value="艺术">艺术</option>
							<option  value="体育">体育</option>
							<option  value="社会科学">社会科学</option>
							<option  value="人文">人文</option>
							<option  value="工具书">工具书</option>
							<option  value="管理">管理</option>
					</select>
				</div>
				<div class="choiceFile">
					<span>商品图片</span>
					<input id="file" type="file" name="upload" />
				</div>
				<div class="textinfo">
					<span>商品描述</span>
					<textarea  name="description"  cols="70" rows="4" >${book.description }
					</textarea>
				</div>
				<div class="buttons">
					<input type="submit" value="确定"/>&nbsp;&nbsp;&nbsp;
					<input type="reset"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="返回"/>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>