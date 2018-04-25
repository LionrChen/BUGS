$(
	$("#addToCart").click(function(){
		var num = $("#pnum").val();
		var bookId = $("#bookId").val();
		$(location).attr('href', 'addBookToCartServlet?bookId='+bookId+'&pnum='+num);
	})
);
