var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TT_TOKEN");//从cookie获取数据 token
		if(!_ticket){
			return ;
		}
		$.ajax({
			//http://localhost:8084/user/token/123?callback=fun
			url : "http://localhost:8088/user/token/" + _ticket,//首先定义个fun(),再发送请求的时候带上?callback=fun
			dataType : "jsonp",
			type : "GET",
			success : function(data){//相当于fun
				if(data.status == 200){
					var username = data.data.username;/*<a href=\"http://localhost:8088/user/logout.html\" 
*/					var html = username + "，欢迎来到淘淘！<a class=\"link-logout\" id=\"logout\">[退出]</a>";
					$("#loginbar").html(html);
					//登出发送ajax请求
					$("#logout").click(function () {
					   $.ajax({
					       url : "http://localhost:8088/user/logout/" + _ticket,
					       dataType : "jsonp",
					       type : "GET",
					       error: function(request) {  //失败
					           alert("登出失败！");
					       },
					       success: function(data) {  //成功
					       if (data.status == 200){
					           alert("登出成功！");
					           window.location.href="http://localhost:8082"
					       }
					   }
					 });
					});
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});