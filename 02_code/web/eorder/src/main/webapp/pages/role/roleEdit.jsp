<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(function() {
		//移到右边
		$('#remove').click(
				function() {
					//获取选中的选项，删除并追加给对方
					$('#myFunctionsSelect option:selected').appendTo(
							'#leftFunctionsSelect');
				});
		//移到左边
		$('#add').click(
				function() {
					$('#leftFunctionsSelect option:selected').appendTo(
							'#myFunctionsSelect');
				});
		//全部移到右边
		$('#remove_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#myFunctionsSelect option').appendTo('#leftFunctionsSelect');
		});
		//全部移到左边
		$('#add_all').click(function() {
			$('#leftFunctionsSelect option').appendTo('#myFunctionsSelect');
		});
		//双击选项
		$('#myFunctionsSelect').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#leftFunctionsSelect'); //追加给对方
		});
		//双击选项
		$('#leftFunctionsSelect').dblclick(function() {
			$("option:selected", this).appendTo('#myFunctionsSelect');
		});
	});

	function save() {
		var myList = new Array();

		$("#myFunctionsSelect").each(function() {
			$(this).children("option").each(function() {
				myList.push($(this).val());
			});
		});

		var leftList = new Array();
		$("#leftFunctionsSelect").each(function() {
			$(this).children("option").each(function() {
				leftList.push($(this).val());
			});
		});

		$("#myFunctionsArray").val(myList);
		$("#leftFunctionsArray").val(leftList);

		$("[name='saveForm']").attr("action", "save.action");
		$("[name='saveForm']").attr("method", "post");
		$("[name='saveForm']").submit();
	}

	function update() {
		var myList = new Array();

		$("#myFunctionsSelect").each(function() {
			$(this).children("option").each(function() {
				myList.push($(this).val());
			});
		});

		var leftList = new Array();
		$("#leftFunctionsSelect").each(function() {
			$(this).children("option").each(function() {
				leftList.push($(this).val());
			});
		});

		$("#myFunctionsArray").val(myList);
		$("#leftFunctionsArray").val(leftList);
		$("[name='updateForm']").attr("action", "update.action");
		$("[name='updateForm']").attr("method", "post");
		$("[name='updateForm']").submit();
	}

	function updateBack() {
		$("[name='updateForm']").attr("action", "role.action");
		$("[name='updateForm']").attr("method", "post");
		$("[name='updateForm']").submit();
	}

	function saveBack() {
		$("[name='saveForm']").attr("action", "role.action");
		$("[name='saveForm']").attr("method", "post");
		$("[name='saveForm']").submit();
	}
</script>

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
						name="roleName" type="text" value='<s:property value="roleName" />'
						class="dfinput" /></li>
					<li><label><s:text name="role_desc" /></label><input id="roleDesc"
						name="roleDesc" type="text" value='<s:property value="roleDesc" />'
						class="dfinput" /></li>
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
					<li><label><s:text name="role_name" /></label><input
						id="roleName" name="roleName" type="text"
						value='<s:property value="roleName" />' class="dfinput" /></li>
					<li><label><s:text name="role_desc" /></label><input
						id="roleDesc" name="roleDesc" type="text"
						value='<s:property value="roleDesc" />' class="dfinput" /></li>
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