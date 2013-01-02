<%@ page language="java" contentType="text/html;charset=utf-8"   
		import="java.util.*,java.sql.*,com.leec.lmodules_article.util.Configuration.*,
				com.leec.lmodules_article.service.*,com.leec.lmodules_article.model.DTO.*" 
		pageEncoding="utf-8"%>
<form id="pagerForm" method="post" action="demo_page1.jsp">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this,navTabAjaxDone);" action="demo_page1.jsp" method="post">
	<div class="searchBar">

		<table class="searchContent">
			<tr>
				<td>
					管理员：<input type="text" name="keyword" />
				</td>
				<td>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="Servlet.do?method=deleteuser&uid={sid_user}" target="ajaxTodo" title="确定要删除吗?" onsubmit="return validateCallback(this, dialogAjaxDone)"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page5.jsp?uid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="120">用户id</th>
				<th>用户名</th>
		</thead>
		<tbody>	
		<% List<Lmo_article_adminDTO> ls = Service.getadminlist();
			for(int i=0;i<ls.size();i++){
				Lmo_article_adminDTO dto=ls.get(i);									
			%>	
				<tr target="sid_user" rel="<%=dto.getAdmin_id()%>">
				<td>管理员</td>
				<td><%=dto.getAdmin_id()%></td>
				<td><%=dto.getAdmin_username()%></td>			
			</tr>
			<% }%>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
