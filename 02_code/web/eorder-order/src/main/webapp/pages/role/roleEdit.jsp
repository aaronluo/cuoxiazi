<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/role.js"></script>

<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="role.action"><s:text name="role_manage" /></a></li>
			<li><s:text name="add_role" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form name="saveForm">
				<s:hidden id="myFunctionsArray" name="myFunctionsArray" />
				<s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
				<ul class="forminfo">
					<li><label><s:text name="role_name" /></label><input id="roleName"
						name="roleName" value="${roleName}" type="text"
						value='<s:property value="roleName" />' class="dfinput" /></li>
					<li><label><s:text name="role_desc" /></label><input id="roleDesc"
						name="roleDesc" value="${roleDesc}" type="text"
						value='<s:property value="roleDesc" />' class="dfinput" /></li>
					<li><label><s:text name="functions_allocation" /></label>
						<div class="selectcontent">
							<div class="rightselect">
								<select id="leftFunctionsSelect" name="leftFunctionsSelect"
									multiple="multiple" class="select2">
									<s:iterator value="leftFunctions" id="function">
										<option style="margin: 5px;" value="${id}">${functionName}</option>
									</s:iterator>
								</select>
							</div>
							<div class="centerbutton">
								<ul class="toolbar">
									<li id="add_all" style="width: 100px;"><span><img
											src="../resources/images/t01.png"></span> <s:text name="add_all" /></li>
									<li id="add" style="width: 100px;"><span><img
											src="../resources/images/t01.png"></span> <s:text name="add_select" /></li>
									<li id="remove" style="width: 100px;"><span><img
											src="../resources/images/t03.png"></span> <s:text name="delete_select" /></li>
									<li id="remove_all" style="width: 100px;"><span><img
											src="../resources/images/t03.png"></span> <s:text name="delete_all" /></li>
								</ul>
							</div>
							<div class="leftselect">
								<select id="myFunctionsSelect" name="myFunctionsSelect"
									multiple="multiple" class="select2">
									<s:iterator value="myFunctions" id="function">
										<option style="margin: 5px;" value="${id}">${functionName}</option>
									</s:iterator>
								</select>
							</div>
						</div></li>
					<li><label>&nbsp;</label></li>
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview'/>" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="save();"
						value="<s:text name='confirm_save'/>" /></li>
				</ul>
			</form>
		</div>
	</div>
</s:if>
<s:else>
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="role.action"><s:text name="role_manage" /></a></li>
			<li><s:text name="edit_role" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form name="updateForm">
				<s:hidden id="id" name="id" value="%{id}"></s:hidden>
				<s:hidden id="myFunctionsArray" name="myFunctionsArray" />
				<s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
				<ul class="forminfo">
					<li><label><s:text name="role_name" /></label><input id="roleName"
						name="roleName" type="text" value='<s:property value="roleName" />'
						class="dfinput" /></li>
					<li><label><s:text name="role_desc" /></label><input id="roleDesc"
						name="roleDesc" type="text" value='<s:property value="roleDesc" />'
						class="dfinput" /></li>
					<li><label><s:text name="functions_allocation" /></label>
						<div class="selectcontent">
							<div class="rightselect">
								<select id="leftFunctionsSelect" name="leftFunctionsSelect"
									multiple="multiple" class="select2"
									style="height: 280px; border: 1px solid #ccc; margin: 0px;">
									<s:iterator value="leftFunctions" id="function">
										<option style="margin: 5px;" value="${id}">${functionName}</option>
									</s:iterator>
								</select>
							</div>
							<div class="centerbutton">
								<ul class="toolbar">
									<li id="add_all" style="width: 100px;"><span><img
											src="../resources/images/t01.png"></span> <s:text name="add_all" /></li>
									<li id="add" style="width: 100px;"><span><img
											src="../resources/images/t01.png"></span> <s:text name="add_select" /></li>
									<li id="remove" style="width: 100px;"><span><img
											src="../resources/images/t03.png"></span> <s:text name="delete_select" /></li>
									<li id="remove_all" style="width: 100px;"><span><img
											src="../resources/images/t03.png"></span> <s:text name="delete_all" /></li>
								</ul>
							</div>
							<div class="leftselect">
								<select id="myFunctionsSelect" name="myFunctionsSelect"
									multiple="multiple" class="select2"
									style="height: 280px; border: 1px solid #ccc; margin: 0px;">
									<s:iterator value="myFunctions" id="function">
										<option style="margin: 5px;" value="${id}">${functionName}</option>
									</s:iterator>
								</select>
							</div>
						</div></li>
					<li><label>&nbsp;</label></li>
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