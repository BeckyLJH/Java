<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE4 {
	color: #03515d;
	font-size: 14px;
	face:华文行楷；
}
-->
</style>

</HEAD>
<BODY bgcolor=#DBF1F2>
  <%
	request.setCharacterEncoding("UTF-8");
	String msg="";
	String type_father=(String)request.getParameter("_type_father");
		//System.out.println("-------77777---------"+type_father);
	if(type_father==null||type_father.equals("null")){
		type_father="0";//System.out.println("-------88888---------"+type_father);
	}//System.out.println("-------99999---------"+type_father);
	
		if(request.getParameter("msg")!=null){
			if(request.getParameter("msg").equals("a")){
				msg="分类名称为空,请输入";
			}
			if(request.getParameter("msg").equals("b")){
				msg="分类排序为空,请输入";
			}
			if(request.getParameter("msg").equals("c")){
				msg="分类说明为空,请输入";
			}			
			if(request.getParameter("msg").equals("0")){
				msg="数据连接异常";
			}
			if(request.getParameter("msg").equals("1")){
				msg="分类名称或分类排序已存在，请重新输入";
			}
		  }
  %>         
				<H1><center><font color="#aaggzz"><%=msg%><center></H1>
<form name=add  action="Servlet.do?method=add&type_father=<%=type_father %>" method=POST >
	<table align=center >
	    <TR height=100>
        	<TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
        </TR>
		<tr><td class=STYLE4>分类名称：</td><td ><input type=text name=type_name ></td></tr>
		<tr><td class=STYLE4>分类排序：</td><td ><input type=text name=type_order ></td></tr>
		<tr><td class=STYLE4>分类说明：</td><td ><textarea cols="40" rows="6" name=type_desc ></textarea></td></tr>
		<tr><td><INPUT TYPE=submit class=input_s value=添加></td></tr>
</table></form>
                
</BODY></HTML>
