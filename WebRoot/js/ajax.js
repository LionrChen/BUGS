//创建XMLHttpRequest对象
function  getXMLHttpRequest(){
	 var xhr;
     //window.XMLHttpRequest表示当前浏览器是否内置了XMLHttpRequestXMLHttpRequest对象
     if(window.XMLHttpRequest){
      //IE7+，firefox,chrom.opera,...............
      xhr=new XMLHttpRequest();
     }else{
     //IE6,IE5.....
      xhr=new ActiveXObject("Microsoft.XMLHTTP");
     }
	
     return xhr;
}