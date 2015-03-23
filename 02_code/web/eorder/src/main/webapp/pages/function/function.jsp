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

<script type="text/javascript">
	function add() {
		$("[name='addForm']").attr("action", "add.action");
		$("[name='addForm']").attr("method", "post");
		$("[name='addForm']").submit();
	}

	function prePage() {
		var pageNow = $("#pageNow").val();
		pageNow = parseInt(pageNow) - 1;
		$("#pageNow").val(pageNow);

		$("[name='pageForm']").attr("action", "function.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}

	function nextPage() {
		var pageNow = $("#pageNow").val();
		pageNow = parseInt(pageNow) + 1;
		$("#pageNow").val(pageNow);

		$("[name='pageForm']").attr("action", "function.action");
		$("[name='pageForm']").attr("method", "post");
		$("[name='pageForm']").submit();
	}

	function load() {
		// 取得用户输入的页数
		var pageNow = $("#pageInput").val();
		// 如果没有输入，则不响应确认按钮事件
		if ('' != pageNow) {
			$("[name='pageForm']").attr("action", "function.action");
			$("[name='pageForm']").attr("method", "post");
			$("[name='pageForm']").submit();
		}
	}
</script>

<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="function_manage" /></a></li>
	</ul>
</div>

<div class="operatemessage">
	<s:property value="message" />
</div>

<!--页面主体部分-->
<div class="rightinfo">
	<div class="tools">
		<form name="addForm" id="addForm">
			<ul class="toolbar" onclick="add();">
				<li><span><img src="../resources/images/t01.png"></span><s:text name="add" /></li>
			</ul>
		</form>
	</div>

	<s:form id="functionForm" action="remove" theme="simple">
		<!--列表表格-->
		<table class="tablelist">
			<thead>
				<tr>
					<th><s:text name="function_name" /></th>
					<th><s:text name="function_desc" /></th>
					<th><s:text name="function_path" /></th>
					<th><s:text name="function_parent_name" /></th>
					<th width="8%"><s:text name="edit" /></th>
					<th width="8%"><s:text name="delete" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="functionvos">
					<tr>
						<td><s:property value="functionName" /></td>
						<td><s:property value="functionDesc" /></td>
						<td><s:property value="functionPath" /></td>
						<td><s:property value="functionParentName" /></td>
						<td><a
							href='<s:url action="edit"><s:param name="id" value="id" /></s:url>'
							class="tablelink"><s:text name="edit" /></a></td>
						<td><a
							href='<s:url action="remove"> <s:param name="id" value="id" /></s:url>'
							class="tablelink"><s:text name="delete" /></a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:form>

	<!-- 分页信息 -->
	<div class="pagin">
		<div class="message">
			<s:text name="total" />
			&nbsp;&nbsp;<i class="blue"><s:property value="count" /></i>&nbsp;&nbsp;
			<s:text name="record_message" />
			&nbsp;&nbsp;<i class="blue"><s:property value="pageNow" />&nbsp;&nbsp;</i>
			<s:text name="page_total" />
			&nbsp;&nbsp;<i class="blue"><s:property value="pageTotal" />&nbsp;&nbsp;</i>
			<s:text name="page_end" />
		</div>
		<form name="pageForm" id="pageForm">
			<s:hidden id="pageNow" name="pageNow" />
			<s:hidden id="pageTotal" name="pageTotal" />
			<ul class="paginList">
				<li class="paginItem"><s:if test="pageNow == 1">
						<span class="pagepre01">&nbsp;&nbsp;</span>
					</s:if> <s:else>
						<span class="pagepre02"><a href="javascript:prePage();">&nbsp;&nbsp;</a></span>
					</s:else></li>
				<li class="paginItem"><label>&nbsp;&nbsp;<s:text name="no" />&nbsp;&nbsp;<s:property
							value="pageNow" />&nbsp;&nbsp;<s:text name="page" />&nbsp;&nbsp;
				</label></li>
				<li class="paginItem"><s:if test="pageNow == pageTotal">
						<span class="pagenxt01">&nbsp;&nbsp;</span>
					</s:if> <s:else>
						<span class="pagenxt02"><a href="javascript:nextPage();">&nbsp;&nbsp;</a></span>
					</s:else></li>
				<li class="paginItem"><span><input type="text" class="twoinput"
						id="pageInput" name="pageInput">&nbsp;<input name="" type="button"
						class="btn2" onclick="load();" value='<s:text name="confirm" />' /></span></li>
			</ul>
		</form>
	</div>
</div>

<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	
</script>