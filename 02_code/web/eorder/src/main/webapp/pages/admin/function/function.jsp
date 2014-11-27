<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.innovaee.eorder.module.entity.Function"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

table thead tr th {
	border: 1px solid black;
	padding: 3px;
	background-color: #cccccc;
}

table tbody tr td {
	border: 1px solid black;
	padding: 3px;
}
</style>
</head>

<body>
	<div width="1020" style="height: 604px; background-color: #FFFFFF">
		<s:form id="functionForm" action="doRemove" theme="simple">
			<table cellspacing="0">
				<tr>
					<th>功能名称</th>
					<th>功能简称</th>
					<th>功能描述</th>
					<th>功能路径</th>
					<th>父功能</th>
					<th>功能序号</th>
					<th>功能状态</th>
					<th>操作</th>
				</tr>

				<s:iterator value="functions">
					<tr>
						<td><input type="checkbox" name="functionName" value='<s:property value="functionName" />' /></td>
						<td><s:property value="functionDesc" /></td>
						<td><s:property value="functionDisplay" /></td>
						<td><s:property value="functionPath" /></td>
						<td><s:property value="functionParent" /></td>
						<td><s:property value="functionOrder" /></td>
						<td><s:property value="functionEnable" /></td>
						<td><a href='<s:url action="doEdit"><s:param name="functionName" value="functionName" /></s:url>'> 修改 </a> &nbsp; <a
							href='<s:url action="doRemove"><s:param name="functionName" value="functionName" /></s:url>'> 删除 </a></td>
					</tr>
				</s:iterator>
			</table>
			<a href="doEdit.action">增加功能</a>
		</s:form>
	</div>
</body>
</html>