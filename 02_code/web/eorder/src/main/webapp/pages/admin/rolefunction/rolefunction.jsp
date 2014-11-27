<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="../resources/js/moo.fx.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>
<script src="../resources/js/utils.js" type="text/javascript"></script>
<script src="../resources/js/inputtransferselect.js" type="text/javascript"></script>
<script src="../resources/js/optiontransferselect.js" type="text/javascript"></script>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

table thead tr th {
	border: 1px solid black;
	padding: 3px;
	background-color: #cccccc;
}

table tbody tr td {
	border: 1px solid black;
	padding: 3px;
}
</style>
</head>
<script type="text/javascript">
	function loadFunctions() {
		//alert('call loadFunctions');
		var t = document.getElementById("role");
		//alert("t: " + t);
		//alert("t: " + t.options[t.selectedIndex].value);
		var a = t.options[t.selectedIndex].value
		//alert('a: ' + a);
		document.getElementById("roleName").value = a;
		//alert('b: ' + document.getElementById("roleName").value);
		//val selectedRoleName = document.getElementById("role").options[t.selectedIndex].value;
		//alert('selectedRoleName: ' + selectedRoleName);
		//var roleName = document.getElementById("roleName");
		//alert('roleName value: ' + roleName.value);
		//roleName.value = selectedRoleName; 
		//alert('OK');
		//var roleObject = document.getElementById("role");
		//alert('roleObject: ' + roleObject);
		//var optionsObject = document.getElementById("role").options[selectIndex];
		//alert('optionsObject: ' + optionsObject);
		//var textValue = document.getElementById("role").options[selectIndex].text;
		//alert('textValue: ' + textValue);
		
		//alert(pageNow.value);
		//获取该页面中的第一个表单元素
		var targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "doLoad.action";
		//提交表单
		targetForm.submit();
	}
	
	function updateFunctions() {
		//alert('call loadFunctions');
		//var t = document.getElementById("role");
		//alert("t: " + t);
		//alert("t: " + t.options[t.selectedIndex].value);
		//var a = t.options[t.selectedIndex].value
		//alert('a: ' + a);
		//document.getElementById("roleName").value = a;
		//alert('b: ' + document.getElementById("roleName").value);
		//val selectedRoleName = document.getElementById("role").options[t.selectedIndex].value;
		//alert('selectedRoleName: ' + selectedRoleName);
		//var roleName = document.getElementById("roleName");
		//alert('roleName value: ' + roleName.value);
		//roleName.value = selectedRoleName; 
		//alert('OK');
		//var roleObject = document.getElementById("role");
		//alert('roleObject: ' + roleObject);
		//var optionsObject = document.getElementById("role").options[selectIndex];
		//alert('optionsObject: ' + optionsObject);
		//var textValue = document.getElementById("role").options[selectIndex].text;
		//alert('textValue: ' + textValue);
		
		//alert(pageNow.value);
		
		var myFunctionsOptions = document.getElementById("myFunctions").options;
		var leftFunctionsOptions = document.getElementById("leftFunctions").options;
		
		//var cnbook = document.getElementByName("cnbook");
		//alert("cnbook: " + cnbook);
		//alert("cnbook: " + cnbook.length);

		//var cnbookFlag = "";
		var myList = new Array();
		for (var i = 0; i < myFunctionsOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(myFunctionsOptions[i].value);
			myList.push(myFunctionsOptions[i].value);
		}
		var leftList = new Array();
		for (var i = 0; i < leftFunctionsOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(leftFunctionsOptions[i].value);
			leftList.push(leftFunctionsOptions[i].value);
		}
		document.getElementById("myFunctionsArray").value = myList;
		document.getElementById("leftFunctionsArray").value = leftList;	
		
		//获取该页面中的第一个表单元素
		var targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "doUpdate.action";
		//提交表单
		targetForm.submit();
	}
	
</script>
<body>
	<div width="1020" style="height: 604px; background-color: #FFFFFF">
		<s:form id="roleForm" action="#" method="post" theme="simple">
			<s:hidden id="roleName" name="roleName"/>
			<s:hidden id="myFunctionsArray" name="myFunctionsArray"/>
			<s:hidden id="leftFunctionsArray" name="leftFunctionsArray"/>
			<table cellspacing="0">
				<tr>
					<td><s:select 
							label="角色" 
							list="roles" 
							id="role" 
							name="role" 
							listKey="roleName"
							listValue="roleDesc" 
							value="roleName"
							onchange="javascript:loadFunctions();" 
							multiple="ture" 
							size="10"/>
					</td>
					<td><s:optiontransferselect 
							label="拥有的功能" 
							name="myFunctions" 
							list="myFunctions" 
							listKey="functionName" 
							listValue="functionDisplay" 
							doubleName="leftFunctions" 
							doubleList="leftFunctions" 
							doubleListKey="functionName"  
							doubleListValue="functionDisplay"
							/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="更新角色功能关系" onclick="updateFunctions();"/></td></tr>
			</table>
		</s:form>
	</div>

</body>
</html>