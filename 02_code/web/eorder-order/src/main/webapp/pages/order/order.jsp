<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<script type="text/javascript" src="../resources/js/order.js"></script>
<script language="javascript" src="../resources/js/JTimer.js"></script>
<sd:head parseContent="true" />
<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="order_label" /></a></li>
	</ul>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div id="tab2" class="tabson">
			<form name="queryForm" id="queryForm">
				<s:hidden id="order.cashierId" name="order.cashierId" />
				<s:hidden id="order.serventId" name="order.serventId" />
				<s:hidden id="order.status" name="order.status" />
				<ul class="seachform">
					<li><label><s:text name="order_seq_label" /></label><input
						id="order.orderSeq" name="order.orderSeq" value="${order.orderSeq}"
						type="text" class="scinput" /></li>
					<li><label><s:text name="member_label" /></label><input
						id="order.cellphone" name="order.cellphone" value="${order.cellphone}"
						type="text" class="scinput" /></li>
					<li><label><s:text name="order_status_label" /></label> <s:select
							headerKey="0" headerValue=" " list="statusList" listKey="key"
							listValue="value" id="statusSelect" name="statusSelect"
							cssStyle="border:1px dotted blue;height:30px;width:130px"></s:select></li>
					<li><label><s:text name="cashier_label" /></label> <s:select
							headerKey="0" headerValue=" " list="cashierList" listKey="id"
							listValue="username" id="cashierSelect" name="cashierSelect"
							cssStyle="border:1px dotted blue;height:30px;width:130px"></s:select></li>
					<li><label><s:text name="servent_label" /></label> <s:select
							headerKey="0" headerValue=" " list="serventList" listKey="id"
							listValue="username" id="serventSelect" name="serventSelect"
							cssStyle="border:1px dotted blue;height:30px;width:130px"></s:select></li>
				</ul>
				<ul class="seachform">
					<li><label><s:text name="order_create_at_min" /></label><input
						id="order.createAtMin" name="order.createAtMin"
						value='<s:date name="order.createAtMin" format="yyyy-MM-dd" />'
						type="text" class="scinput" readonly onclick="JTC.setday(this)"> </input></li>
					<li><label><s:text name="order_create_at_max" /></label><input
						id="order.createAtMax" name="order.createAtMax"
						value='<s:date name="order.createAtMax" format="yyyy-MM-dd" />'
						type="text" class="scinput" readonly onclick="JTC.setday(this)" /></li>
					<li><label><s:text name="order_total_price_min" /></label><input
						id="order.totalPriceMin" name="order.totalPriceMin"
						value="${order.totalPriceMin}" type="text" class="scinput" /></li>
					<li><label><s:text name="order_total_price_max" /></label><input
						id="order.totalPriceMax" name="order.totalPriceMax"
						value="${order.totalPriceMax}" type="text" class="scinput" /></li>
					<li><input name="" type="button" class="scbtn" onclick="query();"
						value='<s:text name="query" />' /></li>
				</ul>
			</form>

			<!-- 菜品列表 -->
			<div class="tablePanel">
				<table class="tablelist">
					<thead>
						<tr>
							<th width="18%"><s:text name="order_seq_label" /></th>
							<th width="8%"><s:text name="table_number_label" /></th>
							<th width="8%"><s:text name="attendee_number_label" /></th>
							<th width="8%"><s:text name="total_price_label" /></th>
							<th width="8%"><s:text name="discount_price_label" /></th>
							<th width="8%"><s:text name="order_status_label" /></th>
							<th width="8%"><s:text name="servent_label" /></th>
							<th width="12%"><s:text name="member_label" /></th>
							<th width="8%"><s:text name="cashier_label" /></th>
							<th><s:text name="order_create_date_label" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="orders" status="status">
							<tr class='<s:if test="#status.even">odd</s:if>'>
								<td><s:property value="orderSeq" /></td>
								<td><s:property value="tableNumber" /></td>
								<td><s:property value="attendeeNumber" /></td>
								<td><s:property value="totalPrice" /></td>
								<td><s:property value="discountPrice" /></td>

								<td><s:if test="100==orderStatus">
										<s:text name="order_status_new" />
									</s:if> <s:elseif test="101==orderStatus">
										<s:text name="order_status_submitted" />
									</s:elseif> <s:else>
										<s:text name="order_status_paid" />
									</s:else></td>
								<td><s:property value="servent.username" /></td>
								<td><s:property value="member.cellphone" /></td>
								<td><s:property value="casher.username" /></td>
								<td><s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<s:if test="0 != count">
					<!-- 分页信息 -->
					<div class="pagin" style="padding: 0 0">
						<div class="message" style="width: 60%; float: left">
							<s:text name="total" />
							&nbsp;&nbsp;<i class="blue"><s:property value="count" /></i>&nbsp;&nbsp;
							<s:text name="record_message" />
							&nbsp;&nbsp;<i class="blue"><s:property value="pageNow" />&nbsp;&nbsp;</i>
							<s:text name="page_total" />
							&nbsp;&nbsp;<i class="blue"><s:property value="pageTotal" />&nbsp;&nbsp;</i>
							<s:text name="page_end" />
						</div>
						<!-- 分页跳转 -->
						<div class="message" style="width: 40%; float: right">
							<form name="pageForm" id="pageForm">
								<s:hidden id="pageNow" name="pageNow" />
								<s:hidden id="pageTotal" name="pageTotal" />
								<ul class="paginList" style="margin-right: -12px">
									<li class="paginItem"><s:if test="pageNow == 1">
											<span class="pagepre01">&nbsp;&nbsp;</span>
										</s:if> <s:else>
											<span class="pagepre02"><a href="javascript:prePage();">&nbsp;&nbsp;</a></span>
										</s:else></li>
									<li class="paginItem"><label>&nbsp;&nbsp;<s:text name="no" />&nbsp;&nbsp;
											<s:property value="pageNow" />&nbsp;&nbsp;<s:text name="page" />&nbsp;&nbsp;
									</label></li>
									<li class="paginItem"><s:if test="pageNow == pageTotal">
											<span class="pagenxt01">&nbsp;&nbsp;</span>
										</s:if> <s:else>
											<span class="pagenxt02"><a href="javascript:nextPage();">&nbsp;&nbsp;</a></span>
										</s:else></li>
									<li class="paginItem"><span><input type="text"
											class="twoinput" id="pageInput" name="pageInput">&nbsp;<input
											name="" type="button" class="btn2" onclick="load();"
											value='<s:text name="confirm" />' /></span></li>
								</ul>
							</form>
						</div>
					</div>
				</s:if>
			</div>

			<!-- 消息显示 -->
			<div class="msgPanel">
				<s:property value="message" />
			</div>
		</div>
	</div>
</div>