<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Function</title>
</head>
<body>
	<h2>
		<s:if test="null == user">
            新增功能
            <s:form action="doStore">
				<s:textfield name="user.userName" label="用户名称" cssStyle="width: 300px;" />
				<s:textfield name="user.userPassword" label="用户密码" cssStyle="width: 300px;" />
				<s:textfield name="user.reportEntityName" label="名称" cssStyle="width: 300px;" />
				<s:textfield name="user.userEnable" label="用户状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:if>
		<s:else>
            修改功能
            <s:form action="doUpdate">
				<s:textfield name="user.userName" label="用户名称" cssStyle="width: 300px;" />
				<s:textfield name="user.userPassword" label="用户密码" cssStyle="width: 300px;" />
				<s:textfield name="user.reportEntityName" label="名称" cssStyle="width: 300px;" />
				<s:textfield name="user.userEnable" label="用户状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:else>
	</h2>

</body>
</html>