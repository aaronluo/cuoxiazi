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
				<th scope="col" class="top-bg" align="left">Bank Payment</th>
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
													<td><label>Bank Name</label></td>
													<td><input type="text" /></td>
													<td><label>Address</label></td>
													<td><input type="text" /></td>
												</tr>
												<tr>
													<td><label>Email Address</label></td>
													<td><input type="text" /></td>
													<td><label>Contract Number</label></td>
													<td><input type="text" /></td>
												</tr>
												<tr>
													<td><label>Incharge Person Name</label></td>
													<td><input type="text" /></td>
													<td><label>Bank Code</label></td>
													<td><input type="text" /></td>
												</tr>
												<tr>
													<td colspan="4" align="left" valign="middle"><input
														type="button" value="Search" class="search_button" /></td>
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
											<th width="10px"><label>No.</label></th>
											<th><label><a href="#">Bank Name</a></label></th>
											<th><label><a href="#">Activation Fee Amount</a></label></th>
											<th><label><a href="#">Initial Balance</a></label></th>
											<th><label><a href="#">Outstanding Credit
														Balance</a></label></th>
											<th><label><a href="#">Numbers of Files <br>
														that can upload
												</a></label></th>
											<th width="40px"><label>Operations</label></th>
										</tr>
										<tr>
											<td>1</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update"
												onclick="document.location.href='./bankpayment_update.html'" /></td>
										</tr>
										<tr>
											<td>2</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>3</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>4</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>5</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>6</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>7</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>8</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>9</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td>10</td>
											<td>China Bank</td>
											<td>1,000</td>
											<td>3,000</td>
											<td>7,000</td>
											<td>3</td>
											<td><input type="button" value="Update" /></td>
										</tr>
										<tr>
											<td colspan="7" class="table-footer"><span>1/20
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