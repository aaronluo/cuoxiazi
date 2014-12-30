<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<title>eOrder</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<!--head navigation bar -->
<div class="navbar navbar-default navbar-static-top">
    <frameset rows="80,*,50" frameborder="no" border="0" framespacing="0"
        id="container">
        <frame src="<%=request.getContextPath()%>/login/header.action" name="topFrame"
            noresize="noresize" frameborder="0" scrolling="no" marginwidth="0"
            marginheight="0" />
        <frameset cols="200,*" id="frame">
            <frame src="<%=request.getContextPath()%>/login/left.action" name="leftFrame"
                noresize="noresize" marginwidth="0" marginheight="0" frameborder="0"
                scrolling="auto" />
            <frame src="<%=request.getContextPath()%>/user/doUser.action" name="main"
                marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" />
        </frameset>
        <frame src='<%=request.getContextPath()%>/login/bottom.action'
            name="bottomFrame" noresize="noresize" frameborder="0" scrolling="no"
            marginwidth="0" marginheight="0" />
    </frameset>

    <noframes>
        <body>
            <p>Your brower can't support frame!</p>
        </body>
    </noframes>
</div>
</html>