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

		var myFunctionsOptionsObj = document.getElementById("myFunctions");
		//alert('getOptions #1');
		var leftFunctionsOptionsObj = document.getElementById("leftFunctions");
		//alert('getOptions #2');
		var myFunctionsOptions = myFunctionsOptionsObj.options;
		//alert('getOptions #3');
		var leftFunctionsOptions = leftFunctionsOptionsObj.options;
		//alert('getOptions #4');

		var myList = new Array();

		//alert('myFunctionsOptions.length: ' + myFunctionsOptions.length);
		for (var i = 0; i < myFunctionsOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert('myFunctionsOptions[i].value: ' + myFunctionsOptions[i].value);
			if (0 != myFunctionsOptions[i].value) {
				myList.push(myFunctionsOptions[i].value);
			}
		}
		var leftList = new Array();
		//alert('leftFunctionsOptions.length: ' + leftFunctionsOptions.length);
		for (var i = 0; i < leftFunctionsOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(leftFunctionsOptions[i].value);
			if (0 != leftFunctionsOptions[i].value) {
				leftList.push(leftFunctionsOptions[i].value);
			}
		}
		//alert('update ###5');
		document.getElementById("myFunctionsArray").value = myList;
		document.getElementById("leftFunctionsArray").value = leftList;

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
			<h3 class="page-header">用户管理</h3>
		</div>

		<div class="row">
			<div class="col-md-3">
				<s:if test="null == roleId">
					<h4>新增功能</h4>
					<s:form class="eorder-form-usr" id="saveForm" action="doStore">
						<s:hidden id="functionId" name="functionId" />
						<s:hidden id="myFunctionsArray" name="myFunctionsArray" />
						<s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
						<input type="text" id="roleName" name="roleName"
							class="form-control eorder-input" placeholder="角色名称" />
						<input type="text" id="roleDesc" name="roleDesc"
							class="form-control eorder-input" placeholder="角色描述">
						<a href="#" onclick="save();"
							class="btn btn-default btn-block eorder-btn-login">创建新角色</a>
					</s:form>
				</s:if>
				<s:else>
					<h4>修改功能</h4>
					<s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
						<s:hidden id="roleId" name="roleId" value="%{roleId}" />
						<s:hidden id="functionId" name="functionId" />
						<s:hidden id="myFunctionsArray" name="myFunctionsArray" />
						<s:hidden id="leftFunctionsArray" name="leftFunctionsArray" />
						<input type="text" name="roleName" id="roleName" name="roleName"
							class="form-control eorder-input" placeholder="角色名称" value="${roleName}" />
						<input type="text" id="roleDesc" name="roleDesc"
							class="form-control eorder-input" placeholder="角色描述" value="${roleDesc}">
						<a href="#" onclick="update();"
							class="btn btn-default btn-block eorder-btn-login">修改角色信息</a>
					</s:form>
				</s:else>
			</div>

			<div class="col-md-3">
				<h4>已分配功能</h4>
			</div>
			<s:optiontransferselect cssStyle="form-control eorder-multi-sel"
				cssClass="form-control eorder-multi-sel" id="myFunctions"
				doubleId="leftFunctions" name="myFunctions" list="myFunctions"
				listKey="functionId" listValue="functionName" doubleName="leftFunctions"
				doubleList="leftFunctions" doubleListKey="functionId"
				doubleListValue="functionName" />
		</div>

		<br>

		<div class="row">
			<h3 class="page-header">角色列表</h3>
		</div>

		<!--列表表格-->
		<div class="row">
			<div class="col-md-10">
				<table class="table table-condensed">
					<s:form id="roleForm" action="doRemove" theme="simple">
						<thead>
							<tr>
								<th></th>
								<th>角色名称</th>
								<th>角色描述</th>
								<th>功能列表</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="rolevos">
								<tr>
									<td><s:hidden id="roleId" name="roleId" value="%{roleId}" /></td>
									<td><s:property value="roleName" /></td>
									<td><s:property value="roleDesc" /></td>
									<td><s:property value="functionName" /></td>
									<td><a
										href='<s:url action="doEdit"><s:param name="roleId" value="roleId" /></s:url>'>
											<span class="glyphicon glyphicon-edit">
									</a></span></td>
									<td><a
										href='<s:url action="doRemove"><s:param name="roleId" value="roleId" /></s:url>'>
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