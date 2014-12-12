<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<title>eOrder</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
<script src="../resources/js/inputtransferselect.js" type="text/javascript"></script>
<script src="../resources/js/optiontransferselect.js" type="text/javascript"></script>
</head>

<s:head />
<script type="text/javascript">
	function save() {
		//alert('call save');
		//获取该页面中的第一个表单元素
		var targetForm = document.getElementById("saveForm");
		//动态修改目标表单的action属性
		targetForm.action = "doStore.action";
		//提交表单
		targetForm.submit();
	}

	function update() {
		//alert('call update');

		//alert('update ###');

		//获取该页面中的第一个表单元素
		var targetForm = document.getElementById("updateForm");
		//动态修改目标表单的action属性
		targetForm.action = "doUpdate.action";
		//提交表单
		targetForm.submit();
	}
</script>
<body>
	<div class="col-md-9">
		<!--导航标题栏-->
		<div class="row">
			<h3 class="page-header">功能管理</h3>
		</div>

		<div class="row">
			<div class="col-md-3">
				<s:if test="null == functionId">
					<h4>新增功能</h4>
					<s:form class="eorder-form-usr" id="saveForm" action="doStore">
						<input type="text" id="functionName" name="functionName"
							class="form-control eorder-input" placeholder="功能名称" />
						<input type="text" id="functionDesc" name="functionDesc"
							class="form-control eorder-input" placeholder="功能描述" />
						<input type="text" id="functionPath" name="functionPath"
							class="form-control eorder-input" placeholder="功能路径" />
						<input type="text" id="functionParent" name="functionParent"
							class="form-control eorder-input" placeholder="上级功能" />
						<input type="text" id="functionOrder" name="functionOrder"
							class="form-control eorder-input" placeholder="功能排序" />
						<a href="#" onclick="save();"
							class="btn btn-default btn-block eorder-btn-login">创建新功能</a>
					</s:form>
				</s:if>
				<s:else>
					<h4>修改功能</h4>
					<s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
						<s:hidden id="functionId" name="functionId" value="%{functionId}" />
						<input type="text" id="functionName" name="functionName"
							class="form-control eorder-input" placeholder="功能名称"
							value="${functionName}" />
						<input type="text" id="functionDesc" name="functionDesc"
							class="form-control eorder-input" placeholder="功能描述"
							value="${functionDesc}" />
						<input type="text" id="functionPath" name="functionPath"
							class="form-control eorder-input" placeholder="功能路径"
							value="${functionPath}" />
						<input type="text" id="functionParent" name="functionParent"
							class="form-control eorder-input" placeholder="上级功能"
							value="${functionParent}" />
						<input type="text" id="functionOrder" name="functionOrder"
							class="form-control eorder-input" placeholder="功能排序"
							value="${functionOrder}" />
						<a href="#" onclick="update();"
							class="btn btn-default btn-block eorder-btn-login">修改功能信息</a>
					</s:form>
				</s:else>
			</div>
		</div>

		<br>

		<div class="row">
			<h3 class="page-header">功能列表</h3>
		</div>

		<!--列表表格-->
		<div class="row">
			<div class="col-md-10">
				<table class="table table-condensed">
					<s:form id="functionForm" action="doRemove" theme="simple">
						<thead>
							<tr>
								<th></th>
								<th>功能名称</th>
								<th>功能描述</th>
								<th>功能路径</th>
								<th>上级功能ID</th>
								<th>上级功能名称</th>
								<th>功能排序</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="functionvos">
								<tr>
									<td><s:hidden id="functionId" name="functionId"
											value="%{functionId}" /></td>
									<td><s:property value="functionName" /></td>
									<td><s:property value="functionDesc" /></td>
									<td><s:property value="functionPath" /></td>
									<td><s:property value="functionParent" /></td>
									<td><s:property value="functionParentName" /></td>
									<td><s:property value="functionOrder" /></td>
									<td><a
										href='<s:url action="doEdit"><s:param name="functionId" value="functionId" /></s:url>'>
											<span class="glyphicon glyphicon-edit">
									</a></span></td>
									<td><a
										href='<s:url action="doRemove"><s:param name="functionId" value="functionId" /></s:url>'>
											<span class="glyphicon glyphicon-trash">
									</a></span></td>
								</tr>
							</s:iterator>
						</tbody>
					</s:form>
				</table>

				<!--表格分页-->
				<nav class="pull-right" style="margin-right: -17px">
					<ul class="pagination">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>