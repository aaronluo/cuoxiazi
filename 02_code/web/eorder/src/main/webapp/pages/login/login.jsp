<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>XBRL Shared Testing Facility</title>
  <link href="../resources/css/loginStyle.css" rel="stylesheet" type="text/css" />
</head>

<body onload="document.loginForm.j_username.focus();">
  <div class="loginLogo">
    <img src="../resources/image/hplogo.jpg" width="43" height="43" alt="hpLogo" />
  </div>
  
  <div class="login">
    <h1>
      <img src="../resources/image/xbrl.gif" alt="XBRL Shared Testing Facility" width="274" height="22" />
    </h1>

    <!-- start login_card j_spring_security_check -->
    <div id="login_card">
      <form id="loginForm" action="<c:url value='/j_spring_security_check'/>" method="post" accept-charset="utf-8">
        
        <!-- start login_params -->
        <div class="login_params">
          <!-- user_name -->
          <div class="param">
            <p>
              <span>User name:<span>*</span></span>
              <label for="user_name"></label>
              <input type="text" name="j_username" id="user_name" value="admin" tabindex="1" />
              <label id="user_name_error" for="user_name" style="color: red"></label>
            </p>
		  </div>

          <!-- password -->
          <div class="param">
            <p>
              <span>Password:<span>*</span></span> <label for="password"></label>
              <input type="text" name="j_password" id="password" value="admin" tabindex="1" style="color: red" />
              <label id="password_error" for="password" style="color: red"></label>
			</p>
	      </div>
	    </div>
	    <!-- end login_params -->

	    <!-- login_submit -->
	    <div class="loginBut">
	      <span>
	        <a href="#">Forget password</a>
	        <a href="#">Help desk</a>
          </span>
          <a href="#">
            <img src="../resources/image/loginBut.gif" alt="login" width="55" height="26" onclick="javascript:loginForm.submit();"/>
          </a>
		</div>
	  </form>
			
      <s:actionmessage />
      <c:if test="${not empty param.login_error}">
        <font color="red">
          Your login attempt was not successful, try again.<br/> <br/>
          Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
        </font>
      </c:if>
    </div>
    <!-- end login_card -->
  </div>
</body>
</html>