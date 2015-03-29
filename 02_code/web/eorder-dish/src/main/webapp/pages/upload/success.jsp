<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

上传成功：
<p />

文件名:
<s:property value="newFileName" />
<br />
文件类型：
<s:property value="fileContentType" />
<br />
文件描述：
<s:property value="description" />