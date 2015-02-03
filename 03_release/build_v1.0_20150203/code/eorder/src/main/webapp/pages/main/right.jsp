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
        <script src="../resources/js/prototype.lite.js" type="text/javascript"></script>
        <script src="../resources/js/moo.fx.js" type="text/javascript"></script>
        <script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>        
        <link href="../resources/css/skin.css" rel="stylesheet" type="text/css">
        <link href="../resources/css/basic.css" rel="stylesheet" type="text/css">
    </head>
    
      <body>
      
<table id="right" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="../resources/image/mail_leftbg.gif"><img src="../resources/image/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="../resources/image/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
        <tr>
          <td height="31"><div class="titlebt">Welcome</div></td>
        </tr>
      </table></td>
    <td width="16" valign="top" background="../resources/image/mail_rightbg.gif"><img src="../resources/image/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="../resources/image/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="2" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
          <td valign="top">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><span class="left_bt">Thanks for using TRADE INFO MANAGEMENT SYSTEM</span><br>
            <br />
            <span class="left_txt_subtitle"><img src="../resources/image/ts.gif" width="16" height="16"> Tips:<br />
            <span class="left_txt">This system is develop by www.isstudy.com, which is a professional system, used to build business information portal website. If you have any questions, please contact <span class="left_ts">online customer service</span> at left bottom coner for consultation.</span><br />
            <span class="left_txt">Ideal integration of the seven columns, one-stop-use, powerful, easy to operate, only typing, surfing the net, will administer the site.</span><br />
            <span class="left_txt">This aplication is the preferred solution for you to create a regional business information portal.<br />
            </span></td>
          <td width="1%">&nbsp;</td>
          <td width="47%" valign="top"><table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
              <tr>
                <td width="7%" height="27" background="../resources/image/news-title-bg.gif"><img src="../resources/image/news-title-bg.gif" width="2" height="27"></td>
                <td width="93%" background="../resources/image/news-title-bg.gif" class="left_bt2">News</td>
              </tr>
              <tr>
                <td height="102" valign="top">&nbsp;</td>
                <td height="102" valign="top"></td>
              </tr>
              <tr>
                <td height="5" colspan="2">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><!--JavaScript--> 
            <SCRIPT language=javascript>
function secBoard(n)
{
for(i=0;i<secTable.cells.length;i++)
secTable.cells[i].className="sec1";
secTable.cells[n].className="sec2";
for(i=0;i<mainTable.tBodies.length;i++)
mainTable.tBodies[i].style.display="none";
mainTable.tBodies[n].style.display="block";
}
          </SCRIPT> 
            <!--HTML-->
            
            <TABLE width=72% border=0 cellPadding=0 cellSpacing=0 id=secTable>
              <TBODY>
                <TR align=middle height=20>
                  <TD align="center" class=sec2 onclick=secBoard(0)>Authentication Info</TD>
                  <TD align="center" class=sec1 onclick=secBoard(1)>Statistic Info</TD>
                  <TD align="center" class=sec1 onclick=secBoard(2)>System Parameter</TD>
                  <TD align="center" class=sec1 onclick=secBoard(3)>Copyright</TD>
                </TR>
              </TBODY>
            </TABLE>
            <TABLE class=main_tab id=mainTable cellSpacing=0
cellPadding=0 width=100% border=0>
              <!--TBODY-->
              <TBODY style="DISPLAY: block">
                <TR>
                  <TD vAlign=top align=middle><TABLE width=98% height="133" border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD width="4%" height="28" background="../resources/image/news-title-bg.gif"></TD>
                          <TD height="25" colspan="2" background="../resources/image/news-title-bg.gif" class="left_txt">Dear administrator:<font color="#FFFFFF" class="left_ts"><b></b></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="42%" height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified category letter:</span> <span class="left_ts"> </span></TD>
                          <TD width="54%" height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified advertising poster:</span> <span class="left_ts"> </span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified category letter:</span> <span class="left_ts"> </span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified advertising poster:</span> <span class="left_ts"> </span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified category letter:</span> <span class="left_ts"> </span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified advertising poster: </span> <span class="left_ts"> </span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified category letter: </span> <span class="left_ts"> </span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">You have not verified advertising poster:</span> <span class="left_ts"> </span></TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                      </TBODY>
                    </TABLE></TD>
                </TR>
              </TBODY>
              <!--cells-->
              <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle><TABLE width=98% height="133" border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD width="4%" height="28" background="../resources/image/news-title-bg.gif"></TD>
                          <TD colspan="2" background="../resources/image/news-title-bg.gif" class="left_txt">Existing members:</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="42%" height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                          <TD width="54%" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Existing category info:</span></TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                      </TBODY>
                    </TABLE></TD>
                </TR>
              </TBODY>
              <!--tBodies-->
              <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle><TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD width="4%" height="25" background="../resources/image/news-title-bg.gif"></TD>
                          <TD height="25" colspan="2" background="../resources/image/news-title-bg.gif" class="left_txt"><span class="TableRow2">Sever IP: System version:</span></TD>
                        </TR>
                        <TR>
                          <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD width="42%" height="25" bgcolor="#FAFBFC"><span class="left_txt">Sever type:</span></TD>
                          <TD width="54%" height="25" bgcolor="#FAFBFC"><span class="left_txt">Script interpreter engine:</span></TD>
                        </TR>
                        <TR>
                          <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" colspan="2" bgcolor="#FAFBFC"><span class="left_txt">Site physical path:</span></TD>
                        </TR>
                        <TR>
                          <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">FSO text read-write:</span><span class="TableRow2"><font color="#FF0066"><b><img src="../resources/image/X.gif" width="12" height="13"></b></font><img src="../resources/image/g.gif" width="12" height="12"></span></TD>
                          <TD height="25" bgcolor="#FAFBFC"><span class="left_txt">Database:</span><span class="left_ts"><img src="../resources/image/X.gif" width="12" height="13"><b style="color:blue"> MS SQL
                            
                            ACCESS </b></span></TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                      </TBODY>
                    </TABLE></TD>
                </TR>
              </TBODY>
              <!--display-->
              <TBODY style="DISPLAY: none">
                <TR>
                  <TD vAlign=top align=middle><TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                      <TBODY>
                        <TR>
                          <TD colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                        <TR>
                          <TD width="4%" background="../resources/image/news-title-bg.gif"></TD>
                          <TD width="91%" background="../resources/image/news-title-bg.gif" class="left_ts"><> program instructions:</TD>
                          <TD width="5%" background="../resources/image/news-title-bg.gif" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">1. </span>Transform by www.isstudy.com(QQ:996694055) </TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">2. </span>Just for using.</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">3. </span>Protect aplication's copyright.</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD bgcolor="#FAFBFC">&nbsp;</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">4. </span>Contact www.isstudy.com for technical support</TD>
                          <TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
                        </TR>
                        <TR>
                          <TD height="5" colspan="3"></TD>
                        </TR>
                      </TBODY>
                    </TABLE></TD>
                </TR>
              </TBODY>
            </TABLE></td>
          <td>&nbsp;</td>
          <td valign="top"><table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
              <tr>
                <td width="7%" height="27" background="../resources/image/news-title-bg.gif"><img src="../resources/image/news-title-bg.gif" width="2" height="27"></td>
                <td width="93%" background="../resources/image/news-title-bg.gif" class="left_bt2">Procedure Instruction</td>
              </tr>
              <tr>
                <td height="102" valign="top">&nbsp;</td>
                <td height="102" valign="top"><label></label>
                  <label>
                    <textarea name="textarea" cols="48" rows="8" class="left_txt">Preferred solution for professional regional business portal.
Preferred solution for professional regional business portal.
Preferred solution for professional regional business portal.
</textarea>
                  </label></td>
              </tr>
              <tr>
                <td height="5" colspan="2">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="50%" class="left_txt"><img src="../resources/image/icon-mail2.gif" width="16" height="11"> Customer service mailbox: 996694055@qq.com<br>
            <img src="../resources/image/icon-phone.gif" width="17" height="14"> Official website: http://www.isstudy.com</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table></td>
    <td background="../resources/image/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="../resources/image/mail_leftbg.gif"><img src="../resources/image/buttom_left2.gif" width="17" height="17" /></td>
    <td background="../resources/image/buttom_bgs.gif"><img src="../resources/image/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="../resources/image/mail_rightbg.gif"><img src="../resources/image/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
      </body>
</html>