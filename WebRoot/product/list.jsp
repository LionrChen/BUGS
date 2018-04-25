<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//点击全选/反选的按钮
	function ckAll() {
		/*
		  分析过程：点击全选，每项按钮必须全部选中，
		                     点击反选，每项按钮都不选中；
		*/
		//查找全选/反选的按钮
		var all = document.getElementById("all");
		//查找每一项的选中按钮(获取所有叫one名称的选项)
		var one = document.getElementsByName("one");
		//all.checked: 全选表示true,反选表示false
		for (var i = 0; i < one.length; i++) {
			one[i].checked = all.checked;
		}


	}
	//点击某一项的按钮
	function ckOne() {
		/*
		分析的思路：
		情况1；所有每项按钮选中，那么全选按钮选中； (每一项的选中状态为true的个数和所有项的个数一致，表示全部选中)
		情况2； 如果全选按钮时选中的，但是其中每项的按钮有一个去掉了选中状态，那么全选按钮必须不选中;
		*/
		var all = document.getElementById("all");
		//查找每一项的选中按钮(获取所有叫one名称的选项)
		var one = document.getElementsByName("one");
		
		var count = 0;
		
		for (var i = 0; i < one.length; i++) {
			if (one[i].checked) {
				//计算选中的个数
				count = count + 1;
			}
		}
		if (count == one.length) {
			//表示全部选中，那么全选按钮必须设置为true
			all.checked = true;
		} else {
			all.checked = false;
		}

	}
	//批量删除
	function DelByBatch(){
	 //1.获取所有选中项的id，所有制传递给servlet,让servlet根据id值进行删除
	 //查找每一项的选中按钮(获取所有叫one名称的选项)
		
		var one = document.getElementsByName("one");
		var result="";
		//1,2,3 
	     for (var i = 0; i < one.length; i++) {
			if (one[i].checked) {
				//获取选中的id(book的id);
				if(i>0){
				result+=",";
				}
				result+=one[i].value;
			}
		}
		
	  window.location.href="${pageContext.request.contextPath}/deleteBookByBatchServlet?ids="+result;
	
	}	
	//单挑删除
	function  delBookById(id,name1){
		if(window.confirm("确定要删除"+name1+"书吗?")){
		
		 window.location.href="${pageContext.request.contextPath}/deleteBookByBatchServlet?ids="+id;
		}
	
	}
	//修改图书信息
	function  editBook(id){
	  //把id传递给servlet,查询指定的图书
	  window.location.href="${pageContext.request.contextPath}/queryBookByIdServlet?id="+id;
	
	}
</script>
</head>
<body>
	<h1
		style="background-color:  #AFD1F4;text-align: center; margin: -10px 10px -10px -10px;padding: 20px 0px 20px 0px;">图书列表</h1>
	<!-- cellspacing:边框的间隙-->
	<table border="1px solid gray" align="center" cellspacing="0px"
		width="90%" style="margin-top: 20px;">
		<tr>
		    <td colspan="4" style="color:red;text-align:center;">${del_msg }</td>
			<td colspan="4" style="text-align:right;"><input type="button"
				value="批量删除" onclick="DelByBatch()"/></td>
		</tr>
		<tr bgcolor="#AFD1F4">
			<td width="9%" align="center"><input type="checkbox"
				onclick="ckAll()" id="all" />全选/反选</td>
			<td width="18%" align="center">图书编号</td>
			<td width="18%" align="center">图书名称</td>
			<td width="12%" align="center">图书价格</td>
			<td width="12%" align="center">图书分类</td>
			<td width="9%" align="center">图书库存</td>
			<td width="9%" align="center">编辑</td>
			<td width="9%" align="center">删除</td>
		</tr>
		<!--items表示要遍历的集合  ，var值表明的是每一次遍历出来的对象进行存储 -->
		<p:forEach items="${booklist }" var="book" varStatus="vs">
			<!-- title属性不再界面显示， 暂存一个数据  -->
			<tr>
				<td align="center"><input type="checkbox" onclick="ckOne()"
					name="one"  value="${book.id }"/></td>
				<!-- ${vs.index}取遍历的索引值，从0开始 -->
				<!-- ${vs.count}计数，从1开始 -->
				<td align="center">${vs.count}</td>
				<td align="center">${book.name }</td>
				<td align="center">${book.price }</td>
				<td align="center">${book.category }</td>
				<td align="center">${book.pnum}</td>
				<td align="center"><img src="images/i_edit.gif" onclick="editBook('${book.id}')" /></td>
				<td align="center"><img src="images/i_del.gif"  onclick="delBookById('${book.id}','${book.name}')"/></td>
			</tr>
		</p:forEach>
	</table>
</body>
</html>