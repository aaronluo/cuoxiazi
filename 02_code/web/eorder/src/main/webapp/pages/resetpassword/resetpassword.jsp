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
	<s:form action="doReset" namespace="/resetpassword">
		<table id="right-title" width="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="17" valign="top"
					background="../resources/image/mail_leftbg.gif"><img
					src="../resources/image/left-top-right.gif" width="17" height="29" /></td>
				<td valign="middle" background="../resources/image/content-bg.gif"><span
					class="right_title">Reset Password</span></td>
				<td width="16" valign="top"
					background="../resources/image/mail_rightbg.gif"><img
					src="../resources/image/nav-right-bg.gif" width="16" height="29" /></td>
			</tr>

			<tr>
				<td valign="middle" background="../resources/image/mail_leftbg.gif">&nbsp;</td>
				<td bgcolor="#F7F8F9">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="1" class=table_cls style="margin-top: 5px;">
						<tr>
							<td width="15%" height="26" class="STYLE1"><div
									align="right" class="STYLE2 STYLE1">Old Password:</div></td>
							<td width="85%" height="18" class="STYLE1"><input
								type="text" name="resetPasswordVo.oldPassword"></td>
						</tr>
						<tr>
							<td width="15%" height="26" class="STYLE1"><div
									align="right" class="STYLE2 STYLE1">New Password:</div></td>
							<td width="85%" height="18" class="STYLE1"><input
								type="password" name="resetPasswordVo.newPassword"></td>
						</tr>
						<tr>
							<td width="15%" height="26" class="STYLE1"><div
									align="right" class="STYLE2 STYLE1">Repeat New Password:</div></td>
							<td width="85%" height="18" class="STYLE1"><input
								type="password"></td>
						</tr>
						<tr>
							<td width="15%" height="26" class="STYLE1"><div
									align="right" class="STYLE2 STYLE1">&nbsp;</div></td>
							<td width="85%" class="STYLE1" align=left><input
								type="submit" value="Confirm" class="btn_confirm" /></td>
						</tr>
					</table>
				</td>
				<td valign="middle" background="../resources/image/mail_rightbg.gif">&nbsp;</td>
			</tr>
			
			<tr>
				<td width="17" valign="top"
					background="../resources/image/mail_leftbg.gif"><img
					src="../resources/image/left-top-right.gif" width="17" height="29" /></td>
				<td valign="middle" background="../resources/image/content-bg.gif"><span
					class="right_title"><s:actionerror />
					<s:actionmessage /></span></td>
				<td width="16" valign="top"
					background="../resources/image/mail_rightbg.gif"><img
					src="../resources/image/nav-right-bg.gif" width="16" height="29" /></td>
			</tr>
			<tr>
				<td valign="bottom" background="../resources/image/mail_leftbg.gif"><img
					src="../resources/image/buttom_left2.gif" width="17" height="17" /></td>
				<td background="../resources/image/buttom_bgs.gif"><img
					src="../resources/image/buttom_bgs.gif" width="17" height="17"></td>
				<td valign="bottom" background="../resources/image/mail_rightbg.gif"><img
					src="../resources/image/buttom_right2.gif" width="16" height="17" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>