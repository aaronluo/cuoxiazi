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
		<s:form id="userForm" action="doRemove" theme="simple">
			<table cellspacing="0">
				<tr>
					<th></th>
					<th>用户名称</th>
					<th>用户密码</th>
					<th>名称</th>
					<th>用户状态</th>
					<th>用户注册日期</th>
					<th>用户失效日期</th>
					<th>密码失效日期</th>
					<th>用户邮箱</th>
					<th>操作</th>
				</tr>
				
				<s:iterator value="users">
					<tr>
						<td><input type="checkbox" name="userName" value='<s:property value="userName" />' /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="userPassword" /></td>
						<td><s:property value="reportEntityName" /></td>
						<td><s:property value="userEnable" /></td>
						<td><s:property value="userRegisterDate" /></td>
						<td><s:property value="userExpirationDate" /></td>
						<td><s:property value="userPasswdExpDate" /></td>
						<td><s:property value="userEmail" /></td>
						<td><a href='<s:url action="doEdit"><s:param name="userName" value="userName" /></s:url>'> 修改 </a> &nbsp; <a
							href='<s:url action="doRemove"><s:param name="userName" value="userName" /></s:url>'> 删除 </a></td>
					</tr>
				</s:iterator>
			</table>
			<a href="doEdit.action">增加用户</a>
		</s:form>
	</div>
</body>
</html>