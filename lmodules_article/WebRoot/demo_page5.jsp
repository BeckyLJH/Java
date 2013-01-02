<%@ page language="java" contentType="text/html;charset=utf-8"   
		import="java.util.*,java.sql.*,com.leec.lmodules_article.util.Configuration.*,
				com.leec.lmodules_article.service.*,com.leec.lmodules_article.model.DTO.*" 
		pageEncoding="utf-8"%>
		<%  request.setCharacterEncoding("UTF-8");
			String admin_id = (String)request.getParameter("uid");
		 %>
<div class="pageContent">
	<form method="post" action="Servlet.do?method=updateuser&uid=<%=admin_id%>" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名：</label>
				<input type="text" name="admin_username" size="30" />
			</p>
			<p>
				<label>密码：</label>
				<input type="text" name="admin_pass"size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>


