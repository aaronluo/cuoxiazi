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

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
%>
<link type="text/css" href="<%=basePath%>/resources/css/style.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/cloud.js"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>

<script type="text/javascript">
	// 回车提交表单（登录）
	$(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				$('#loginForm').submit();//处理事件 
			}
		}
	});
</script>

</head>

<body
	style="background-color: #ee9154; background-image: url(<%=basePath%>/resources/images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div class="logintop">
		<span><s:text name="login_info" /></span>
	</div>

	<div class="loginbody">
		<span class="systemlogo"></span>

		<div class="loginbox">
			<ul>
				<form class="eorder-form-login" id="loginForm"
					action="<c:url value='/j_spring_security_check'/>" method="post"
					accept-charset="utf-8" role="form">
					<li><input name="j_username" id="username" type="text"
						class="loginuser" onclick="JavaScript:this.value=''" /></li>
					<li><input name="j_password" id="password" type="password"
						class="loginpwd" onclick="JavaScript:this.value=''" /></li>
					<li><input type="submit" class="loginbtn"
						value="<s:text name='login' />" onclick="javascript:loginForm.submit();" /><label><input
							name="" type="checkbox" value="" checked="checked" /> <s:text
								name="remember_password" /></label><label><a href="#"><s:text
									name="forget_password" /></a></label></li>
					<li><div class="row" style="height: 120px">
							<div class="eorder-form-login">
								<s:actionmessage />

								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
									<font color="red"> <s:text name="login_failure" /><br /> <br />
										<s:text name="login_failure_reason" /> <c:out
											value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
									</font>
								</c:if>
							</div>
						</div></li>
				</form>
			</ul>
		</div>
	</div>

	<div class="loginbm">
		<s:text name="copyright" /> 2015 <a href="http://www.innovaee.com">innovaee.com</a> <s:text name="copyright_info" />
	</div>
</body>
</html>