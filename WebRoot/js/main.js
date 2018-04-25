/**
 * Created by ChenSir on 2017/6/22.
 */
//倒计时的对象
var interval;

function startTime() {
    //倒计时，参数1：函数，执行的内容 参数2:隔多长时间执行一次，单位是毫秒
    interval = setInterval("changeTime()", 1000);
}

function changeTime() {
    //查找控件
    var s = document.getElementById("span01");
    //获取内容
    var i = s.innerHTML;
    //每隔1s时间减1
    i = i - 1;
    if (i == 0) {
        window.clearInterval(interval);
        //结束倒计时跳转登录页面
        window.location.href = "login.jsp";
    } else {
        //赋值
        s.innerHTML = i;
    }

}
window.onload= function () {
    startTime();

};
