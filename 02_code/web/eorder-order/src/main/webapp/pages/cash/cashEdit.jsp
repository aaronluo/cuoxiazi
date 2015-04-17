<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="../resources/js/cash.js"></script>
<script type="text/javascript" src="../resources/js/jquery.PrintArea.js"></script>

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
					<th><s:text name="dish_price_thin_label" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="order.orderItems" status="status">
					<tr class='<s:if test="#status.even">odd</s:if>'>
						<td><s:property value="dish.name" /></td>
						<td width="10%"><s:property value="dishAmount" /></td>
						<td width="20%"><fmt:formatNumber value="${dish.price}"
								pattern="0.00" /></td>
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
				<s:hidden id="order.orderItems.size" name="order.orderItems.size" />
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
					<li><label>&nbsp;&nbsp;</label><input name="" id="btn_center"
						type="button" class="scbtn" style="width: 130px;"
						value="<s:text name='print_label' />" />&nbsp;&nbsp;&nbsp;&nbsp; <input
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
</div>

<div class="pop_print_window" id="pop_print_windowid">
	<div id="title" class="poptitle">
		<img src="../resources/images/close1.png" />
		<s:text name="print_label" />
	</div>
	<div class="pop_print_content" id="pop_print_contentid">
		<div id="printContent" class="rightinfo">
			<div style="width: 550px;">
				<div style="padding-left: 25px; width: 430px">
					<span class="print_page_title"><s:text name="order.print_page_label" /></span>
				</div>
				<ul class="print_label">
					<li><label><s:text name="member_label" /></label><span
						class="labelvalue" style="width: 180px;"><s:property value="order.orderSeq" /></span></li>
					<li><label><s:text name="table_number_label" /></label><span
						class="labelvalue"><s:property value="order.tableNumber" /></span></li>
				</ul>
				<ul class="print_label">
					<li><label><s:text name="order_create_date_label" /></label>
						<div class="labelvalue" style="width: 180px;">
							<s:date name="order.createDate" format="yyyy-MM-dd HH:mm:ss" />
						</div></li>
					<li><label><s:text name="cashier_label" /></label><span
						class="labelvalue"><s:property value="order.casher.username" /></span></li>
				</ul>
				<div style="padding-left: 25px">
					<table class="print_tablelist" style="width: 430px">
						<thead>
							<tr>
								<th><s:text name="dish_name_label" /></th>
								<th><s:text name="amount_label" /></th>
								<th><s:text name="dish_price_thin_label" /></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="order.orderItems" status="status">
								<tr class='<s:if test="#status.even">odd</s:if>'>
									<td><s:property value="dish.name" /></td>
									<td width="10%"><s:property value="dishAmount" /></td>
									<td width="20%"><fmt:formatNumber value="${dish.price}"
											pattern="0.00" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>

				<ul class="print_label">
					<li><label><b><s:text name="total_price" /></b></label><span
						class="labelvalue"><s:property value="order.totalPrice" /></span></li>
					<li><label><b><s:text name="real_price_label" /></label></b><span
						class="labelvalue"><s:property value="order.discountPrice" /></span></li>
				</ul>

			</div>
		</div>
		<ul class="seachform" style="padding-left: 50px">
			<li style="padding-left: 135px"><input name="" id="btnPrint"
				type="button" class="scbtn" style="width: 130px;"
				value="<s:text name='print_thin_label' />" /></li>
		</ul>
	</div>
</div>