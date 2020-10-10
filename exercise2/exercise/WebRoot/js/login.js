function changeImg() {
    //需要让每次请求的ur1都发生变化。否则服务器会认为访问的是同一张图片，就不会刷新请求了
    document.getElementById("vccodeImg").src ="createVerifyImage.do?t="+Math.random();
}

 
 
 