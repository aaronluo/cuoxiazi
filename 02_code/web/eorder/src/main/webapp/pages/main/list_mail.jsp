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
		<script src="<%= basePath %>/js/base.js" type="text/javascript"></script>
		<link href="<%=basePath %>/css/skin.css" rel="stylesheet" type="text/css">
	</head>
	
  	<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
  	<tr>
    	<td width="17" valign="top" background="<%=basePath %>/images/mail_leftbg.gif"><img src="<%=basePath %>/images/left-top-right.gif" width="17" height="29" /></td>
    	<td valign="middle" background="<%=basePath %>/images/content-bg.gif"><span class="right_title">邮件列表</span></td>
    	<td width="16" valign="top" background="<%=basePath %>/images/mail_rightbg.gif"><img src="<%=basePath %>/images/nav-right-bg.gif" width="16" height="29" /></td>
  	</tr>
  
  	<tr>
  		<td valign="middle" background="<%=basePath %>/images/mail_leftbg.gif">&nbsp;</td>
  		<td bgcolor="#F7F8F9">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" onmouseover="changeto()"  onmouseout="changeback()">
		<tr>
            <td width="6%" height="26" class="STYLE1"><div align="center" class="STYLE2 STYLE1">选择</div></td>
            <td width="8%" height="18" background="#c5bfbe" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="24%" height="18" class="STYLE1"><div align="center" class="STYLE2 STYLE1">运行机器ip地址</div></td>
            <td width="10%" height="18" class="STYLE1"><div align="center" class="STYLE2 STYLE1">机器名</div></td>
            <td width="14%" height="18" class="STYLE1"><div align="center" class="STYLE2 STYLE1">节点类型</div></td>
            <td width="24%" height="18" class="STYLE1"><div align="center" class="STYLE2">服务器进程配置</div></td>
            <td width="7%" height="18"  class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
            <td width="7%" height="18"  class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
        </tr>
		<tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 ">A0012</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">192.168.0.124</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">wtz_fh</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">DBserver</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#">服务器进程配置</a></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=basePath %>/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="#">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="<%=basePath %>/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="#">删除</a><span class="STYLE1">]</span></div></td>
		</tr>
		<tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 ">A0012</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">192.168.0.124</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">wtz_fh</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">DBserver</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#">服务器进程配置</a></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=basePath %>/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="#">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="<%=basePath %>/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="#">删除</a><span class="STYLE1">]</span></div></td>
        </tr>
		<tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 ">A0012</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">192.168.0.124</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">wtz_fh</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 ">DBserver</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#">服务器进程配置</a></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=basePath %>/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="#">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="<%=basePath %>/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="#">删除</a><span class="STYLE1">]</span></div></td>
        </tr>
		<tr>
			<td colspan=3 height="20" valign="bottom" nowrap="nowrap"><span class="STYLE2">共3条记录，当前第1/10页，每页10条纪录</span></td>
			<td></td>
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