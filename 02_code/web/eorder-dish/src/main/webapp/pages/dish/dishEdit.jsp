<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/dish.js"></script>

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
					<li><label><s:text name="dish_name_label" /></label><input id="dish.name"
						name="dish.name" value="${dish.name}" type="text" class="dfinput" /></li>
					<li><label><s:text name="dish_price_label" /></label><input id="dish.price"
						name="dish.price" value="${dish.price}" type="text" class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="dish_picture_label" /></label><img id="dishPic"
						style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images${dish.picPath}'></li>
					<li><label><s:text name="category_name" /></label> <select
						id="dishSelect" name="dishSelect" class="select3">
							<s:iterator value="categoryVOList" id="categoryVO">
								<option value='<s:property value="#categoryVO.id" />'><s:property
										value="#categoryVO.name" /></option>
							</s:iterator>
					</select></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="save();"
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
					<li><label><s:text name="dish_name_label" /></label><input id="dish.name"
						name="dish.name" type="text" value='<s:property value="dish.name" />'
						class="dfinput" /></li>
					<li><label><s:text name="dish_price_label" /></label><input id="dish.price"
						name="dish.price" type="text" value='<s:property value="dish.price" />'
						class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="dish_picture_label" /></label><img id="dishPic"
						style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images${dish.picPath}'></li>
					<li><label><s:text name="category_name_label" /></label> <select
						id="dishSelect" name="dishSelect" class="select3">
							<s:iterator value="categoryVOList" id="categoryVO">
								<option value='<s:property value="#categoryVO.id" />'><s:property
										value="#categoryVO.name" /></option>
							</s:iterator>
					</select></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="updateBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="update();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
		</div>
	</div>
</s:else>