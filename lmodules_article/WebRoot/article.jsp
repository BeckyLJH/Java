<%@ page language="java" import="java.util.*,com.leec.lmodules_article.service.*,com.leec.lmodules_article.model.DTO.*" contentType="text/html;charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<style type="text/css">
<!--
body {
	background-color: #ffffff;
	margin-left: 200px;
	margin-right: 200px;
}
.STYLE4 {
	color: #03515d;
	font-size: 14px;
	face:华文行楷；
}
.button { 
	padding: 0 5px;  
	height: 24px; 
	border: none; 
	background: #C5C5C5; 
	color: #333; 
	line-height: 20px; 
	letter-spacing: 1px; 
	cursor: pointer; 
}
.file{ 
	position:absolute; 
	top:0; right:80px; 
	height:24px; 
	filter:alpha(opacity:0);
	opacity: 0;
	width:260px; 
}
.file-box{ 
	position:relative;
	width:450px
}
-->
</style>

</HEAD>
<BODY >

	<%
		request.setCharacterEncoding("UTF-8");
		String article_id=(String)request.getParameter("_article_id");
		//System.out.println("article_id:"+article_id);
		List<Lmo_articleDTO> ls = Service.getarticle(article_id);
		Lmo_articleDTO dto=ls.get(0);
		//String path = request.getRealPath("/");
		//System.out.println("777777777:"+path);
		String img=dto.getArticle_picture();
		int hits=dto.getArticle_hits();
		hits=hits+1;
		Service.dohits(article_id,hits);

	%>

		<table  border=0 width="90%" align=center cellspacing=1 cellpadding=0 bgcolor=#D5E2E9>
			<tr height=40><td width="130" ></td><td></td></tr>
			
			<tr bgcolor=#ffffff>
				<td width="130" height="30"><font color=#306AA2 size=3><b>&nbsp;文章信息</b></font></td>
				<td></td>
			</tr>				
			
			<tr class="STYLE4">
				<td width="130" align=right height="30"><font color="red"><sup>*</sup></font>标题：</td>
				<td><%=dto.getArticle_title()%></td>
			</tr>
			
			
			<tr class="STYLE4" bgcolor=#ffffff>
				<td width="130" align=right height="30">作者：</td>
				<td><a href=http://www.google.com.hk/search?sourceid=chrome&ie=UTF-8&q=<%=dto.getArticle_zuozhe()%>><%=dto.getArticle_zuozhe()%></a></td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">最近更新时间：</td>
				<td><%=dto.getArticle_fabu_time()%></td>
			</tr>
			
			<tr class="STYLE4" bgcolor=#ffffff>
				<td width="130" align=right height="30">文章来源：</td>
				<td><%=dto.getArticle_from()%></td>
			</tr>
			
			<tr class="STYLE4" bgcolor=#ffffff>
				<td width="130" align=right height="30">浏览次数：</td>
				<td><%=hits%></td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">关键词：</td>
				<td><%=dto.getArticle_keys()%></td>
			</tr>
			
			<tr class="STYLE4" bgcolor=#ffffff>
				<td width="130" align=right height="30">摘要：</td>
				<td><%=dto.getArticle_intro()%></td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">内容：</td>
					<td><%=dto.getArticle_content()%></td>	
			</tr>
			
			<%if(dto.getArticle_is_tuwen()==1){ %>
			<tr class="STYLE4" bgcolor=#ffffff>
				<td width="130" align=right height="30">缩略图：</td>
				<td><img src="<%=img%>" width=100 height=100/>
				<%//System.out.println("55555555555555:"+dto.getArticle_is_tuwen()); %>
				<%//System.out.println("55555555555555:"+img); %>
			</td>
			</tr>
			<% }%>
				<%//System.out.println("6666666666666:"+dto.getArticle_is_tuwen()); %>
		</table>
	
</BODY></HTML>
