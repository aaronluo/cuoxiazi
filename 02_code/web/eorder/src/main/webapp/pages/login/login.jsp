<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="shortcut icon" href="../resources/images/favico_32.ico"
	type="image/x-icon" />

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<link type="text/css" href="../resources/css/style.css" rel="stylesheet" />
<script type="text/javascript" src="../resources/js/jquery.js"></script>
<script type="text/javascript" src="../resources/js/cloud.js"></script>
<script type="text/javascript" src="../resources/js/login.js"></script>
<title><s:text name="eorder" /></title>
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
		<s:text name="copyright" />
		2015 <a href="http://www.innovaee.com">innovaee.com</a>
		<s:text name="copyright_info" />
	</div>
</body>
</html>