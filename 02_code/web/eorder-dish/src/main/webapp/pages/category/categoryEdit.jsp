<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/category.js"></script>

<script type="text/javascript">
	//上传
	function uploadFile() {
		// 取得用户输入的分类
		var oldCategoryName = $("#category\\.name").val();
		var oldCategoryId = $("#category\\.id").val();
		// alert("oldCategoryName: " + oldCategoryName);
		$("#categoryName").val(oldCategoryName);
		$("#categoryId").val(oldCategoryId);

		var filename = $("[name='file']").val();
		//alert("filename: " + filename);
		if ('' == filename) {
			alert('<s:text name="upload_picture_success_rule"/>')
			return;
		}

		$("[name='uploadForm']").attr("action", "uploadImage.action");
		$("[name='uploadForm']").attr("method", "post");
		$("[name='uploadForm']").submit();
	}
</script>

<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="category.action"><s:text name="category_manage" /></a></li>
			<li><s:text name="add_category" /></li>
		</ul>
	</div>

	<div class="rightinfo" style="margin-top: 50px">
		<div class="tablePanel">
			<form id="saveForm" name="saveForm" action="save">
				<s:hidden id="category.picPath" name="category.picPath" />
				<ul class="forminfo">
					<li><label><s:text name="category_name_label" /></label><input
						id="category.name" name="category.name" value="${category.name}"
						type="text" class="dfinput" style="width: 280px;" /></li>
					<li><label><s:text name="upload_label" /></label><input name=""
						id="btn_center" type="button" class="scbtn"
						value='<s:text name="upload_label" />' /></li>
					<li><label><s:text name="category_picture_label" /></label><img
						id="categoryPic" style="width: 280px; height: 280px;"
						src='../resources/images${category.picPath}'></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" style="width: 130px;" onclick="save();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
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

	<div class="rightinfo" style="margin-top: 50px">
		<div class="tablePanel">
			<form id="updateForm" name="updateForm" action="update">
				<s:hidden id="category.id" name="category.id" value="%{category.id}"></s:hidden>
				<s:hidden id="category.picPath" name="category.picPath" />
				<ul class="forminfo">
					<li><label><s:text name="category_name_label" /></label><input
						id="category.name" name="category.name" type="text"
						value='<s:property value="category.name" />' class="dfinput"
						style="width: 280px;" /></li>
					<li><label><s:text name="upload_label" /></label><input name=""
						id="btn_center" type="button" class="scbtn"
						value='<s:text name="upload_label" />' /></li>
					<li><label><s:text name="category_picture_label" /></label><img
						id="categoryPic" style="width: 280px; height: 280px;"
						src='../resources/images${category.picPath}'></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnUpdate"
						type="button" class="btn" onclick="updateBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" style="width: 130px;"
						onclick="update();" value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
		</div>
	</div>
</s:else>

<div class="popwindow" id="popwindowid">
	<div id="title" class="poptitle">
		<img src="../resources/images/close1.png" />
		<s:text name="upload_label" />
	</div>
	<div class="popcontent">
		<div class="formbody">
			<div>
				<form name="uploadForm" id="uploadForm" enctype="multipart/form-data">
					<s:hidden id="categoryId" name="categoryId" />
					<s:hidden id="categoryName" name="categoryName" />
					<s:hidden id="newFileName" name="newFileName" />
					<ul class="forminfo">
						<li><label><s:text name="upload_choose_picture_label" /></label> <s:file
								name="file" /></li>
						<li><label>&nbsp;&nbsp;</label><input name="" type="button"
							class="btn" onclick="uploadFile();"
							value="<s:text name='upload_label' />" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
</div>