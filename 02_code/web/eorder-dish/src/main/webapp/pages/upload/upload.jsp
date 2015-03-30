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
<script type="text/javascript" src="../resources/js/upload.js"></script>

<div class="formbody">
	<div id="usual1" class="usual">
		<div id="tab2" class="tabson">
			<s:actionerror />
			<form name="uploadForm" id="uploadForm" enctype="multipart/form-data">
				<s:hidden id="newFileName" name="newFileName" />
				<ul class="forminfo">
					<li><s:file name="file" label="上传的文件" /></li>
					<li><label>&nbsp;</label><input name="" type="button" class="scbtn"
						onclick="uploadFile();" value='<s:text name="upload" />' /><label>&nbsp;</label><input
						name="" type="button" class="scbtn" onclick="closeWind();"
						value='<s:text name="confirm" />' /></li>
					<s:if test="null != newFileName">
						<li><img style="width: 348px; height: 348px; padding: 5px;"
							src='../resources/images/dish/<s:property value="newFileName" />'></li>
					</s:if>
				</ul>
			</form>
			<div class="operatemessage">
				<s:property value="message" />
			</div>
		</div>
	</div>
</div>