<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<script type="text/javascript" src="../resources/js/menu.js"></script>

<div style="background: #f0f9fd;">
	<dl class="leftmenu">
		<!--左侧菜单 begin-->
		<s:hidden id="currentFunctionDesc" name="currentFunctionDesc" />
		<s:iterator value="menuList" id="banner">
			<dd>
				<!--菜单头-->
				<div class="title">
					<span><img src="<%=basePath%>/resources/images/leftico01.png" /></span>
					<s:property value="#banner.functionName" />
					<input type="hidden"
						id='menuTitle<s:property value="#banner.functionDesc" />'>
				</div>
				<ul class="menuson">
					<!--菜单内容-->
					<li><input type="hidden"
						id='myText<s:property value="#banner.functionDesc" />'><cite></cite><a
						href='<%=basePath%><s:property value="#banner.link" />'><s:property
								value="#banner.functionName" /></a><i></i></li>

				</ul>
			</dd>
		</s:iterator>
		<!--左侧菜单 end-->
	</dl>
</div>