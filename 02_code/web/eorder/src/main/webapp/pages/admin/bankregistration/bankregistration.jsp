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
				<th scope="col" class="top-bg" align="left">Register A Bank</th>
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
													<td align="right" width="40%"><label>Bank
															Name: </label></td>
													<td align="left" width="40%"><input type="text"
														value="" /></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right" width="40%"><label>Abbreviation:
													</label></td>
													<td align="left" width="40%"><input type="text"
														value="" /></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right" width="40%"><label>Address:
													</label></td>
													<td align="left" width="40%"><input type="text"
														value="" /></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right"><label>Email Address:</label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Contact Number: </label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Fax Number: </label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Incharge Person Name:
													</label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Bank Code: </label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"><label>Upload Bank Logo
															(gif/jpg/png): </label></td>
													<td align="left"><input type="File" value="" /></td>
													<td></td>
												</tr>

												<tr>
													<td colspan="3" align="left">
														<hr>
													</td>
													<td></td>
												</tr>

												<tr>
													<td align="right"><label>Activation Fee
															Amount: </label></td>
													<td align="left"><input type="text" value="1,000"
														readonly="readonly" /></td>
													<td></td>
												</tr>

												<tr>
													<td align="right"><label>Initial Balance: </label></td>
													<td align="left"><input type="text" value="" /></td>
													<td></td>
												</tr>

												<tr>
													<td align="right"><label>Number of files that
															can upload: </label></td>
													<td align="left"><input type="text" value="9"
														readonly="readonly" /></td>
													<td></td>
												</tr>

												<tr>
													<td colspan="3" align="left">
														<hr>
													</td>
													<td></td>
												</tr>

												<tr>
													<td align="right"></td>
													<td align="left"><input type="submit" value="Submit" />
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