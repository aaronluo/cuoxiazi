<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/category.js"></script>

<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="category.action"><s:text name="category_manage" /></a></li>
			<li><s:text name="add_category" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form id="saveForm" name="saveForm" action="save">
				<s:hidden id="categoryPicture" name="categoryPicture" />
				<ul class="forminfo">
					<li><label><s:text name="category_name" /></label><input
						id="categoryName" name="categoryName" type="text" class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="category_picture" /></label><img
						id="categoryPic" style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images<s:property value="categoryPicture" />'></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="save();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
	</div>
</s:if>
<s:else>
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="category.action"><s:text name="category_manage" /></a></li>
			<li><s:text name="edit_category" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form id="updateForm" name="updateForm" action="update">
				<s:hidden id="id" name="id" value="%{id}"></s:hidden>
				<s:hidden id="categoryPicture" name="categoryPicture" />
				<ul class="forminfo">
					<li><label><s:text name="category_name" /></label><input
						id="categoryName" name="categoryName" type="text"
						value='<s:property value="categoryName" />' class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="category_picture" /></label><img
						id="categoryPic" style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images<s:property value="categoryPicture" />'></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnUpdate"
						type="button" class="btn" onclick="updateBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="update();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
	</div>
</s:else>