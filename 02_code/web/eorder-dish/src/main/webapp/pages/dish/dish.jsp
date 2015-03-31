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
<script type="text/javascript" src="../resources/js/dish.js"></script>

<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="dish_manage" /></a></li>
	</ul>
</div>

<div class="formbody">
	<div id="usual1" class="usual">
		<div id="tab2" class="tabson">
			<form name="queryForm" id="queryForm">
				<s:hidden id="categoryId" name="categoryId" />
				<ul class="seachform">
					<li><label><s:text name="category_name" /></label> <select
						id="dishSelect" name="dishSelect" class="select3" style="width: 100px;">
							<s:iterator value="categoryVOList" id="category">
								<option value='<s:property value="#category.id" />'><s:property
										value="#category.name" /></option>
							</s:iterator>
					</select></li>

					<li><label>&nbsp;</label><input name="" type="button" class="scbtn"
						onclick="query();" value='<s:text name="query" />' /></li>
				</ul>
			</form>

			<div class="operatemessage">
				<s:property value="message" />
			</div>

			<s:if test="null != categoryId && null != dishvos">
				<div class="tools">
					<form name="addForm" id="addForm">
						<s:hidden id="categoryId" name="categoryId" />
						<ul class="toolbar" onclick="add();">
							<li><span><img src="../resources/images/t01.png"></span> <s:text
									name="add" /></li>
						</ul>
					</form>
				</div>
				<s:if test="0 != pageTotal">
					<s:form id="dishForm" action="remove" theme="simple">
						<!--列表表格-->
						<table class="tablelist">
							<thead>
								<tr>
									<th width="18%"><s:text name="dish_name" /></th>
									<th width="8%"><s:text name="dish_price" /></th>
									<th><s:text name="dish_picture" /></th>
									<th width="8%"><s:text name="edit" /></th>
									<th width="8%"><s:text name="delete" /></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="dishvos">
									<tr>
										<td><s:property value="name" /></td>
										<td><s:property value="price" /></td>
										<td class="imgtd"><img
											style="height: 64px; width: 64px; padding: 5px;"
											src='../resources/images<s:property value="picPath" />'></td>
										<td><a
											href='<s:url action="edit"><s:param name="id" value="id" /></s:url>'
											class="tablelink"><s:text name="edit" /></a></td>
										<td><a
											href='<s:url action="remove"> <s:param name="categoryId" value="categoryId" /><s:param name="id" value="id" /></s:url>'
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
							<s:hidden id="categoryId" name="categoryId" />
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
								<li class="paginItem"><span><input type="text"
										class="twoinput" id="pageInput" name="pageInput">&nbsp;<input
										name="" type="button" class="btn2" onclick="load();"
										value='<s:text name="confirm" />' /></span></li>
							</ul>
						</form>
					</div>
				</s:if>
			</s:if>

			<script type="text/javascript">
				$('.tablelist tbody tr:odd').addClass('odd');
			</script>

		</div>
	</div>
</div>