<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		<title>管理中心</title>
		<link href="<%=basePath %>/css/skin.css" rel="stylesheet" type="text/css">
	</head>
	
  	<body>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  	<tr>
		    <td width="17" valign="top" background="<%=basePath %>/images/mail_leftbg.gif"><img src="<%=basePath %>/images/left-top-right.gif" width="17" height="29" /></td>
		    <td valign="middle" background="<%=basePath %>/images/content-bg.gif"><span class="right_title">基本设置</span></td>
		    <td width="16" valign="top" background="<%=basePath %>/images/mail_rightbg.gif"><img src="<%=basePath %>/images/nav-right-bg.gif" width="16" height="29" /></td>
	  	</tr>
  
  		<tr>
  			<td valign="middle" background="<%=basePath %>/images/mail_leftbg.gif">&nbsp;</td>
  			<td bgcolor="#F7F8F9">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" class=table_cls style="margin-top: 5px;">
				<tr>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">系统名称:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">数据库名称:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		        </tr>
				<tr>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">公司名称:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">公司成立时间:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		        </tr>
				<tr>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">系统类型:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		            <td width="6%" height="26" class="STYLE1"><div align="right" class="STYLE2 STYLE1">系统名称:</div></td>
		            <td width="15%" height="18" class="STYLE1"><input type="text"></td>
		        </tr>
				<tr>
		            <td colspan=4 class="STYLE1" align=center>
						<input type="button" value="确定" class=btn_cls/> &nbsp;&nbsp;
						<input type="button" value="重置" class=btn_cls/>
					</td>
		        </tr>
			</table>
  			</td>
  			<td valign="middle" background="<%=basePath %>/images/mail_rightbg.gif">&nbsp;</td>
  		</tr>
		<tr>
		    <td valign="bottom" background="<%=basePath %>/images/mail_leftbg.gif"><img src="<%=basePath %>/images/buttom_left2.gif" width="17" height="17" /></td>
		    <td background="<%=basePath %>/images/buttom_bgs.gif"><img src="<%=basePath %>/images/buttom_bgs.gif" width="17" height="17"></td>
		    <td valign="bottom" background="<%=basePath %>/images/mail_rightbg.gif"><img src="<%=basePath %>/images/buttom_right2.gif" width="16" height="17" /></td>
		</tr>
	</table>
  	</body>
</html>