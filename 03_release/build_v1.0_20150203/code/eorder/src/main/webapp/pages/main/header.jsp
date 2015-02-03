<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<body>
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
            <!-- 标题栏 -->
            <div class="collapse navbar-collapse navHeaderCollapse">
                <ul class="nav navbar-nav navbar-right">
                    <!--标题栏里面需要展现的item-->
                    <li><p class=navbar-text>
                            当前用户：
                            <s:property value="username" />
                            &nbsp;&nbsp; <a href="<c:url value='/j_spring_security_logout' />"
                                target="_parent"> 退出系统</a>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                            &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                        </p></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>