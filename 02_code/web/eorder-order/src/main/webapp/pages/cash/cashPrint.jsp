<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="../resources/js/cash.js"></script>
<script type="text/javascript" src="../resources/js/jquery.PrintArea.js"></script>

<div id="printContent" class="rightinfo">
	<div style="width: 90%">
		<div style="padding-left: 25px; width: 410px">
			<span class="print_page_title"><s:text name="order.print_page_label" /></span>
		</div>
		<ul class="seachform" style="padding-left: 50px">
			<li><label><s:text name="member_label" /></label><span
				class="labelvalue"><s:property value="order.orderSeq" /></span></li>
			<li><label><s:text name="table_number_label" /></label><span
				class="labelvalue"><s:property value="order.tableNumber" /></span></li>
		</ul>
		<ul class="seachform" style="padding-left: 50px">
			<li><label><s:text name="member_label" /></label> <span
				class="labelvalue"><s:date name="order.createDate"
						format="yyyy-MM-dd HH:mm:ss" /></span></li>
			<li><label><s:text name="cashier_label" /></label><span
				class="labelvalue"><s:property value="order.casher.username" /></span></li>
		</ul>
		<div style="padding-left: 25px">
			<table class="tablelist" style="width: 410px">
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

		<ul class="seachform" style="padding-left: 50px">
			<li><label><s:text name="total_price" />:&nbsp;&nbsp;</label><span
				class="labelvalue"><s:property value="order.totalPrice" /></span></li>
			<li><label><s:text name="real_price_label" /></label><span
				class="labelvalue"><s:property value="order.discountPrice" /></span></li>
		</ul>

	</div>
</div>
<ul class="seachform" style="padding-left: 50px">
	<li><label>&nbsp;&nbsp;&nbsp;&nbsp;</label><input name="" id="btnPrint" type="button"
		class="scbtn" style="width: 130px;" value="<s:text name='print_thin_label' />" /></li>
	<li><label>&nbsp;&nbsp;</label><input name="" type="button" class="btn"
		style="width: 130px;" onclick="window.close();"
		value="<s:text name='cancel_label' />" /></li>
</ul>