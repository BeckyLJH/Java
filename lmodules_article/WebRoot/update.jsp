<%@ page language="java" contentType="text/html;charset=utf-8" 
	import="javax.servlet.http.*,java.sql.*,com.leec.lmodules_article.service.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD><TITLE>修改页面</TITLE>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FF99FF;
}
a:hover {
	text-decoration: none;
}
a:active {
}
-->
</style>
</HEAD>


<body><center> 
<form name=update  action="Servlet.do?method=update2" method=POST >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 分类列表</span></td>
              </tr>
            </table></td> 
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
	<td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
	<tr>		
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">分类ID</span></div></td>
		<td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">分类名称</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">分类排序</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">分类说明</span></div></td>
        <td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">父分类ID</span></div></td>
		<td width="9%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">&nbsp;</span></div></td>
      </tr>
 <%	
	request.setCharacterEncoding("UTF-8");
	String type_id=(String)request.getParameter("_type_id");	
	/*request.setCharacterEncoding("UTF-8");			
				String msg="";
				String m=(String)request.getParameter("msg");
				if(m!=null){
					if(m.equals("a")){
						msg="用户名为空，请重新输入！";
					}
					if(m.equals("b")){
						msg="用户密码为空，请重新输入！";
					}
					if(m.equals("c")){
						msg="用户名已存在，请重新输入！";
					}
				}
					out.print(msg);
	*/								
					
							
				out.print("<tr><td height=20 bgcolor=#FFFFFF class=STYLE6>"+
					"<input type=text name=type_id readonly value="+type_id+">"
					+"</td><td height=20 bgcolor=#FFFFFF class=STYLE6>" + 
					"<input type=text name=type_name value="+Service.dogetname(type_id)+">" 
					+" </td><td height=20 bgcolor=#FFFFFF class=STYLE6>"+ 
					"<input type=text name=type_order value="+Service.dogetorder(type_id)+">"
					+"</td><td height=20 bgcolor=#FFFFFF class=STYLE6>"+
					"<input type=text name=type_desc value="+Service.dogetdesc(type_id)+">"
					+"</td><td height=20 bgcolor=#FFFFFF class=STYLE6>"+
					"<input type=text name=type_father value="+Service.dogetfather(type_id)+">"
					+"</td><td height=20 bgcolor=#FFFFFF class=STYLE6>"+
					"<input type=submit value=确认></td></tr>");   
%>
	</table></td>
</tr>
</table></form>
</center></body></html>
