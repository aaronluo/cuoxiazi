<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path;
%>
<script type="text/javascript">
	// 回车提交表单（登录）
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})

		// 进入页面后自动打开菜单，选中子菜单项
		var currentToolbar = $("#currentToolbar").val();
		$(".nav li").each(function() {
			$(this).children("a").each(function() {
				if (currentToolbar == $(this).attr("title")) {
					$(this).addClass("selected");
					return;
				}
			});
		});
	});
</script>

<div style="background: url(../resources/images/topbg.gif) repeat-x;">
	<s:hidden id="currentToolbar" name="currentToolbar" />
	<div class="topleft">
		<a href="main.html" target="_parent"><img
			src="../resources/images/logo.png" /></a>
	</div>

	<ul class="nav">
		<!--头部工具栏 begin-->
		<s:iterator value="toolbarList" id="banner">
			<li><a title='<s:property value="#banner.functionDesc" />'
				href='<%=basePath%><s:property value="#banner.list[0].link" />'><img
					src='../resources/images/<s:property value="#banner.functionPicture" />' />
					<h2>
						<s:property value="#banner.functionName" />
					</h2></a></li>
		</s:iterator>
		<!--头部工具栏 end-->
	</ul>

	<div class="topright">
		<ul>
			<li><span><img src="../resources/images/help.png" class="helpimg" /></span><a
				href="#"><s:text name="help" /></a></li>
			<li><a href="#"><s:text name="about" /></a></li>
			<li><a href="<c:url value='/j_spring_security_logout' />"
				target="_parent"><s:text name="logout_system" /></a></li>
		</ul>
		<div class="user">
			<span><s:property value="loginName" /></span>
		</div>
	</div>

</div>