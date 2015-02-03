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
<script src="../resources/js/prototype.lite.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<!--左侧菜单 -->
<div class="col-md-2">
    <div class="panel-group" id="eorder_menu" role="tablist"
        aria-multiselectable="true">
        <s:iterator value="menulist" id="banner">
            <!-- 用户权限管理模块 -->
            <div class="panel panel-default">
                <!--菜单头-->
                <div class="panel-heading" role="tab" id="usr_mgt">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#eorder_menu" href="#usr_mgt_list"
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
                                    target="main"><s:property value="#subBanner.functionName" /></a>
                            </s:iterator>

                        </div>
                    </div>
                </div>
            </div>
        </s:iterator>
    </div>
</div>
<script type="text/javascript">
    var contents = document.getElementsByClassName('content');
    var toggles = document.getElementsByClassName('type');
    var myAccordion = new fx.Accordion(toggles, contents, {
        opacity : true,
        duration : 400
    });
    myAccordion.showThisHideOpen(contents[0]);
</script>
</html>