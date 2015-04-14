<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="place">
	<span><s:text name="place" /></span>
	<ul class="placeul">
		<li><a href="#"><s:text name="cash_label" /></a></li>
	</ul>
</div>

<div class="rightinfo">
	<div class="tablePanel">
		<table>
			<tbody>
				<c:forEach var="itemi" begin="0" end="3" varStatus="status">
					<tr>
						<c:forEach var="itemj" begin="1" end="5" varStatus="status">
							<s:iterator value="tableStatus" status="status">
								<s:if test="key == (#attr.itemi*5+#attr.itemj)">
									<s:if test="null != value">
										<td><a
											href='<s:url action="edit"><s:param name="orderId" value="value.id" /></s:url>'
											class="tablelink"><div class="divred">
													<s:property value="key" />
												</div></a></td>
									</s:if>
									<s:else>
										<td><div class="divgreen">
												<s:property value="key" />
											</div</td>
									</s:else>
									</td>
								</s:if>
							</s:iterator>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 消息显示 -->
	<div class="msgPanel">
		<s:property value="message" />
	</div>
</div>