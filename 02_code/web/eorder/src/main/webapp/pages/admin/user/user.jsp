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

		var myRolesOptionsObj = document.getElementById("myRoles");
		//alert('getOptions #1');
		var leftRolesOptionsObj = document.getElementById("leftRoles");
		//alert('getOptions #2');
		var myRolesOptions = myRolesOptionsObj.options;
		//alert('getOptions #3');
		var leftRolesOptions = leftRolesOptionsObj.options;
		//alert('getOptions #4');

		var myList = new Array();

		//alert('myRolesOptions.length: ' + myRolesOptions.length);
		for (var i = 0; i < myRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert('myRolesOptions[i].value: ' + myRolesOptions[i].value);
			if (0 != myRolesOptions[i].value) {
				myList.push(myRolesOptions[i].value);
			}
		}
		var leftList = new Array();
		//alert('leftRolesOptions.length: ' + leftRolesOptions.length);
		for (var i = 0; i < leftRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(leftRolesOptions[i].value);
			if (0 != leftRolesOptions[i].value) {
				leftList.push(leftRolesOptions[i].value);
			}
		}
		//alert('update ###5');
		document.getElementById("myRolesArray").value = myList;
		document.getElementById("leftRolesArray").value = leftList;

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
				<s:if test="null == userId">
					<h4>新增功能</h4>
					<s:form class="eorder-form-usr" id="saveForm" action="doStore">
						<s:hidden id="roleId" name="roleId" />
						<s:hidden id="myRolesArray" name="myRolesArray" />
						<s:hidden id="leftRolesArray" name="leftRolesArray" />
						<input type="text name=" id="username" name="username"
							class="form-control eorder-input" placeholder="用户名" />
						<input type="password" id="password" name="password"
							class="form-control eorder-input" placeholder="密  码">
						<input type="text" id="cellphone" name="cellphone"
							class="form-control eorder-input" placeholder="手机号码">
						<a href="#" onclick="save();"
							class="btn btn-default btn-block eorder-btn-login">创建新用户</a>
					</s:form>
				</s:if>
				<s:else>
					<h4>修改功能</h4>
					<s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
						<s:hidden id="userId" name="userId" value="%{userId}" />
						<s:hidden id="roleId" name="roleId" />
						<s:hidden id="myRolesArray" name="myRolesArray" />
						<s:hidden id="leftRolesArray" name="leftRolesArray" />
						<input type="text" id="username" name="username"
							class="form-control eorder-input" placeholder="用户名" value="${username}">
						<input type="password" id="password" name="password"
							class="form-control eorder-input" placeholder="密  码" value="${password}">
						<input type="text" id="cellphone" name="cellphone"
							class="form-control eorder-input" placeholder="手机号码" value="${cellphone}">
						<a href="#" onclick="update();"
							class="btn btn-default btn-block eorder-btn-login">修改用户信息</a>
					</s:form>
				</s:else>
			</div>

			<div class="col-md-3">
				<h4>已分配角色</h4>
				<div style="width:300px;">
			<s:optiontransferselect 
				doubleCssClass="form-control eorder-multi-sel"
				cssClass="form-control eorder-multi-sel" 
				buttonCssClass="btn btn-default eorder-btn-arrow"
				id="myRoles" name="myRoles"
				list="myRoles" listKey="roleId" listValue="roleName" doubleId="leftRoles"
				doubleName="leftRoles" doubleList="leftRoles" doubleListKey="roleId"
				doubleListValue="roleName" />
				</div>
			</div>
			
		</div>

		<br>

		<div class="row">
			<h3 class="page-header">用户列表</h3>
		</div>

		<!--列表表格-->
		<div class="row">
			<div class="col-md-10">
				<table class="table table-condensed">
					<s:form id="userForm" action="doRemove" theme="simple">
						<thead>
							<tr>
								<th></th>
								<th>用户名</th>
								<th>角色</th>
								<th>手机号码</th>
								<th>用户等级</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="uservos">
								<tr>
									<td><s:hidden id="userId" name="userId" value="%{userId}" /></td>
									<td><s:property value="username" /></td>
									<td><s:property value="roleName" /></td>
									<td><s:property value="cellphone" /></td>
									<td><s:property value="levelName" /></td>
									<td><a
										href='<s:url action="doEdit"><s:param name="userId" value="userId" /></s:url>'>
											<span class="glyphicon glyphicon-edit">
									</a></span></td>
									<td><a
										href='<s:url action="doRemove"><s:param name="userId" value="userId" /></s:url>'>
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