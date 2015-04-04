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
<base target="_self">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/upload.js"></script>

<div class="formbody">
	<div>
		<form name="uploadForm" id="uploadForm" enctype="multipart/form-data">
			<s:hidden id="newFileName" name="newFileName" />
			<ul class="forminfo">
				<li><label>选择图片</label> <s:file name="file" /></li>
				<li><label>上传的图片</label> <s:if test="null != newFileName">
						<img id="newPic" style="width: 348px; height: 348px; padding: 5px;"
							src='../resources/images/dish/<s:property value="newFileName" />'>
					</s:if> <s:else>
						<img style="width: 348px; height: 348px; padding: 5px;"
							src='../resources/images/default_catgory.png'>
					</s:else></li>
				<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
					type="button" class="btn" onclick="closeWind();"
					value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
					name="" type="button" class="btn" onclick="uploadFile();"
					value="<s:text name='upload' />" /></li>
			</ul>
		</form>
	</div>
</div>
