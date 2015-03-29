<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<script type="text/javascript" src="../resources/js/dish.js"></script>

<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="dish.action"><s:text name="dish_manage" /></a></li>
			<li><s:text name="add_dish" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form id="saveForm" name="saveForm" action="save">
				<s:hidden id="categoryId" name="categoryId" />
				<s:hidden id="dishPicture" name="dishPicture" />
				<ul class="forminfo">
					<li><label><s:text name="dish_name" /></label><input id="dishName"
						name="dishName" type="text" class="dfinput" /></li>
					<li><label><s:text name="dish_price" /></label><input id="dishPrice"
						name="dishPrice" type="text" class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="dish_picture" /></label><img id="dishPic"
						style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images<s:property value="dishPicture" />'></li>
					<li><label><s:text name="category_name" /></label> <select
						id="dishSelect" name="dishSelect" class="select3">
							<s:iterator value="categoryVOList" id="categoryVO">
								<option value='<s:property value="#categoryVO.id" />'><s:property
										value="#categoryVO.categoryName" /></option>
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
	</div>
</s:if>
<s:else>
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="dish.action"><s:text name="dish_manage" /></a></li>
			<li><s:text name="edit_dish" /></li>
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
				<s:hidden id="categoryId" name="categoryId" value="%{categoryId}" />
				<ul class="forminfo">
					<li><label><s:text name="dish_name" /></label><input id="dishName"
						name="dishName" type="text" value='<s:property value="dishName" />'
						class="dfinput" /></li>
					<li><label><s:text name="dish_price" /></label><input id="dishPrice"
						name="dishPrice" type="text" value='<s:property value="dishPrice" />'
						class="dfinput" /></li>
					<li><label><s:text name="upload" /></label><input name=""
						type="button" class="scbtn" onclick="openUploadPage();"
						value='<s:text name="upload" />' /></li>
					<li><label><s:text name="dish_picture" /></label><img id="dishPic"
						style="width: 348px; height: 348px; padding: 5px;"
						src='../resources/images<s:property value="dishPicture" />'></li>
					<li><label><s:text name="category_name" /></label> <select
						id="dishSelect" name="dishSelect" class="select3">
							<s:iterator value="categoryVOList" id="categoryVO">
								<option value='<s:property value="#categoryVO.id" />'><s:property
										value="#categoryVO.categoryName" /></option>
							</s:iterator>
					</select></li>
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