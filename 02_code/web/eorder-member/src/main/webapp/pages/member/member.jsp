<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/member.js"></script>

<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="member_name" /></a></li>
	</ul>
</div>

<div class="rightinfo">

	
	<!-- 下拉选择会员等级 -->
	<div class="tools" >
		<s:form name="listForm" action="list">
		<s:hidden name="pageNow" />
		<s:hidden name="pageInput" />
		<div style="font-weight:bold;float:left;width:65px;margin-left:5px;height:15px;padding:10px 6px">
			<label><s:text name="level_name" />:</label>
		</div>
		<div>
			<s:select 
				list="levels" listKey="id" listValue="levelName" value="level.id" name="level.id"
				headerKey="" headerValue=""
				cssStyle="border:1px dotted blue;height:30px;width:120px">
			</s:select>
			<input type="submit" value='<s:text name="query" />' class="btn" />
		</div>
		</s:form>
	</div>
	
	<s:if test="level.id != null && level.id > 0">
	<!-- 添加会员用户按钮 -->
	<div class="tools">
		<ul class="toolbar">
			<li><a href='<s:url action="add" ><s:param name="level.id" value="level.id"/></s:url>'><span><img
						src="../resources/images/t01.png"></span> <s:text name="add" /></a></li>
		</ul>
	</div>
	<!-- 用户会员列表 -->
	<div	class="tablePanel">
		<table class="tablelist">
			<thead>
				<tr>
					<th width="20%"><s:text name="member_id" /></th>
					<th><s:text name="level_name" /></th>
					<th><s:text name="level_discount" /></th>
					<th><s:text name="member_score" /></th>
					<th width="8%"><s:text name="edit" /></th>
					<th width="8%"><s:text name="delete" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="users" status="status" >
				<tr class='<s:if test="#status.even">odd</s:if>'>
					<td><s:property value="cellphone" /></td>
					<td><s:property value="memberShip.level.levelName" /></td>
					<td><s:property value="memberShip.level.discount" /></td>
					<td><s:property value="memberShip.currentScore" /></td>
					<td><a
							href='<s:url action="edit"><s:param name="user.id" value="id" /></s:url>'
							class="tablelink"><s:text name="edit" /></a></td>
					<td><a
							href='<s:url action="remove"> <s:param name="user.id" value="id" /></s:url>'
							class="tablelink"><s:text name="delete" /></a></td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		
		<!-- 分页信息 -->
		<s:if test="users.size != 0">
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
					<s:hidden id="levelId" name="level.id" />
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
		</s:if>
	</div>
	</s:if>
	<!-- 消息显示 -->
	<div class="msgPanel">
		<s:property value="message" />
	</div>
</div>