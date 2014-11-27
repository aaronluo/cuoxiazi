<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resources/js/prototype.lite.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>

<link href="../resources/css/skin.css" rel="stylesheet" type="text/css">
<link href="../resources/css/basic.css" rel="stylesheet" type="text/css">
<link href="../resources/css/content.css" rel="stylesheet"
	type="text/css">
</head>

<script>
	
</script>

<body>
	<table class="table-container" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tbody>
			<tr>
				<th scope="col" class="top-left"></th>
				<th scope="col" class="top-bg" align="left">Report Entity
					Information</th>
				<th scope="col" class="top-right"></th>
			</tr>
			<tr>
				<td class="middle-left" valign="middle">&nbsp;</td>
				<td class="middle-content">
					<table class="container-conter" width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<!--Search panel-->
						<tr>
							<td>
								<div class="table-frame-div">
									<div class="table-frame-div2">
										<table class="search_condition" width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<td align="right" width="20%"><label><b>Bank
																Name: </b></label></td>
													<td align="left" width="20%">&nbsp;&nbsp;China Bank</td>
													<td align="right" width="20%"><label><b>User
																Name: </b> </label></td>
													<td align="left" width="20%">&nbsp;&nbsp;CBNK001</td>
													<td align="right" width="10%"></td>
													<td align="left" width="10%"></td>
												</tr>
												<tr>
													<td align="right"><label><b>Numbers
																[Uploaded Files]:</b> </label></td>
													<td align="left">&nbsp;&nbsp;3(successful)/1(failed)</td>
													<td align="right"><label><b>Numbers of
																Files that can upload:</b> </label></td>
													<td align="left">&nbsp;&nbsp;3</td>
													<td align="right"></td>
													<td align="left"></td>
												</tr>
												<tr>
													<td align="right"><label><b>Outstanding
																Credit Balance</b></label></td>
													<td align="left">&nbsp;&nbsp;3,000</td>
													<td align="right"><label><b>Credit Usage</b></label></td>
													<td align="left">&nbsp;&nbsp;7,000</td>
													<td align="right"></td>
													<td align="left"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td class="middle-right" valign="middle"></td>
			</tr>
			<tr>
				<td class="bottom-left" valign="bottom"></td>
				<td class="bottom-bg" align="left"></td>
				<td class="bottom-right" valign="bottom"></td>
			</tr>
		</tbody>
	</table>

	<table class="table-container" width="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tbody>
			<tr>
				<th scope="col" class="top-left"></th>
				<th scope="col" class="top-bg" align="left">File Upload
					Transaction Details</th>
				<th scope="col" class="top-right"></th>
			</tr>
			<tr>
				<td class="middle-left" valign="middle">&nbsp;</td>
				<td class="middle-bg" valign="middle">


					<table class="container-conter" width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<!--Search panel-->
						<tr>
							<td>
								<div class="table-frame-div">
									<div class="table-frame-div2">
										<table class="search_condition" width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<td><label>Submission Profile</label></td>
													<td><select style="width: 200px;" name="select1"
														size="0">
															<option>All</option>
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
													</select></td>
													<td><label>Type of Reporting Category</label></td>
													<td><select style="width: 200px;" name="select1"
														size="0">
															<option>All</option>
															<option>Financial Reporting</option>
															<option>Industry Specific Reporting</option>
													</select></td>
												</tr>
												<tr>
													<td><label>Report Types</label></td>
													<td><select style="width: 200px;" name="select2"
														size="0">
															<option>All</option>
															<option>Financial Reporting</option>
															<option>Financial Reporting</option>
															<option>Financial Reporting</option>
															<option>Financial Reporting</option>
													</select></td>
													<td><label>Reporting Frequency</label></td>
													<td><select style="width: 200px;" name="select3"
														size="0">
															<option>All</option>
															<option>Monthly</option>
															<option>Monthly</option>
															<option>Monthly</option>
															<option>Monthly</option>
													</select></td>
												</tr>
												<tr>
													<td><label>Success/Failed</label></td>
													<td><select style="width: 200px;" name="select3"
														size="0">
															<option>All</option>
															<option>Success</option>
															<option>Failed</option>
													</select></td>
													<td><label></label></td>
													<td></td>
												</tr>

												<tr>
													<td colspan="4" align="left" valign="middle"><input
														type="button" value="Search" class="search_button" /> <input
														type="button" value="Print Result" class="search_button" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
						<!--Search panel end-->
						<tr>
							<td height="20"><hr></td>
						</tr>
						<!-- Result panel -->
						<tr>
							<td>
								<table class="search_result" width="100%" cellspacing="0"
									cellpadding="0">
									<tbody>
										<tr>
											<th><label>No.</label></th>
											<th><label><a href="#">Date & Time</a></label></th>
											<th><label>Submission Profile</label></th>
											<th><label>Type of Reporting Category</label></th>
											<th><label>Report Types</label></th>
											<th><label>Reporting Frequency</label></th>
											<th><label>File Name</label></th>
										</tr>
										<tr>
											<td>1</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>2</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>3</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>4</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>5</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>6</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>7</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>8</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>9</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td>10</td>
											<td>2012/2/10 3:50:10</td>
											<td>Commercial Banking Conventional</td>
											<td>Financial Reporting</td>
											<td>Report on Financial Position</td>
											<td>Monthly</td>
											<td>BI_Financial Position V1.0.xls</td>
										</tr>
										<tr>
											<td colspan="8" class="table-footer"><span>1/20
													pages.<a href="#">First</a> <a href="#">Next</a> <a
													href="#">Previous</a> <a href="#">Last</a> Jump to <input
													type="text" size="2" /> page.
											</span></td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<!-- Result panel end-->
					</table>
				</td>
				<td class="middle-right" valign="middle">&nbsp;</td>
			</tr>
			<tr>
				<td class="bottom-left" valign="bottom"></td>
				<td class="bottom-bg" align="left"></td>
				<td class="bottom-right" valign="bottom"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>