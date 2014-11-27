<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path; 
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> ### - Banner</title>
		<base target="main">
<link href="../resources/css/basic.css" rel="stylesheet" type="text/css">
<link href="../resources/css/banner.css" rel="stylesheet" type="text/css">
	</head>
	
  	<body>
  	<table id="banner" border="0" cellpadding="0" cellspacing="0">
<tr>
<td id="banner_left"><img src="../resources/image/logo.gif"></td>
<td id="banner_right">
<table id="banner_right_content" border="0" cellpadding="0" cellspacing="0">
<tr>
<td>Hi Admin, welcome to use this system!</td>
<td><a href="<c:url value="/j_spring_security_logout" />" target="_parent"> Logout</a></td></tr>
</table>
</td>
</tr>
</table>
  	</body>
</html>