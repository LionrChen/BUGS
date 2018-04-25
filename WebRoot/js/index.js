$(function(){
	$.ajax({
            type: "get",
            url: "getCartInfoServlet",
            processData: false,
            contentType: false,
            dataType:"text",
            success:function(t){
                console.log(t);
                var arrayTemp = t.split(",");
                setCartInfo(arrayTemp);
            }

    });
    
    function setCartInfo(CartInfoArray){
    	$("#cartItemsPayment").html(CartInfoArray[0]);
    	$("#cartItemsNum").html(CartInfoArray[1]);
    };
	
	
	
})