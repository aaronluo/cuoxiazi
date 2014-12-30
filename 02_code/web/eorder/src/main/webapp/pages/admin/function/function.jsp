<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="../resources/js/jquery.min.js" /></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>

<script type="text/javascript">
    function save() {
        $("[name='saveForm']").attr("action", "save.action");
        $("[name='saveForm']").attr("method", "post");
        $("[name='saveForm']").submit();
    }

    function update() {
        $("[name='updateForm']").attr("action", "update.action");
        $("[name='updateForm']").attr("method", "post");
        $("[name='updateForm']").submit();
    }
</script>
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
                            <s:property value="loginName" />
                            &nbsp;&nbsp; <a href="<c:url value='/j_spring_security_logout' />"
                                target="_parent"> 退出系统</a>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                            &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
                        </p></li>
                </ul>
            </div>
        </div>
    </div>

    <!--页面主体部分-->
    <div class="container-fluid">
        <div class="row">
            <!--左侧菜单 begin-->
            <div class="col-md-2">
                <div class="panel-group" id="eorder_menu" role="tablist"
                    aria-multiselectable="true">
                    <s:iterator value="menulist" id="banner">
                        <!-- 用户权限管理模块 -->
                        <div class="panel panel-default">
                            <!--菜单头-->
                            <div class="panel-heading" role="tab" id="usr_mgt">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#eorder_menu" href="#"
                                        aria-expanded="true" aria-controls="usr_mgt_list"> <s:property
                                            value="#banner.functionName" />
                                    </a>
                                </h4>
                            </div>
                            <div class="panel-collapse collapse in" id="usr_mgt_list" role="tabpanel"
                                aria-labelledby="usr_mgt">
                                <!--菜单内容-->
                                <div class="panel-body">
                                    <div class="list-group">
                                        <s:iterator value="#banner.list" id="subBanner">
                                            <a class="list-group-item"
                                                href='<%=basePath%><s:property value="#subBanner.link" />'
                                                target="_self"><s:property value="#subBanner.functionName" /></a>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </s:iterator>
                </div>
            </div>
            <!--左侧菜单 end-->

            <div class="col-md-9">
                <!--导航标题栏-->
                <div class="row">
                    <h3 class="page-header">权限管理</h3>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <s:if test="null == functionId || 0 == functionId">
                            <h4>新增权限</h4>
                            <s:form class="eorder-form-usr" id="saveForm">
                                <input type="text" id="functionName" name="functionName"
                                    class="form-control eorder-input" placeholder="权限名称"
                                    value="${functionName}" />
                                <input type="text" id="functionDesc" name="functionDesc"
                                    class="form-control eorder-input" placeholder="权限描述"
                                    value="${functionDesc}" />
                                <input type="text" id="functionPath" name="functionPath"
                                    class="form-control eorder-input" placeholder="权限路径"
                                    value="${functionPath}" />
                                <input type="text" id="functionParent" name="functionParent"
                                    class="form-control eorder-input" placeholder="上级权限"
                                    value="${functionParent}" />
                                <input type="text" id="functionOrder" name="functionOrder"
                                    class="form-control eorder-input" placeholder="权限排序"
                                    value="${functionOrder}" />
                                <a href="#" onclick="save();"
                                    class="btn btn-default btn-block eorder-btn-login">创建新权限</a>
                            </s:form>
                        </s:if>
                        <s:else>
                            <h4>修改权限</h4>
                            <s:form class="eorder-form-usr" id="updateForm">
                                <s:hidden id="functionId" name="functionId" value="%{functionId}" />
                                <input type="text" id="functionName" name="functionName"
                                    class="form-control eorder-input" placeholder="权限名称"
                                    value="${functionName}" />
                                <input type="text" id="functionDesc" name="functionDesc"
                                    class="form-control eorder-input" placeholder="权限描述"
                                    value="${functionDesc}" />
                                <input type="text" id="functionPath" name="functionPath"
                                    class="form-control eorder-input" placeholder="权限路径"
                                    value="${functionPath}" />
                                <input type="text" id="functionParent" name="functionParent"
                                    class="form-control eorder-input" placeholder="上级权限"
                                    value="${functionParent}" />
                                <input type="text" id="functionOrder" name="functionOrder"
                                    class="form-control eorder-input" placeholder="权限排序"
                                    value="${functionOrder}" />
                                <a href="#" onclick="update();"
                                    class="btn btn-default btn-block eorder-btn-login">修改权限信息</a>
                            </s:form>
                        </s:else>

                        <br>
                        <s:fielderror />
                        <s:if test=" null != message && '' != message">
                            <h4>
                                <s:property value="message" />
                            </h4>
                        </s:if>
                    </div>
                </div>

                <br>

                <div class="row">
                    <h3 class="page-header">权限列表</h3>
                </div>

                <!--列表表格-->
                <div class="row">
                    <div class="col-md-10">
                        <table class="table table-condensed">
                            <s:form id="functionForm" action="doRemove" theme="simple">
                                <thead>
                                    <tr>
                                        <th>权限ID</th>
                                        <th>权限名称</th>
                                        <th>权限描述</th>
                                        <th>权限路径</th>
                                        <th>上级权限ID</th>
                                        <th>上级权限名称</th>
                                        <th>权限排序</th>
                                        <th>编辑</th>
                                        <th>删除</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="functionvos">
                                        <tr>
                                            <td><s:property value="functionId" /></td>
                                            <td><s:property value="functionName" /></td>
                                            <td><s:property value="functionDesc" /></td>
                                            <td><s:property value="functionPath" /></td>
                                            <td><s:property value="functionParent" /></td>
                                            <td><s:property value="functionParentName" /></td>
                                            <td><s:property value="functionOrder" /></td>
                                            <td><a
                                                href='<s:url action="doEdit"><s:param name="functionId" value="functionId" /></s:url>'>
                                                    <span class="glyphicon glyphicon-edit">
                                            </a></span></td>
                                            <td><a
                                                href='<s:url action="remove"><s:param name="functionId" value="functionId" /></s:url>'>
                                                    <span class="glyphicon glyphicon-trash">
                                            </a></span></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </s:form>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--页面底部footer-->
    <div class="navbar navbar-default navbar-fixed-bottom">
        <div class="container">
            <p class="navbar-text pull-left">Powered by eOrder</p>
        </div>
    </div>

</body>
</html>