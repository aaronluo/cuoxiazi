<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function save() {
		$("[name='saveForm']").attr("action", "save.action");
		$("[name='saveForm']").attr("method", "post");
		$("[name='saveForm']").submit();
	}

	function update() {
		$("[name='updateForm']").attr("action", "update.action");
		$("[name='updateForm']").attr("method", "post");
		$("[name='updateForm']").submit();
	}

	function updateBack() {
		$("[name='updateForm']").attr("action", "function.action");
		$("[name='updateForm']").attr("method", "post");
		$("[name='updateForm']").submit();
	}

	function saveBack() {
		$("[name='saveForm']").attr("action", "function.action");
		$("[name='saveForm']").attr("method", "post");
		$("[name='saveForm']").submit();
	}

	// 回车提交表单（登录）
	$(function() {
		var functionParent = $("#functionParent").val();
		$("#functionSelect").each(function() {
			$(this).children("option").each(function() {
				if (functionParent == $(this).val()) {
					$(this).attr("selected", true);
					return;
				}
			});
		});

		//移到左边
		$('#functionSelect').change(function() {
			var functionParent = $(this).children('option:selected').val();
			$("#functionParent").val(functionParent);
		});
	});
</script>


<s:if test="null == id">
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="function.action"><s:text name="function_manage" /></a></li>
			<li><s:text name="add_function" /></li>
		</ul>
	</div>

	<div class="formbody">
		<div class="operatemessage">
			<s:property value="message" />
			&nbsp;&nbsp;&nbsp;&nbsp;
		</div>

		<div>
			<form id="saveForm" name="saveForm" action="save">
				<ul class="forminfo">
					<s:hidden id="functionParent" name="functionParent" />
					<li><label><s:text name="function_name" /></label><input
						id="functionName" name="functionName" type="text" class="dfinput" /></li>
					<li><label><s:text name="function_desc" /></label><input
						id="functionDesc" name="functionDesc" type="text" class="dfinput" /></li>
					<li><label><s:text name="function_path" /></label><input
						id="functionPath" name="functionPath" type="text" class="dfinput" /></li>
					<li><label><s:text name="function_parent_name" /></label> <select
						id="functionSelect" name="functionSelect" class="select3">
							<s:iterator value="functionParentList" id="function">
								<option value='<s:property value="#function.stringId" />'><s:property
										value="#function.functionName" /></option>
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
			<li><a href="function.action"><s:text name="function_manage" /></a></li>
			<li><s:text name="edit_function" /></li>
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
				<s:hidden id="functionParent" name="functionParent"
					value="%{functionParent}" />
				<ul class="forminfo">
					<li><label><s:text name="function_name" /></label><input
						id="functionName" name="functionName" type="text"
						value='<s:property value="functionName" />' class="dfinput" /></li>
					<li><label><s:text name="function_desc" /></label><input
						id="functionDesc" name="functionDesc" type="text"
						value='<s:property value="functionDesc" />' class="dfinput" /></li>
					<li><label><s:text name="function_path" /></label><input
						id="functionPath" name="functionPath" type="text"
						value='<s:property value="functionPath" />' class="dfinput" /></li>
					<li><label><s:text name="function_parent_name" /></label> <select
						id="functionSelect" name="functionSelect" class="select3">
							<s:iterator value="functionParentList" id="function">
								<option value='<s:property value="#function.stringId" />'><s:property
										value="#function.functionName" /></option>
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