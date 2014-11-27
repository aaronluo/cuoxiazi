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
				<th scope="col" class="top-bg" align="left">File Upload</th>
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
													<td align="right" width="40%"><label>Submission
															Profile: </label></td>
													<td align="left" width="40%"><select
														style="width: 240px;" name="select1" size="0">
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
															<option>Commercial Banking Conventional</option>
													</select></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right" width="40%"><label>Type of
															Reporting Category: </label></td>
													<td align="left" width="40%"><select
														style="width: 240px;" name="select1" size="0">
															<option>Financial Reporting</option>
															<option>Industry Specific Reporting</option>
													</select></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right"><label>Report Types:</label></td>
													<td align="left"><select style="width: 240px;"
														name="select2" size="0">
															<option>Report on Financial Position Statement</option>
															<option>Report on Comprehensive Income Statement</option>
													</select></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Reporting Frequency:
													</label></td>
													<td align="left"><select style="width: 240px;"
														name="select3" size="0">
															<option>Monthly</option>
															<option>Monthly</option>
															<option>Monthly</option>
															<option>Monthly</option>
													</select></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>File: </label></td>
													<td align="left"><input type="file"
														value="Select File" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"></td>
													<td align="left"><input type="submit" value="Upload" />
													</td>
													<td></td>
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
</body>
</html>