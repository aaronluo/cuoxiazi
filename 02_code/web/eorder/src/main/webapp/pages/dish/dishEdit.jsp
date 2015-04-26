<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/dish.js"></script>
<script type="text/javascript">
	// 上传
	function uploadFile() {
		// 取得用户输入的分类
		var oldDishId = $("#dish\\.id").val();
		var oldCategoryId = $("#categoryId").val();
		var oldDishName = $("#dish\\.name").val();
		var oldDishPrice = $("#dish\\.price").val();

		//alert("oldDishId: " + oldDishId);
		//alert("oldCategoryId: " + oldCategoryId);
		//alert("oldDishName: " + oldDishName);
		//alert("oldDishPrice: " + oldDishPrice);
		$("#dishId").val(oldDishId);
		$("#oldCategoryId").val(oldCategoryId);
		$("#dishName").val(oldDishName);
		$("#dishPrice").val(oldDishPrice);

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

<s:if test="dish.id == null || dish.id == 0L">
	<!-- 创建新的菜品 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="dish.action"><s:text name="dish_manage" /></a></li>
			<li><s:text name="dish_add" /></li>
		</ul>
	</div>

	<div class="rightinfo" style="margin-top: 50px">
		<div class="tablePanel">
			<form id="saveForm" name="saveForm" action="save">
				<s:hidden id="categoryId" name="categoryId" />
				<s:hidden id="dish.picPath" name="dish.picPath" />
				<ul class="forminfo">
					<li><label><s:text name="dish_name_label" /></label><input
						id="dish.name" name="dish.name" value="${dish.name}" type="text"
						class="dfinput" style="width: 280px;" /></li>
					<li><label><s:text name="dish_price_label" /></label><input
						id="dish.price" name="dish.price" value="${dish.price}" type="text"
						class="dfinput" style="width: 280px;" /></li>
					<li><label><s:text name="upload_label" /></label><input name=""
						id="btn_center" type="button" class="scbtn"
						value='<s:text name="upload_label" />' /></li>
					<li><label><s:text name="dish_picture_label" /></label><img
						id="dishPic" style="width: 140px; height: 140px;"
						src='../resources/images${dish.picPath}'></li>
					<li><label><s:text name="category_name_label" /></label> <s:select
							list="categoryVOList" listKey="id" listValue="name" id="dishSelect"
							name="dishSelect"
							cssStyle="border:1px dotted blue;height:30px;width:280px"></s:select></li>
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
	<!-- 更新菜品 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="dish.action"><s:text name="dish_manage" /></a></li>
			<li><s:text name="dish_edit" /></li>
		</ul>
	</div>

	<div class="rightinfo" style="margin-top: 50px">
		<div class="tablePanel">
			<form id="updateForm" name="updateForm" action="update">
				<s:hidden id="dish.id" name="dish.id" value="%{dish.id}"></s:hidden>
				<s:hidden id="categoryId" name="categoryId" />
				<s:hidden id="dish.picPath" name="dish.picPath" />
				<ul class="forminfo">
					<li><label><s:text name="dish_name_label" /></label><input
						id="dish.name" name="dish.name" type="text" style="width: 280px;"
						value='<s:property value="dish.name" />' class="dfinput" /></li>
					<li><label><s:text name="dish_price_label" /></label><input
						id="dish.price" name="dish.price" type="text" style="width: 280px;"
						value='<s:property value="dish.price" />' class="dfinput" /></li>
					<li><label><s:text name="upload_label" /></label><input name=""
						id="btn_center" type="button" class="scbtn"
						value='<s:text name="upload_label" />' /></li>
					<li><label><s:text name="dish_picture_label" /></label><img
						id="dishPic" style="width: 140px; height: 140px;"
						src='../resources/images${dish.picPath}'></li>
					<li><label><s:text name="category_name_label" /></label> <s:select
							list="categoryVOList" listKey="id" listValue="name" id="dishSelect"
							name="dishSelect"
							cssStyle="border:1px dotted blue;height:30px;width:280px"></s:select></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
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
					<s:hidden id="dishId" name="dishId" />
					<s:hidden id="oldCategoryId" name="oldCategoryId" />
					<s:hidden id="dishName" name="dishName" />
					<s:hidden id="dishPrice" name="dishPrice" />
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