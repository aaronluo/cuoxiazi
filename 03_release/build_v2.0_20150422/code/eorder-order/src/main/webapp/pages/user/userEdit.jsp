<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/user.js"></script>

<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="user.action"><s:text name="user_manage" /></a></li>
			<li><s:text name="add_user" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form id="saveForm" name="saveForm" action="save">
				<s:hidden id="myRolesArray" name="myRolesArray" />
				<s:hidden id="leftRolesArray" name="leftRolesArray" />
				<ul class="forminfo">
					<li><label><s:text name="username" /></label><input id="username"
						name="username" value="${username}" type="text" class="dfinput" /></li>
					<li><label><s:text name="password" /></label><input id="passwordOne"
						name="passwordOne" value="${passwordOne}" type="password" class="dfinput" /></li>
					<li><label><s:text name="confirm_password" /></label><input
						id="passwordTwo" name="passwordTwo" value="${passwordTwo}" type="password"
						class="dfinput" /></li>
					<li><label><s:text name="cellphone" /></label><input name="cellphone"
						id="cellphone" value="${cellphone}" type="text" class="dfinput" /></li>
					<li><label><s:text name="roles_allocation" /></label>
						<div class="selectcontent">
							<div class="rightselect">
								<select id="leftRolesSelect" name="leftRolesSelect" multiple="multiple"
									class="select2">
									<s:iterator value="leftRoles" id="role">
										<option style="margin: 5px;" value="${id}">${roleName}</option>
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
								<select id="myRolesSelect" name="myRolesSelect" multiple="multiple"
									class="select2">
									<s:iterator value="myRoles" id="role">
										<option style="margin: 5px;" value="${id}">${roleName}</option>
									</s:iterator>
								</select>
							</div>
						</div></li>
					<li><label>&nbsp;</label></li>
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
			<li><a href="user.action"><s:text name="user_manage" /></a></li>
			<li><s:text name="edit_user" /></li>
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
				<s:hidden id="myRolesArray" name="myRolesArray" />
				<s:hidden id="leftRolesArray" name="leftRolesArray" />
				<ul class="forminfo">
					<li><label><s:text name="username" /></label><input id="username"
						name="username" type="text" value='<s:property value="username" />'
						class="dfinput" /></li>
					<li><label><s:text name="password" /></label><input id="passwordOne"
						name="passwordOne" type="password"
						value='<s:property value="passwordOne" />' class="dfinput" /></li>
					<li><label><s:text name="confirm_password" /></label><input
						id="passwordTwo" name="passwordTwo" type="password"
						value='<s:property value="passwordTwo" />' class="dfinput" /></li>
					<li><label><s:text name="cellphone" /></label><input name="cellphone"
						type="text" value='<s:property value="cellphone" />' class="dfinput" /></li>
					<li><label><s:text name="roles_allocation" /></label>
						<div class="selectcontent">
							<div class="rightselect">
								<select id="leftRolesSelect" name="leftRolesSelect" multiple="multiple"
									class="select2">
									<s:iterator value="leftRoles" id="role">
										<option style="margin: 5px;" value="${id}">${roleName}</option>
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
								<select id="myRolesSelect" name="myRolesSelect" multiple="multiple"
									class="select2">
									<s:iterator value="myRoles" id="role">
										<option style="margin: 5px;" value="${id}">${roleName}</option>
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