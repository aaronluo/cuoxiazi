<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<title>eOrder</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../resources/js/jquery.min.js" /></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/optiontransferselect.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>

<script type="text/javascript">
	$(function() {
		//移到右边
		$('#add').click(function() {
			//获取选中的选项，删除并追加给对方
			$('#myRoles option:selected').appendTo('#leftRoles');
		});
		//移到左边
		$('#remove').click(function() {
			$('#leftRoles option:selected').appendTo('#myRoles');
		});
		//全部移到右边
		$('#add_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#myRoles option').appendTo('#leftRoles');
		});
		//全部移到左边
		$('#remove_all').click(function() {
			$('#leftRoles option').appendTo('#myRoles');
		});
		//双击选项
		$('#myRoles').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#leftRoles'); //追加给对方
		});
		//双击选项
		$('#leftRoles').dblclick(function() {
			$("option:selected", this).appendTo('#myRoles');
		});
	});

	function save() {
		$("[name='saveForm']").attr("action", "save.action");
		$("[name='saveForm']").attr("method", "post");
		$("[name='saveForm']").submit();
	}

	function update() {

		var myList = new Array();

		$("#myRoles").each(function() {
			$(this).children("option").each(function() {
				myList.push($(this).val());
			});
		});

		var leftList = new Array();
		$("#leftRoles").each(function() {
			$(this).children("option").each(function() {
				leftList.push($(this).val());
			});
		});
		$("#myRolesArray").val(myList);
		$("#leftRolesArray").val(leftList);

		$("[name='updateForm']").attr("action", "update.action");
		$("[name='updateForm']").attr("method", "post");
		$("[name='updateForm']").submit();
	}
</script>
<body>
	<!--head navigation bar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<!-- 商家名称-->
			<a class="navbar-brand">eOrder订餐系统</a>
			<!--自适应mobile设备菜单开关, Web后台可以不管-->
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- 标题栏 -->
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<!--标题栏里面需要展现的item-->
					<li><p class=navbar-text>
							当前用户：
							<s:property value="loginName" />
							&nbsp;&nbsp; <a href="<c:url value='/j_spring_security_logout' />"
								target="_parent"> 退出系统</a>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
							&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
						</p></li>
				</ul>
			</div>
		</div>
	</div>

	<!--页面主体部分-->
	<div class="container-fluid">
		<div class="row">
			<!--左侧菜单 begin-->
			<div class="col-md-2">
				<div class="panel-group" id="eorder_menu" role="tablist"
					aria-multiselectable="true">
					<s:iterator value="menulist" id="banner">
						<!-- 用户权限管理模块 -->
						<div class="panel panel-default">
							<!--菜单头-->
							<div class="panel-heading" role="tab" id="usr_mgt">
								<h4 class="panel-title">
									<s:property value="#banner.functionName" />
								</h4>
							</div>
							<div class="panel-collapse collapse in" id="usr_mgt_list" role="tabpanel"
								aria-labelledby="usr_mgt">
								<!--菜单内容-->
								<div class="panel-body">
									<div class="list-group">
										<s:iterator value="#banner.list" id="subBanner">
											<a class="list-group-item"
												href='<%=basePath%><s:property value="#subBanner.link" />'
												target="_self"><s:property value="#subBanner.functionName" /></a>
										</s:iterator>

									</div>
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
			<!--左侧菜单 end-->

			<div class="col-md-9">
				<!--导航标题栏-->
				<div class="row">
					<h3 class="page-header">用户管理</h3>
				</div>

				<!--列表表格-->
				<div class="row">
					<div class="col-md-3">
						<s:if test="null == userId || 0 == userId">
							<h4>新增用户</h4>
							<s:form class="eorder-form-usr" id="saveForm">
								<s:hidden id="roleId" name="roleId" />
								<s:hidden id="myRolesArray" name="myRolesArray" />
								<s:hidden id="leftRolesArray" name="leftRolesArray" />
								<input type="text" id="username" name="username"
									class="form-control eorder-input" placeholder="用户名" value="${username}" />
								<input type="password" id="password" name="password"
									class="form-control eorder-input" placeholder="密  码"
									value="${password}">
								<input type="text" id="cellphone" name="cellphone"
									class="form-control eorder-input" placeholder="手机号码"
									value="${cellphone}">
								<a href="#" onclick="save();"
									class="btn btn-default btn-block eorder-btn-login">创建新用户</a>
							</s:form>
						</s:if>
						<s:else>
							<h4>修改用户</h4>
							<s:form class="eorder-form-usr" id="updateForm">
								<s:hidden id="userId" name="userId" value="%{userId}" />
								<s:hidden id="roleId" name="roleId" />
								<s:hidden id="myRolesArray" name="myRolesArray" />
								<s:hidden id="leftRolesArray" name="leftRolesArray" />
								<input type="text" id="username" name="username"
									class="form-control eorder-input" placeholder="用户名" value="${username}">
								<input type="password" id="password" name="password"
									class="form-control eorder-input" placeholder="密  码"
									value="${password}">
								<input type="text" id="cellphone" name="cellphone"
									class="form-control eorder-input" placeholder="手机号码"
									value="${cellphone}">
								<a href="#" onclick="update();"
									class="btn btn-default btn-block eorder-btn-login">修改用户信息</a>
							</s:form>
						</s:else>

						<s:fielderror />
						<s:if test=" null != message && '' != message">
							<h4>
								<s:property value="message" />
							</h4>
						</s:if>
					</div>

					<div class="col-md-3">
						<h4>已分配角色</h4>
						<select id="myRoles" name="myRoles" multiple
							class="form-control eorder-multi-sel">
							<s:iterator value="myRoles" id="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</s:iterator>
						</select>
					</div>
					<div class="col-md-2 text-center">
						<button id="remove_all" class="btn btn-default eorder-btn-arrow"
							style="margin-top: 42px">
							<span class="glyphicon glyphicon-backward"></span>
						</button>
						<br />
						<button id="remove" class="btn btn-default eorder-btn-arrow">
							<span class="glyphicon glyphicon-chevron-left"></span>
						</button>
						<br />
						<button id="add" class="btn btn-default eorder-btn-arrow">
							<span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<br />
						<button id="add_all" class="btn btn-default eorder-btn-arrow">
							<span class="glyphicon glyphicon-forward"></span>
						</button>
					</div>
					<div class="col-md-3">
						<h4>可分配角色</h4>
						<select id="leftRoles" name="leftRoles" multiple
							class="form-control eorder-multi-sel">
							<s:iterator value="leftRoles" id="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</s:iterator>
						</select>
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
					</div>
				</div>

			</div>
		</div>
	</div>

	<!--页面底部footer-->
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text pull-left">Powered by Bootstrap</p>
		</div>
	</div>

</body>
</html>