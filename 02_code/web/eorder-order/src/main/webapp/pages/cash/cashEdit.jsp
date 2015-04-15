<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript" src="../resources/js/cash.js"></script>
<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="cash.action"><s:text name="cash_label" /></a></li>
		<li><s:text name="order_detail_label" /></li>
	</ul>
</div>

<div class="rightinfo">
	<div class="orderItemTablePanel">
		<div class="formtitle">
			<span><s:text name="order_detail_label" /></span>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><s:text name="dish_name_label" /></th>
					<th><s:text name="amount_label" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="order.orderItems" status="status">
					<tr class='<s:if test="#status.even">odd</s:if>'>
						<td width="70%"><s:property value="dish.name" /></td>
						<td><s:property value="dishAmount" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div class="orderItemPanel">
		<div class="formtitle">
			<span><s:text name="other_info_label" /></span>
		</div>
		<div class="tablePanel">
			<form id="orderForm" name="orderForm">
				<s:hidden id="orderId" name="orderId" />
				<s:hidden id="order.id" name="order.id" />
				<s:hidden id="order.totalPrice" name="order.totalPrice" />
				<s:hidden id="order.orderStatus" name="order.orderStatus" />
				<ul class="forminfo">
					<li><label><s:text name="table_number_label" /></label><input
						id="order.tableNumber" name="order.tableNumber"
						value="${order.tableNumber}" type="text" class="dfinput"
						style="width: 280px;" readonly="readonly" /></li>
					<li><label><s:text name="servent_label" /></label><input
						id="order.servent.username" name="order.servent.username"
						value="${order.servent.username}" type="text" class="dfinput"
						style="width: 280px;" readonly="readonly" /></li>
					<li><label><s:text name="member_label" /></label> <input
						id="order.member.cellphone" name="order.member.cellphone"
						value="${order.member.cellphone}" type="text" class="dfinput"
						style="width: 280px;" /></li>
					<s:if
						test="null == order.member || null == order.member.memberShip || null == order.member.memberShip.discount || '' == order.member.memberShip.discount">
						<li><label>&nbsp;&nbsp;</label><input name="" type="button"
							class="btn" style="width: 130px;" onclick="discount();"
							value="<s:text name='update_user_info_label' />" /></li>
					</s:if>
				</ul>
			</form>
			<form id="saveForm" name="saveForm">
				<s:hidden id="order.tableNumber" name="order.tableNumber" />
				<s:hidden id="orderId" name="orderId" />
				<s:hidden id="order.id" name="order.id" />
				<s:hidden id="order.orderStatus" name="order.orderStatus" />
				<ul class="forminfo">
					<li><label><s:text name="total_price" /></label><input
						id="order.totalPrice" name="order.totalPrice" value="${order.totalPrice}"
						type="text" class="dfinput" style="width: 280px;" readonly="readonly" /></li>
					<li><label><s:text name="level_discount" /></label><input
						id="order.member.memberShip.discount"
						name="order.member.memberShip.discount"
						value="${order.member.memberShip.level.discount}" type="text"
						class="dfinput" style="width: 280px;" readonly="readonly" /></li>
					<li><label><s:text name="real_price_label" /></label> <input
						id="order.discountPrice" name="order.discountPrice"
						value="${order.discountPrice}" type="text" class="dfinput"
						style="width: 280px;" readonly="readonly" /></li>
					<li><label>&nbsp;&nbsp;</label><input name="" type="button"
						class="btn" style="width: 130px;" onclick="print();"
						value="<s:text name='print_label' />" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" class="btn" style="width: 130px;" onclick="save();"
						value="<s:text name='confirm_save' />" /></li>
				</ul>
			</form>
		</div>
		<div class="msgPanel">
			<s:property value="message" />
			<s:fielderror />
		</div>
	</div>