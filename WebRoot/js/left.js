var nav_set= document.getElementById('navSet');
var nav_list = nav_set.getElementsByTagName('span');

/*nav_list.onclick=function(){
	var count_p=nav_list.getElementsByTagName("p");
	alert(count_p.length);
	nav_list.style.height="50px";
}*/
for(var i=0;i<nav_list.length;i++){
	nav_list[i].tempvalue=0;
	nav_list[i].onclick=function(){
		var temp=this;
		var count_p=this.getElementsByTagName("p");
		if(this.tempvalue==0){
			var p_height=count_p[1].offsetHeight;
			var basic_height=count_p[0].offsetHeight;
			this.style.height=basic_height+p_height*(count_p.length-1)+"px";
			this.tempvalue=1;
		}else{
			
			this.style.height="35px";
			this.tempvalue=0;
		}
		for(var j=1;j<count_p.length;j++){
			count_p[j].onclick=function(){
				temp.tempvalue=0;
			}
		}
	}
}
function addHeight(element){
	var count_p=element.getElementsByTagName("p");
	for(var j=0;j<count_p.length;j++){
		count_p[j].style.display="block";
	}
	nav_list[i].style.height=10*count_p.length;
}
