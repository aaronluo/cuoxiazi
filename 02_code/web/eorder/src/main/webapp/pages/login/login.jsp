<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
	<script src="../resources/js/jquery.min.js" /></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</head>

<script type="text/javascript">
// 回车提交表单（登录）
$(function(){ 
	document.onkeydown = function(e){ 
		var ev = document.all ? window.event : e; 
		if(ev.keyCode==13) { 
			$('#loginForm').submit();//处理事件 
		} 
	} 
});

</script>

<body onload="document.loginForm.j_username.focus();">
	<!--head navigation bar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<!-- 商家名称-->
			<a class="navbar-brand">eOrder订餐系统</a>
			<!--自适应mobile设备菜单开关, Web后台可以不管-->
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
	</div>

	<!--页面主体部分-->
	<div class="container">
		<!-- err msg -->
		<div class="row" style="height: 120px"></div>
		<!-- 登录框 -->
		<div class="row">
			<form class="eorder-form-login" id="loginForm" action="<c:url value='/j_spring_security_check'/>"
				method="post" accept-charset="utf-8" role="form">				
				<input type="text" name="j_username" id="username" tabindex="1" value="admin"
					class="form-control eorder-input" placeholder="用户名" required autofocus />

				<input type="password" name="j_password" id="password" value="admin"
					tabindex="1" class="form-control eorder-input" placeholder="密  码" required />

				<a href="#" onclick="javascript:loginForm.submit();" class="btn btn-default btn-block eorder-btn-login"> 登录 </a>
			</form>
			<s:actionmessage />
			<c:if test="${not empty param.login_error}">
				<font color="red"> Your login attempt was not successful, try again.<br />
					<br /> Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
				</font>
			</c:if>
		</div>
	</div>

	<!--页面底部footer-->
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text pull-left">Powered by Bootstrap</p>
		</div>
	</div>
</body>
</html>