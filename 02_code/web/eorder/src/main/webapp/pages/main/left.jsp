<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="../resources/js/prototype.lite.js" type="text/javascript"></script>
    <script src="../resources/js/moo.fx.js" type="text/javascript"></script>
    <script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>
    <link href="../resources/css/basic.css" rel="stylesheet" type="text/css">
    <link href="../resources/css/navigation.css" rel="stylesheet" type="text/css">
  </head>

  <body>
	<table id="left" width="100%" height="280" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="182" valign="top">
	      <div id="container">
	        <s:iterator value="menulist" id="banner">
	        <h1 class="type">
	          <a href="javascript:void(0)"><s:property value="#banner.displayName" /></a>
            </h1>
            <div class="content">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="../resources/image/menu_topline.gif" width="182" height="5" /></td>
				</tr>
			  </table>
			  <ul class="MM">
			  <s:iterator value="#banner.list" id="subBanner">
			    <li><a href='<%=basePath%><s:property value="#subBanner.link" />' target="main"><s:property value="#subBanner.displayName" /></a></li>
		      </s:iterator>
			  </ul>
			</div>
			</s:iterator>
		  </div>
		  <script type="text/javascript">
		    var contents = document.getElementsByClassName('content');
		    var toggles  = document.getElementsByClassName('type');
		    var myAccordion = new fx.Accordion(toggles, contents, {opacity : true, duration : 400});
			myAccordion.showThisHideOpen(contents[0]);
		  </script>
		</td>
	  </tr>
	</table>
  </body>
</html>