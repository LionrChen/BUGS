$(function(){
	$("#selectall").change(function(){
		if($(this).is(':checked')){
			$(".select").prop("checked",true);
		}else{
			$(".select").prop("checked",false);
		}
	});
	
	$(".select").on("change",function(){
		setSeletctAll();
	});
	
	function setSeletctAll(){
		var isSelectCount = true;
		$(".select").each(function(){
			if(!$(this).is(':checked')){
				isSelectCount = false;
			}
		});
		if(isSelectCount){
			$("#selectall").prop("checked",true);
		}else{
			$("#selectall").prop("checked",false);
		}
	};
	
	$("#deletechecked").on("click",function(){
		var urlString = 'customerDeleteOrderServlet?action=batch';
		var theFirst = true;
		$(".select").each(function(){
			if(theFirst){
				urlString += "id="+$(this).val();
				theFirst = false;
			}else{
				urlString += "&id="+$(this).val();
			}		
		})
		console.log(urlString);
	});
});
