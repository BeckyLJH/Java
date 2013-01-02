<%@ page language="java" import="com.leec.lmodules_article.service.*" contentType="text/html;charset=utf-8"  pageEncoding="utf-8"%>
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
	width:65px;
}
.submit { 
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
	width:300px; 
}
.file-box{ 
	position:relative;
	width:525px
}
-->
</style>

<script src="scripts/xheditor/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="scripts/xheditor/xheditor-zh-cn.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//初始化xhEditor编辑器插件
			$('#xh_editor').xheditor({
				tools:'full',
				skin:'default',
				upMultiple:false,
				upImgUrl: "/lmodules_article/Servlet.do",
				upImgExt: "jpg,jpeg,gif,bmp,png",
				upFlashUrl: "/lmodules_article/Servlet.do",
				upFlashExt: "swf",
				upMediaUrl: "/lmodules_article/Servlet.do",
				upMediaExt: "avi",
				upLinkUrl: "/lmodules_article/Servlet.do",
				upLinkExt: "",
				onUpload:insertUpload			
			});
			//xbhEditor编辑器图片上传回调函数
			function insertUpload(msg) {//-----这里传进来的msg=webapps\demo_xheditor_withUpload\ARTICLE_IMG\20111027\name.jpg----为什么？？？？？？？？？？？
				var _msg = msg.toString();
				//public String.substring(int beginIndex)返回一个新的字符串，它是此字符串的一个子字符串。该子字符串始于指定索引处的字符，一直到此字符串末尾。
				//例如："unhappy".substring(2) returns "happy"----------"Harbison".substring(3) returns "bison"--------
				//lastIndexOf返回的是“最后的”匹配的字符在字符串中的第几位的右边，然后返回第几，+1就表示它自己当时所在位置，如果该元素不存在于字符串中那么返回-1
				//例如：alert('0a234a6'.lastIndexOf('a')) // 打印的是5
				//var test = "abcdefg": --------var  num1 = test.lastIndexOf("de"); //返回3，表示"de"在第三位的右边
				//------------------------------var  num2 = test.lastIndexOf("ed"); //返回-1,因为ed不存在
				//所以这里返回的是最后一个"/"所在位置+1，刚好是"n"所在位置，再一个".substring()",结果返回-------name.jpg------------
				var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
				//调用下面的Substring()函数----结果为-----\20111027\name.jpg
				var _picture_path = Substring(_msg);
				var _str = "<input type='checkbox' name='_pictures' value='"+_picture_name+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";				
				//var picture_name=$("#picture_name").append(_picture_name).toString();
				//var _img = "<input type='hidden' name='img' value='"+_picture_name+"' checked='checked' onclick='return false'/>";
				//$("#img").append(_img);
				$("#xh_editor").append(_msg);
				$("#uploadList").append(_str);				
			}
			//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
			//s = msg = webapps\demo_xheditor_withUpload\ARTICLE_IMG\20111027\name.jpg 
			//s.lastIndexOf("/") = "7"所在位置
			//s.substring(0,s.lastIndexOf("/")) = webapps\demo_xheditor_withUpload\ARTICLE_IMG\20111027
			//s.substring(0,s.lastIndexOf("/")).lastIndexOf("/") = "G"所在位置
			//s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length) = msg.substring("G"所在位置，结尾)=\20111027\name.jpg
			function Substring(s){
				return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);//return   \20111027\name.jpg
			}
		});
	</script>
	
	<script type="text/javascript"> 
		function checkLength(which) { 
			var maxChars = 80; 
			if (which.value.length > maxChars)
			which.value = which.value.substring(0,maxChars); 
			var curr = maxChars - which.value.length; 
			document.getElementById("chLeft").innerHTML = curr.toString(); 
		} 
	</script>
	
	<script type="text/javascript"> 
		function checkLength2(which) { 
			var maxChars = 255; 
			if (which.value.length > maxChars)
			which.value = which.value.substring(0,maxChars); 
			var curr = maxChars - which.value.length; 
			document.getElementById("chLeft2").innerHTML = curr.toString(); 
		} 
	</script>
	

	
</HEAD>
<%	request.setCharacterEncoding("UTF-8");
		String article_type_id=(String)request.getParameter("_type_father");
		//System.out.println("article_type_id2222222:"+article_type_id);
		
		%>

<BODY >
	<form action=Servlet.do?method=shangchuan&_article_type_id=<%=article_type_id %> method=post enctype="multipart/form-data">
		<table  border=0 width="90%" align=center cellspacing=1 cellpadding=0 bgcolor=#D5E2E9>
			<tr height=40><td width="130" ></td><td></td></tr>
			
			<tr bgcolor=#ffffff>
				<td width="130" height="30"><font color=#306AA2 size=3><b>&nbsp;在线投稿</b></font></td>
				<td></td>
			</tr>
			
	<!-- 	<tr class="STYLE4">
				<td width="130" align=right height="30">文章分类：</td>
				<td><select name=type style=width:250>
						//<%=Service.getArticleTypeListByTT()%>
					</select></td>
			</tr>    -->
	
			<tr class="STYLE4">
				<td width="130" align=right height="30"><font color="red"><sup>*</sup></font>标题：</td>
				<td><input type=text name=title style=width:400 onkeyup="checkLength(this);"> 还可以输入<span id="chLeft">80</span>个字符</td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">关键词：</td>
				<td><input type=text name=keys style=width:300>多关键词之间用空格或者","隔开</td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">作者：</td>
				<td><input type=text name=zuozhe style=width:300></td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">来源：</td><td>
					<input type=text name=from1 style=width:400>
						<select name=from2>
							<option value="">请选择</option>
							<option>网页</option>
							<option>报纸</option>
							<option>电视</option>
							<option>其他</option>
						</select>
				</td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">摘要：</td>
				<td><textarea name=intro cols="40" rows="4"  style=width:550 onkeyup="checkLength2(this);"></textarea>
				</td>
			</tr>
			
			<tr class="STYLE4">
				<td></td><td>还可以输入<span id="chLeft2">255</span>个字符</td>
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">内容：</td>
				<td><table><tr><td>
						<textarea wrap=soft name=content rows=25 cols=85 style="overflow:auto" id="xh_editor"></textarea>
						<div id="uploadList"></div>
					</td></tr></table>
				</td>	
			</tr>
			
			<tr class="STYLE4">
				<td width="130" align=right height="30">缩略图：</td>
				<td><div class=file-box>
					<input type=checkbox name=is_tuwen value=1><font color=red size=2>&nbsp;是否图文</font>
					<input type=text name=picture style=width:290 id=textfield class='txt'>										
					<input type=button class=button  name=button value="上传图片" >
					<input type=file name=fileField class=file id=fileField name=file size=28 onchange="document.getElementById('textfield').value=this.value"/>
					
				</div></td>
			</tr>
		
			<tr class="STYLE4">
				<td width="130" align=right height="30">转向链接：</td>
				<td><input type=checkbox name=checkbox value=1><font color=red size=2>&nbsp;转向链接</font>
					<input type=text name=link style=width:200><SPAN style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white"></SPAN>	
				</td>
			</tr>
				
			<tr class="STYLE4">
				<td width="130" align=right height="30"></td>
				<td><input type=submit class=submit name=send style=width:50 value="提交">
				</td>
			</tr>
		</table>
	
	</form>
</BODY></HTML>
