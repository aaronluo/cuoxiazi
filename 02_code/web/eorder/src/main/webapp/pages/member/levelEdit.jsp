<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/level.js"></script>

<s:if test="level.id == null || level.id == 0L">
<!-- 创建新的会员等级 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="list.action"><s:text name="level_name" /></a></li>
			<li><s:text name="level_add" /></li>
		</ul>
	</div>
	
	<div class="rightinfo" style="margin-top:50px">
		<div	class="tablePanel" >
			<form id="saveForm" name="saveForm" action="save">
				<ul class="forminfo">
					<li><label><s:text name="level_name_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.name" />
					</li>
					<li><label><s:text name="level_discount_label"/></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.discount" />
					</li>
					<li><label><s:text name="level_score_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.levelScore" />
					</li>						
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="saveBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="submit" class="btn" onclick="save();"
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
<!-- 更新会员等级 -->
	<div class="place">
		<span><s:text name="place" /></span>
		<ul class="placeul">
			<li><a href="list.action"><s:text name="level_name" /></a></li>
			<li><s:text name="level_edit" /></li>
		</ul>
	</div>
	
	<div class="rightinfo" style="margin-top:50px">
		<div	class="tablePanel" >
			<form id="updateForm" name="updateForm" action="update">
				<s:hidden name="level.id"  />
				<ul class="forminfo">
					<li><label><s:text name="level_name_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.name" />
					</li>
					<li><label><s:text name="level_discount_label"/></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.discount" />
					</li>
					<li><label><s:text name="level_score_label" /></label>
					<s:textfield cssClass="dfinput"  type="text"  name="level.levelScore" />
					</li>						
					<li><label>&nbsp;&nbsp;</label><input id="btnSave" name="btnSave"
						type="button" class="btn" onclick="updateBack();"
						value="<s:text name='back_to_preview' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="submit" class="btn" onclick="update();"
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