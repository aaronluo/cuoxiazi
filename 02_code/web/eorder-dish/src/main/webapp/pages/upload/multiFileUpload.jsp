<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>upload page</title>
</head>
<body>
	<s:actionerror />
	<s:form action="multiFileUpload" method="post" enctype="multipart/form-data">
		<s:file name="file" label="文件1" />
		<s:file name="file" label="文件2" />
		<s:file name="file" label="文件3" />

		<s:submit value="上传" />
	</s:form>
</body>
</html>