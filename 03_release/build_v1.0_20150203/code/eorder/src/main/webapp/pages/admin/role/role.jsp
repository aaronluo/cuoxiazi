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
<script type="text/javascript" src="../resources/js/optiontransferselect.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>

<script type="text/javascript">
    $(function() {
        //移到右边
        $('#add').click(function() {
            //获取选中的选项，删除并追加给对方
            $('#myFunctions option:selected').appendTo('#leftFunctions');
        });
        //移到左边
        $('#remove').click(function() {
            $('#leftFunctions option:selected').appendTo('#myFunctions');
        });
        //全部移到右边
        $('#add_all').click(function() {
            //获取全部的选项,删除并追加给对方
            $('#myFunctions option').appendTo('#leftFunctions');
        });
        //全部移到左边
        $('#remove_all').click(function() {
            $('#leftFunctions option').appendTo('#myFunctions');
        });
        //双击选项
        $('#myFunctions').dblclick(function() { //绑定双击事件
            //获取全部的选项,删除并追加给对方
            $("option:selected", this).appendTo('#leftFunctions'); //追加给对方
        });
        //双击选项
        $('#leftFunctions').dblclick(function() {
            $("option:selected", this).appendTo('#myFunctions');
        });
    });

    function save() {
        $("[name='saveForm']").attr("action", "save.action");
        $("[name='saveForm']").attr("method", "post");
        $("[name='saveForm']").submit();
    }

    function update() {
        var myList = new Array();

        $("#myFunctions").each(function() {
            $(this).children("option").each(function() {
                myList.push($(this).val());
            });
        });

        var leftList = new Array();
        $("#leftFunctions").each(function() {
            $(this).children("option").each(function() {
                leftList.push($(this).val());
            });
        });

        $("#myFunctionsArray").val(myList);
        $("#leftFunctions").val(leftList);

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
                    <h3 class="page-header">角色管理</h3>
                </div>

                <!--列表表格-->
                <div class="row">
                    <div class="col-md-3">
                        <s:if test="null == roleId || 0 == roleId">
                            <h4>新增角色</h4>
                            <s:form class="eorder-form-usr" id="saveForm" action="doStore">
                                <s:hidden id="functionId" name="functionId" />
                                <s:hidden id="myFunctionsArray" name="myFunctionsArray" />
                                <s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
                                <input type="text" id="roleName" name="roleName"
                                    class="form-control eorder-input" placeholder="角色名称"
                                    value="${roleName}" />
                                <input type="text" id="roleDesc" name="roleDesc"
                                    class="form-control eorder-input" placeholder="角色描述"
                                    value="${roleDesc}">
                                <a href="#" onclick="save();"
                                    class="btn btn-default btn-block eorder-btn-login">创建新角色</a>
                            </s:form>
                        </s:if>
                        <s:else>
                            <h4>修改角色</h4>
                            <s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
                                <s:hidden id="roleId" name="roleId" value="%{roleId}" />
                                <s:hidden id="functionId" name="functionId" />
                                <s:hidden id="myFunctionsArray" name="myFunctionsArray" />
                                <s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
                                <input type="text" name="roleName" id="roleName" name="roleName"
                                    class="form-control eorder-input" placeholder="角色名称"
                                    value="${roleName}" />
                                <input type="text" id="roleDesc" name="roleDesc"
                                    class="form-control eorder-input" placeholder="角色描述"
                                    value="${roleDesc}">
                                <a href="#" onclick="update();"
                                    class="btn btn-default btn-block eorder-btn-login">修改角色信息</a>
                            </s:form>
                        </s:else>

                        <s:fielderror />
                        <s:if test=" null != message && '' != message">
                            <h4>
                                <s:property value="message" />
                            </h4>
                        </s:if>
                    </div>

                    <div class="col-md-3">
                        <h4>已分配功能</h4>
                        <select id="myFunctions" name="myFunctions" multiple
                            class="form-control eorder-multi-sel">
                            <s:iterator value="myFunctions" id="function">
                                <option value="${function.functionId}">${function.functionName}</option>
                            </s:iterator>
                        </select>
                    </div>
                    <div class="col-md-2 text-center">
                        <button id="remove_all" class="btn btn-default eorder-btn-arrow"
                            style="margin-top: 42px">
                            <span class="glyphicon glyphicon-backward"></span>
                        </button>
                        <br />
                        <button id="remove" class="btn btn-default eorder-btn-arrow">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button>
                        <br />
                        <button id="add" class="btn btn-default eorder-btn-arrow">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                        <br />
                        <button id="add_all" class="btn btn-default eorder-btn-arrow">
                            <span class="glyphicon glyphicon-forward"></span>
                        </button>
                    </div>
                    <div class="col-md-3">
                        <h4>可分配功能</h4>
                        <select id="leftFunctions" name="leftFunctions" multiple
                            class="form-control eorder-multi-sel">
                            <s:iterator value="leftFunctions" id="function">
                                <option value="${function.functionId}">${function.functionName}</option>
                            </s:iterator>
                        </select>
                    </div>
                </div>

                <br>

                <div class="row">
                    <h3 class="page-header">角色列表</h3>
                </div>

                <!--列表表格-->
                <div class="row">
                    <div class="col-md-10">
                        <table class="table table-condensed">
                            <s:form id="roleForm" action="doRemove" theme="simple">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>角色名称</th>
                                        <th>角色描述</th>
                                        <th>功能列表</th>
                                        <th>编辑</th>
                                        <th>删除</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="rolevos">
                                        <tr>
                                            <td><s:hidden id="roleId" name="roleId" value="%{roleId}" /></td>
                                            <td><s:property value="roleName" /></td>
                                            <td><s:property value="roleDesc" /></td>
                                            <td><s:property value="functionName" /></td>
                                            <td><a
                                                href='<s:url action="doEdit"><s:param name="roleId" value="roleId" /></s:url>'>
                                                    <span class="glyphicon glyphicon-edit">
                                            </a></span></td>
                                            <td><a
                                                href='<s:url action="remove"><s:param name="roleId" value="roleId" /></s:url>'>
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