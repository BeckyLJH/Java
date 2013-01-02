<%@ page language="java" contentType="text/html;charset=utf-8"   
		import="java.sql.*,com.leec.lmodules_article.util.Configuration.*,com.leec.lmodules_article.service.*" 
		pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分类列表</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>

<script>
var  highlightcolor='#f0f0f0';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
<script language="javascript"> 
// --列头全选框被单击--- 
function ChkAllClick(sonName, cbAllId){ 
var arrSon = document.getElementsByName(sonName); 
var cbAll = document.getElementById(cbAllId); 
var tempState=cbAll.checked; 
for(i=0;i<arrSon.length;i++) { 
if(arrSon[i].checked!=tempState) 
arrSon[i].click(); 
} 
} 

// --子项复选框被单击--- 
function ChkSonClick(sonName, cbAllId) { 
var arrSon = document.getElementsByName(sonName); 
var cbAll = document.getElementById(cbAllId); 
for(var i=0; i<arrSon.length; i++) { 
if(!arrSon[i].checked) { 
cbAll.checked = false; 
return; 
} 
} 
cbAll.checked = true; 
} 

// --反选被单击--- 
function ChkOppClick(sonName){ 
var arrSon = document.getElementsByName(sonName); 
for(i=0;i<arrSon.length;i++) { 
arrSon[i].click(); 
} 
} 



function $(objId){
return document.getElementById(objId);
}

function del_tbl(tblN,ckN){
		
		var ck = document.getElementsByName(ckN);
		var tab = $(tblN);
		var count=0;
		for(var i=0;i<ck.length;i++){		
			if(ck[i].checked){
			 count=count+1;
			}
		}
		if(count==0){
			var str="请先选择要删除的数据!";		
			showMsg(str);
		}else{
			if(window.confirm('是否确认删除?')){
				var rowIndex;	
				for(var i=0;i<ck.length;i++){			
					if(ck[i].checked){
					 rowIndex = ck[i].parentNode.parentNode.parentNode.sectionRowIndex;
					 
					 tab.deleteRow(rowIndex);
					 i = -1;
					}
				}
			}
			var str="删除成功!";		
			showMsg(str);
		}
}

function update_tbl(tblN,ckN){
	var ck = document.getElementsByName(ckN);    
	var count=0;
	for(var i=0;i<ck.length;i++){		
        if(ck[i].checked){
		 count=count+1;
		}
	}
	if(count>1){
		var str="不能同时修改多个数据!";		
		showMsg(str);
	}else if(count==0){		
		var str="请先选择要修改的数据!";		
		showMsg(str);
	}else{
		var str="修改成功!";		
		showMsg(str);
	}
}
function showMsg(msg){
		var str="<img src=\"datagrid2_images/ts.gif\" width=\"27\" height=\"20\" /><font color='red'>"+msg+"</font>";
		document.getElementById("Tishi").innerHTML=str;
		restartTimer();//定时清除提示
}
</script> 
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0" >  
  <tr>
    <td height="25" background="datagrid2_images/headbody.jpg">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td width="5%"><div align="center"><img src="datagrid2_images/tb.gif" width="16" height="16" /></div></td>
					<td width="95%" class="STYLE1"><span class="STYLE3">您当前的位置</span>：[文章分类/标签管理]-[分类管理]</td>
					<!-- #########################################################################################-->
				  </tr>
				</table>
			</td>
            <td width="54%">
			 
			</td>
          </tr>
          </table>
		</td>
      </tr>
    </table>
	</td>
  </tr>
  
  <tr><td height="1"></td></tr>
       
  <%	request.setCharacterEncoding("UTF-8");
		String father2=(String)request.getParameter("_type_id");
		//System.out.println("father2:"+father2);
		%>
		
  <tr>
    <td height="40" background="datagrid2_images/search.jpg">   
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
		<td width="70%" class="STYLE4"> &nbsp;&nbsp;一级分类列表</td>	
		<td width="30%" class="STYLE4">
			<form name=list action="Servlet.do?method=select" method=post>
				按分类检索：&nbsp;&nbsp;			
				<select name=news>
					<option  value="0">顶级分类</option>	
					<%=Service.getArticleTypeListByTT()%>							 
				</select> 			
				<input type=submit value="检索" class="STYLE4"></form>
		</td>			
	   </tr>  
     </table>
   </td>	
  </tr>
  
<%	request.setCharacterEncoding("UTF-8");
		String news=(String)request.getParameter("news");
		//System.out.println("news:"+news);
		
		%>
  
  
  
  <tr>
    <td height="25" background="datagrid2_images/headbody.jpg">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td>
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
           <td width="54%" >
			 <table border="0" align="left" cellpadding="0" cellspacing="0">
              <tr>
                <td width="100"><table width="90%" border="0" cellpadding="0" cellspacing="0" >
                  <tr>
					<!--先链接数据库，查找lmo_article_type中的type_father,如果为0，不显示此链接--><!-- -->
					<!--如果type_father不为0，就链接到type_father所显示type_id所在列表-->
                    <td class="STYLE1"><div align="center"><img src="datagrid2_images/fh.jpg" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><%=Service.getfatheridlist(father2)%>
                    
                    </div></td>
                  </tr>
                </table></td>
              
                <td width="150"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
					<!--点击之后，弹出添加页面，输入分类名称和说明，然后把type_id、type_name、type_desc、type_father、type_order（在type_father相同的一类中自增） 
						所添加的子类的type_father与现在页面列表显示的分类type_father是一样的
					-->
                    <td class="STYLE1"><div align="center"><img src="datagrid2_images/22.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="add.jsp?_type_father=<%=father2%>">在此分类下添加子类</a></div></td>
                  </tr>
                </table></td>
                <td width="60"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
					<!--点击后，把选中的所有行都删除，删除高级分类必须把相关联文章、文件一起删除；
						删除最低级分类文章时，也要提醒存在相关联文件-->
                    <td class="STYLE1"><div align="center"><img src="datagrid2_images/11.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><!--<a href="javascript:del_tbl('list','chkSon')">-->删除</div></td>
                  </tr>
                </table></td>
				<td class="STYLE3"><div id="Tishi" align="center">  </div> </td>
				<td class="STYLE3"><div id="second"></div></td>
              </tr>
			
            </table>
			</td>
            
          </tr>
          </table>
		</td>
      </tr>
    </table>
	</td>
  </tr>
 
  
 
 <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" >
      
	  
	  
	  
	  <tr>
        
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#ececec" onmouseover="changeto()"  onmouseout="changeback()" id="list">
          <tr>
            <td width="5%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF" >
			<div align="center">              
			  <input type="checkbox" name="chkAll" id="chkAll" onClick="ChkAllClick('chkSon','chkAll')" />
            </div>
			</td>
            <td width="10%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">分类ID</span></div></td>
            <td width="20%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">分类名称</span></div></td>
            <td width="10%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">子分类</span></div></td>
            <td width="10%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">相关文章</span></div></td>
            <td width="10%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">分类排序</span></div></td>
			<td width="35%" height="22" background="datagrid2_images/bg.jpg" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">操作</span></div></td>
		  </tr>
	
		<%  
			
			if(father2==null){
				//通过type_father来查询type_id
				out.println(Service.getchildlist("0"));
				//System.out.println("查看_type_id---"+father2);
				}
			else{
				out.println(Service.getchildlist(father2));
				//System.out.println("查看_type_id---"+father2);
				} 
			%>
		
        </table>
		</td>
       
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="27" background="datagrid2_images/under.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
       
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
			<td class="STYLE4">&nbsp;&nbsp;<INPUT name="chkOpposite" id="chkOpposite" title="反选" onClick="ChkOppClick('chkSon')" type="checkbox" />反选</td>
            <td class="STYLE4">&nbsp;&nbsp;共有 120 条记录，当前第 1/10 页</td>
            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40"><img src="datagrid2_images/first.gif" width="37" height="15" /></td>
                  <td width="45"><img src="datagrid2_images/back.gif" width="43" height="15" /></td>
                  <td width="45"><img src="datagrid2_images/next.gif" width="43" height="15" /></td>
                  <td width="40"><img src="datagrid2_images/last.gif" width="37" height="15" /></td>
                  <td width="100"><div align="center"><span class="STYLE1">转到第
                    <input name="textfield" type="text" size="4" style="height:12px; width:20px; border:1px solid #999999;" /> 
                    页 </span></div></td>
                  <td width="40"><img src="datagrid2_images/go.gif" width="37" height="15" /></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        
      </tr>
    </table></td>
  </tr>
  
</table>
</body>
</html>
<script src="js/Timer.js" type="text/javascript"></script>
<script type="text/javascript">
function clearTishi() {
           document.getElementById("Tishi").innerHTML="";
}
var timer = new Timer("clearTishi();", 10000, 1, false);

//10秒执行一次，启动一次就只执行一次
function restartTimer(){
	timer.Stop();
	timer.Start();
	stopCount();
	second = 10;
	countTime();
}

//10秒倒计时
var second = 10;
var t;
function countTime(){
	if (second != 1){
		second -= 1;
		document.getElementById("second").innerHTML ="<font color='red'>"+second+"</font>";
	}
	else{
		document.getElementById("second").innerHTML ="";
		return
	}
	t=setTimeout("countTime()",1000)
}
//关闭倒计时,以便重新计算
function stopCount() 
{ 
	clearTimeout(t) 
} 

</script>