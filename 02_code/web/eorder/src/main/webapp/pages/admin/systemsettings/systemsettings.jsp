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
				<th scope="col" class="top-bg" align="left">Settings</th>
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
													<td align="right" width="40%"><label>Activation
															Fee Amount: </label></td>
													<td align="left" width="40%"><input type="text" /></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right" width="40%"><label>Each File
															Upload Balance: </label></td>
													<td align="left" width="40%"><input type="text" /></td>
													<td width="20%"></td>
												</tr>
												<tr>
													<td align="right"><label>Welcome message:</label></td>
													<td align="left"><input type="text" /></td>
													<td></td>
												</tr>
												<tr>
													<td align="right"></td>
													<td align="left"><input type="submit" value="Save" />
														<input type="submit" value="Revert" /></td>
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