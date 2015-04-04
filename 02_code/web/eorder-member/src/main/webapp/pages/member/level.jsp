<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/level.js"></script>

<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="level_name" /></a></li>
	</ul>
</div>

<div class="rightinfo">
	<!-- 添加会员等级按钮 -->
	<div class="tools">
		<ul class="toolbar">
			<li><a href="<s:url action="add" />"><span><img
						src="../resources/images/t01.png"></span> <s:text name="add" /></a></li>
		</ul>
		
	</div>
	<!-- 会员等级列表 -->
	<div	class="tablePanel">
		<table class="tablelist">
			<thead>
				<tr>
					<th><s:text name="level_name" /></th>
					<th><s:text name="level_discount" /></th>
					<th><s:text name="level_score" /></th>
					<th width="8%"><s:text name="edit" /></th>
					<th width="8%"><s:text name="delete" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="levels" status="status">
				<tr class='<s:if test="#status.even">odd</s:if>'>
					<td><s:property value="levelName" /></td>
					<td><s:property value="discount" /></td>
					<td><s:property value="levelScore" /></td>
					<td><a
							href='<s:url action="edit"><s:param name="id" value="id" /></s:url>'
							class="tablelink"><s:text name="edit" /></a></td>
					<td><a
							href='<s:url action="remove"> <s:param name="level.id" value="id" /></s:url>'
							class="tablelink"><s:text name="delete" /></a></td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 分页信息 -->
		<div class="pagin" style="padding:0 0">
			<div class="message"  style="width:60%;float:left">
				<s:text name="total" />
				&nbsp;&nbsp;<i class="blue"><s:property value="count" /></i>&nbsp;&nbsp;
				<s:text name="record_message" />
				&nbsp;&nbsp;<i class="blue"><s:property value="pageNow" />&nbsp;&nbsp;</i>
				<s:text name="page_total" />
				&nbsp;&nbsp;<i class="blue"><s:property value="pageTotal" />&nbsp;&nbsp;</i>
				<s:text name="page_end" />
			</div>
			<!-- 分页跳转 -->
			<div class="message" style="width:40%;float:right">
				<form name="pageForm" id="pageForm">
					<s:hidden id="pageNow" name="pageNow" />
					<s:hidden id="pageTotal" name="pageTotal" />
					<ul class="paginList" style="margin-right:-12px">
						<li class="paginItem">
							<s:if test="pageNow == 1">
								<span class="pagepre01">&nbsp;&nbsp;</span>
							</s:if> 
							<s:else>
								<span class="pagepre02"><a href="javascript:prePage();">&nbsp;&nbsp;</a></span>
								</s:else>
						</li>
						<li class="paginItem"><label>&nbsp;&nbsp;<s:text name="no" />&nbsp;&nbsp;
							<s:property value="pageNow" />&nbsp;&nbsp;<s:text name="page" />&nbsp;&nbsp;</label>
						</li>
						<li class="paginItem">
							<s:if test="pageNow == pageTotal">
								<span class="pagenxt01">&nbsp;&nbsp;</span>
							</s:if> 
							<s:else>
								<span class="pagenxt02"><a href="javascript:nextPage();">&nbsp;&nbsp;</a></span>
							</s:else>
						</li>
						<li class="paginItem"><span><input type="text" class="twoinput"
								id="pageInput" name="pageInput">&nbsp;<input name="" type="button"
								class="btn2" onclick="load();" value='<s:text name="confirm" />' /></span>
						</li>
					</ul>
				</form>
			</div>	
		</div>
	</div>
	
	<!-- 消息显示 -->
	<div class="msgPanel">
		<s:property value="message" />
	</div>
</div>