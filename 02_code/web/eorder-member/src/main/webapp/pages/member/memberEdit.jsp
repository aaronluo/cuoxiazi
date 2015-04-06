<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/member.js"></script>

<s:if test="user.id == null || user.id == 0L">
<!-- 创建新会员 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="list.action"><s:text name="member_name" /></a></li>
			<li><s:text name="member_add" /></li>
		</ul>
	</div>
	
	<div class="rightinfo" style="margin-top:50px">
		<div	class="tablePanel" >
			<form id="saveForm" name="saveForm" action="save">
				<ul class="forminfo">
					<li><label><s:text name="member_id_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="user.cellphone"  cssStyle="width:285px"/>
					</li>
					<li><label><s:text name="level_name" /></label>
						<s:select 
							list="levels" listKey="id" listValue="levelName" value="level.id" name="level.id"
							cssStyle="border:1px dotted blue;height:30px;width:120px">
						</s:select>
					</li>						
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="save();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
		</div>
	</div>
</s:if>
<s:else>
<!-- 更新用户会员 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="list.action"><s:text name="member_name" /></a></li>
			<li><s:text name="member_edit" /></li>
		</ul>
	</div>
	
	<div class="rightinfo" style="margin-top:50px">
		<div	class="tablePanel" >
			<form id="updateForm" name="updateForm" action="update">
				<s:hidden name="user.cellphone" />
				<s:hidden name="level.id" />
				<ul class="forminfo">
					<li><label><s:text name="member_id_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="user.cellphone"  disabled="true" cssStyle="width:285px"/>
					</li>
					<li><label><s:text name="level_name" /></label>
						<s:select 
							list="levels" listKey="id" listValue="levelName" value="level.id" name="level.id"
							cssStyle="border:1px dotted blue;height:30px;width:120px" disabled="true">
						</s:select>
					</li>
					<li>
						<label><s:text name="member_score"/></label>
						<s:textfield cssClass="dfinput" type="text" name="user.memberShip.currentScore" cssStyle="width:285px"/>
					</li>
					<li><label>&nbsp;&nbsp;</label><input id="btnUpdate" name="btnUpdate"
						type="button" class="btn" onclick="updateBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" onclick="update();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
		</div>
	</div>
</s:else>