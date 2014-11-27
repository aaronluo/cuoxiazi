<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Function</title>
</head>
<body>
	<h2>
		<s:if test="null == function">
            新增功能
            <s:form action="doStore">
				<s:textfield name="function.functionName" label="功能名称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionDesc" label="功能简称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionDisplay" label="功能描述" cssStyle="width: 300px;" />
				<s:textfield name="function.functionPath" label="功能路径" cssStyle="width: 300px;" />
				<s:textfield name="function.functionParent" label="父功能名称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionOrder" label="功能序号" cssStyle="width: 300px;" />
				<s:textfield name="function.functionEnable" label="功能状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:if>
		<s:else>
            修改功能
            <s:form action="doUpdate">
				<s:textfield name="function.functionName" label="功能名称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionDesc" label="功能简称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionDisplay" label="功能描述" cssStyle="width: 300px;" />
				<s:textfield name="function.functionPath" label="功能路径" cssStyle="width: 300px;" />
				<s:textfield name="function.functionParent" label="父功能名称" cssStyle="width: 300px;" />
				<s:textfield name="function.functionOrder" label="功能序号" cssStyle="width: 300px;" />
				<s:textfield name="function.functionEnable" label="功能状态" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:else>
	</h2>

</body>
</html>