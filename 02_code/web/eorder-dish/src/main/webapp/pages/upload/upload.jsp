<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<script type="text/javascript" src="../resources/js/upload.js"></script>
<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="dish_manage" /></a></li>
	</ul>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div id="tab2" class="tabson">
			<s:actionerror />
			<form name="uploadForm" id="uploadForm" enctype="multipart/form-data">
				<s:hidden id="newFileName" name="newFileName" />
				<ul class="seachform">
					<li><s:file name="file" label="选择上传的文件" /></li>
					<li><s:textfield name="description" label="文件描述" /></li>
					<li><label>&nbsp;</label><input name="" type="button" class="scbtn"
						onclick="uploadFile();" value='<s:text name="upload" />' /><label>&nbsp;</label><input
						name="" type="button" class="scbtn" onclick="closeWind();"
						value='<s:text name="confirm" />' /></li>
				</ul>
			</form>
			<div class="operatemessage">
				<s:property value="message" />
			</div>
			<h1></h1>
			<s:if test="null != newFileName">

	上传成功：

	文件名:
	<s:property value="newFileName" />
				<br />
				<img style="width: 348px; height: 348px; padding: 5px;"
					src='../resources/images/dish/<s:property value="newFileName" />'>
				<br /> 文件类型：
	<s:property value="fileContentType" />
				<br /> 文件描述：
	<s:property value="description" />
			</s:if>
		</div>
	</div>
</div>