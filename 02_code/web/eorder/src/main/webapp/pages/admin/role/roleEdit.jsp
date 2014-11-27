<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Function</title>
</head>
<body>
	<h2>
		<s:if test="null == role">
            新增角色
            <s:form action="doStore">
				<s:textfield name="role.roleName" label="角色名称" cssStyle="width: 300px;" />
				<s:textfield name="role.roleDesc" label="角色简称" cssStyle="width: 300px;" />
				<s:textfield name="role.roleEnable" label="角色状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:if>
		<s:else>
            修改角色
            <s:form action="doUpdate">
				<s:textfield name="role.roleName" label="角色名称" cssStyle="width: 300px;" />
				<s:textfield name="role.roleDesc" label="角色简称" cssStyle="width: 300px;" />
				<s:textfield name="role.roleEnable" label="角色状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:else>
	</h2>

</body>
</html>