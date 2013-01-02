package com.leec.lmodules_article.service;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.leec.lmodules_article.model.DTO.*;
import com.leec.lmodules_article.model.DAOIfc.*;
import com.leec.lmodules_article.model.Factory.*;
import com.leec.lmodules_article.util.path.GetRealPath;
import com.leec.lmodules_article.util.uuid.Uuid;

public class Service{
	
	public static List getadminlist(){	
		List<Lmo_article_adminDTO> ls=DAOFactory.getInstance().createLmo_article_adminDAO().findall();
		return ls;
	}
	
	//修改管理员
	public void doupdateuser(String _admin_id,String _admin_username,String _admin_pass){
		Lmo_article_adminDAOIfc dao=DAOFactory.getInstance().createLmo_article_adminDAO();
		Lmo_article_adminDTO dto=dao.findByPrimaryKey(_admin_id); 
			dto.setAdmin_id(_admin_id);
			dto.setAdmin_username(_admin_username);
			dto.setAdmin_pass(_admin_pass);
			dao.updateLmo_article_admin(dto);
		return;
	}
	
	//删除管理员
	public void dodeleteuser(String _admin_id){
		Lmo_article_adminDAOIfc dao=DAOFactory.getInstance().createLmo_article_adminDAO();
		dao.removeByPrimaryKey(_admin_id); 
		return;
	}
	
	//添加管理员
	public String doadduser(String _admin_username,String _admin_pass){
		String change="";
		Lmo_article_adminDAOIfc dao=DAOFactory.getInstance().createLmo_article_adminDAO();
		List<Lmo_article_adminDTO> ls=dao.findByAdmin_username(_admin_username); 		
		if(ls.size()==0){
			Lmo_article_adminDTO dto=new Lmo_article_adminDTO();
			Uuid uuid=new Uuid();
			Uuid _admin_id=uuid.create();
			String admin_id=_admin_id.toString();
			dto.setAdmin_id(admin_id);
			dto.setAdmin_username(_admin_username);
			dto.setAdmin_pass(_admin_pass);
			dao.createLmo_article_admin(dto);
			change="true";
			}
		return change;
	}
	
	//修改密码
	public String dopassword(String _admin_username,String _oldPassword,String _newPassword,String _rnewPassword){
		String change="";
		Lmo_article_adminDAOIfc dao=DAOFactory.getInstance().createLmo_article_adminDAO();
		List<Lmo_article_adminDTO> ls=dao.findByAdmin_username(_admin_username); 
		Lmo_article_adminDTO dto=ls.get(0);
		String pass2=dto.getAdmin_pass();
		if(dto.getAdmin_username()!=null&&_oldPassword.equals(pass2)&&!_oldPassword.equals(_newPassword)&&_newPassword.equals(_rnewPassword)){
			dto.setAdmin_id(dto.getAdmin_id());
			dto.setAdmin_username(_admin_username);
			dto.setAdmin_pass(_newPassword);
			dao.updateLmo_article_admin(dto);
			change="true";
			}
		return change;
	}
	
	//登录------
	public String dologin(String _admin_username,String _admin_pass){
		String admin_username="";
		Lmo_article_adminDAOIfc dao=DAOFactory.getInstance().createLmo_article_adminDAO();
		List<Lmo_article_adminDTO> ls=dao.findByAdmin_username(_admin_username);
		Lmo_article_adminDTO dto=ls.get(0);
		String pass2=dto.getAdmin_pass();
		if(dto.getAdmin_username()!=null&&_admin_pass.equals(pass2)){
			admin_username=_admin_username;			
			}
		return admin_username;
	}

	//检索---通过select获得type_id
	public String doselect(String _select){	
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=dao.findByPrimaryKey(_select); 
		return dto.getType_id();

	}
	//通过遍历传进来的type_father来查询type_id，最终返回所有type_name项
	public static String getArticleTypeListByTT(){
		String str = "";
		List<Lmo_article_typeDTO> ls=DAOFactory.getInstance().createLmo_article_typeDAO().findByTypefather("0");//一级分类
		for(int i=0;i<ls.size();i++){
			Lmo_article_typeDTO dto=ls.get(i);			
			str = str+"<option value='" + dto.getType_id() + "'>|--&nbsp;&nbsp;" + dto.getType_name() + "</option>";
			str = str+Subfl_getArticleTypeListByTT(dto.getType_id(), "&nbsp;&nbsp;|--&nbsp;&nbsp;"); // 循环子级分类			
		}
		return str;
	}
	public static String Subfl_getArticleTypeListByTT( String FID, String StrDis){
		String str = "";
		List<Lmo_article_typeDTO> ls=DAOFactory.getInstance().createLmo_article_typeDAO().findByTypefather(FID);//一级分类
		for(int i=0;i<ls.size();i++){
			Lmo_article_typeDTO dto=ls.get(i);			
			str += "<option  value='" + dto.getType_id() + "'>" + StrDis + dto.getType_name();
			str += Subfl_getArticleTypeListByTT(dto.getType_id(), "&nbsp;&nbsp " + StrDis); // 递归子级分类
			str += "</option>";		
		}
		return str;
	}
	//通过type_father来查询type_id，最终返回所有type_id项
	public static String getchildlist(String _father){
		String str = "";
		List<Lmo_article_typeDTO> ls=DAOFactory.getInstance().createLmo_article_typeDAO().findByTypefather(_father);
		for(int i=0;i<ls.size();i++){
			Lmo_article_typeDTO dto=ls.get(i);
			String father=getchildlist(dto.getType_id());
			List<Lmo_articleDTO> ls2=DAOFactory.getInstance().createLmo_articleDAO().findByArticle_type_id(dto.getType_id());
			//System.out.println("((((((((((("+dto.getType_id()+")))))))"+ls2.size());
			if(father.equals("")&&ls2.size()==0){
				str+= "<tr>"                   	
                    +"<td height=20 bgcolor=#FFFFFF><div align=center>"
              		+"<input type=checkbox name=chkSon id=chkSon onclick=ChkSonClick('chkSon','chkAll')/>"
            		+"</div></td>"
            		+"<td height=20 bgcolor=#FFFFFF><div align=center class=STYLE1>"
              		+"<div align=center>"+dto.getType_id()
            		+"</div></div></td>"
					+"<td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1> "+dto.getType_name()
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>查看"  
					+"</span></div></td><td bgcolor=#FFFFFF><div align=center><span class=STYLE1>查看" //--------------未编辑 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>" + dto.getType_order() 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE4>" 
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"add.jsp?_type_father="+dto.getType_id()+"\">新增子类</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/edt.gif  width=16 height=16 />&nbsp;<a href=\"update.jsp?_type_id="+dto.getType_id()+"\" >编辑</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/del.gif  width=16 height=16 />&nbsp;<a href=\"Servlet.do?method=delete&_type_id="+dto.getType_id()+"\">删除</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"shangchuan.jsp?_type_father="+dto.getType_id()+"\">添加文章</a>&nbsp; &nbsp;"
					+"</span></div></td></tr>"; 
			}else if(father.equals("")&&ls2.size()!=0){
				str+= "<tr>"                   	
                    +"<td height=20 bgcolor=#FFFFFF><div align=center>"
              		+"<input type=checkbox name=chkSon id=chkSon onclick=ChkSonClick('chkSon','chkAll')/>"
            		+"</div></td>"
            		+"<td height=20 bgcolor=#FFFFFF><div align=center class=STYLE1>"
              		+"<div align=center>"+dto.getType_id()
            		+"</div></div></td>"
					+"<td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1> "+dto.getType_name()
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>查看"  
					+"</span></div></td><td bgcolor=#FFFFFF><div align=center><span class=STYLE1><a href=\"articlelist.jsp?_type_id="+dto.getType_id()+"\">查看</a>" //--------------未编辑 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>" + dto.getType_order() 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE4>" 
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"add.jsp?_type_father="+dto.getType_id()+"\">新增子类</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/edt.gif  width=16 height=16 />&nbsp;<a href=\"update.jsp?_type_id="+dto.getType_id()+"\" >编辑</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/del.gif  width=16 height=16 />&nbsp;<a href=\"Servlet.do?method=delete&_type_id="+dto.getType_id()+"\">删除</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"shangchuan.jsp?_type_father="+dto.getType_id()+"\">添加文章</a>&nbsp; &nbsp;"
					+"</span></div></td></tr>"; 
			}else if(!father.equals("")&&ls2.size()==0){
			str+= "<tr>"                   	
                    +"<td height=20 bgcolor=#FFFFFF><div align=center>"
              		+"<input type=checkbox name=checkbox value="+dto.getType_id()+" id=chkSon onclick=ChkSonClick('chkSon','chkAll')/>"
            		+"</div></td>"
            		+"<td height=20 bgcolor=#FFFFFF><div align=center class=STYLE1>"
              		+"<div align=center>"+dto.getType_id()
            		+"</div></div></td>"
					+"<td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1> "+dto.getType_name()
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1><a href=\"list.jsp?_type_id="+dto.getType_id()+"\">查看</a>"  
					+"</span></div></td><td bgcolor=#FFFFFF><div align=center><span class=STYLE1>查看" //--------------未编辑 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>" + dto.getType_order() 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE4>" 
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"add.jsp?_type_father="+dto.getType_id()+"\">新增子类</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/edt.gif  width=16 height=16 />&nbsp;<a href=\"update.jsp?_type_id="+dto.getType_id()+"\" >编辑</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/del.gif  width=16 height=16 />&nbsp;<a href=\"Servlet.do?method=delete&_type_id="+dto.getType_id()+"\">删除</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"shangchuan.jsp?_type_father="+dto.getType_id()+"\">添加文章</a>&nbsp; &nbsp;"
					+"</span></div></td></tr>"; 
		}else{
			str+= "<tr>"                   	
                    +"<td height=20 bgcolor=#FFFFFF><div align=center>"
              		+"<input type=checkbox name=checkbox value="+dto.getType_id()+" id=chkSon onclick=ChkSonClick('chkSon','chkAll')/>"
            		+"</div></td>"
            		+"<td height=20 bgcolor=#FFFFFF><div align=center class=STYLE1>"
              		+"<div align=center>"+dto.getType_id()
            		+"</div></div></td>"
					+"<td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1> "+dto.getType_name()
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1><a href=\"list.jsp?_type_id="+dto.getType_id()+"\">查看</a>"  
					+"</span></div></td><td bgcolor=#FFFFFF><div align=center><span class=STYLE1><a href=\"articlelist.jsp?_type_id="+dto.getType_id()+"\">查看</a>" //--------------未编辑 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE1>" + dto.getType_order() 
					+"</span></div></td><td height=20 bgcolor=#FFFFFF><div align=center><span class=STYLE4>" 
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"add.jsp?_type_father="+dto.getType_id()+"\">新增子类</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/edt.gif  width=16 height=16 />&nbsp;<a href=\"update.jsp?_type_id="+dto.getType_id()+"\" >编辑</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/del.gif  width=16 height=16 />&nbsp;<a href=\"Servlet.do?method=delete&_type_id="+dto.getType_id()+"\">删除</a>&nbsp; &nbsp;"
					+"<img src=datagrid2_images/22.gif  width=16 height=16 />&nbsp;<a href=\"shangchuan.jsp?_type_father="+dto.getType_id()+"\">添加文章</a>&nbsp; &nbsp;"
					+"</span></div></td></tr>"; 
		}
		}	
		return str;
		
	}

	//通过type_id来查询type_father,最终返回所有type_father项
	public static String getfatheridlist(String _id){
		String str = "";
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();		
		if(_id==null||_id.equals("0")){
			str+= "返回上一级";
		}
		else{
			Lmo_article_typeDTO dto=dao.findByPrimaryKey(_id);
			str+= "<a href=\"list.jsp?_type_id="+dto.getType_father()+"\">返回上一级</a>";
		}          		
		return str;
		
	}
	
	public static List getarticlelist(String _article_type_id){	
		List<Lmo_articleDTO> ls=DAOFactory.getInstance().createLmo_articleDAO().findByArticle_type_id(_article_type_id);
		return ls;
	}
	
	//------------------------删除分类--------------------
	public String dodelete(String _type_id) {	//111		
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();	
		List<Lmo_article_typeDTO> ls=dao.findByTypefather(_type_id);
		Lmo_articleDAOIfc daoo=DAOFactory.getInstance().createLmo_articleDAO();	
		
		for(int i=0;i<ls.size();i++){
			Lmo_article_typeDTO dto=ls.get(i);
			dosub_delete(dto.getType_id());//dosub_delete 1111			
			List<Lmo_articleDTO> ls2=daoo.findByArticle_type_id(dto.getType_id());//找type是1111的文章
			for(int ii=0;ii<ls2.size();ii++){
				Lmo_articleDTO dtoo=ls2.get(ii);
				String article_id=dtoo.getArticle_id();//取到article_id
				dodelete3(article_id);//delete article以及files
			}
			DAOFactory.getInstance().createLmo_article_typeDAO().removeByPrimaryKey(dto.getType_id());//remove 1111
			
		}
		
		String type_father=DAOFactory.getInstance().createLmo_article_typeDAO().findByPrimaryKey(_type_id).getType_father();		
		List<Lmo_articleDTO> ls3=daoo.findByArticle_type_id(_type_id);//找type是111的文章		
		for(int iii=0;iii<ls3.size();iii++){
			Lmo_articleDTO dtooo=ls3.get(iii);
			String article_id=dtooo.getArticle_id();//取到article_id
			dodelete3(article_id);//delete article以及files
		}
		DAOFactory.getInstance().createLmo_article_typeDAO().removeByPrimaryKey(_type_id);//remove 111
		return type_father;
	}
	//---------------------循环删除子分类-------------------------
	public static void dosub_delete(String _id){//1111	
		List<Lmo_article_typeDTO> ls=DAOFactory.getInstance().createLmo_article_typeDAO().findByTypefather(_id);	
		for(int i=0;i<ls.size();i++){		
			Lmo_article_typeDTO dto=ls.get(i);
			dosub_delete(dto.getType_id());//dosub_delete 11111
			Lmo_articleDAOIfc daoo=DAOFactory.getInstance().createLmo_articleDAO();	
			List<Lmo_articleDTO> ls2=daoo.findByArticle_type_id(dto.getType_id());//找type是11111的文章
			for(int ii=0;ii<ls2.size();ii++){
				Lmo_articleDTO dtoo=ls2.get(ii);
				String article_id=dtoo.getArticle_id();//取到article_id
				dodelete3(article_id);//delete article以及files
			}
			DAOFactory.getInstance().createLmo_article_typeDAO().removeByPrimaryKey(dto.getType_id());//remove 11111
			
		}		
		return;
	
	}
	//-------------------------增加分类-------------------
	public String doadd(String _type_name, Integer _type_order,String _type_desc,String _type_father){
		String str="";
		Uuid uuid=new Uuid();
		Uuid _type_id=uuid.create();
		String type_id=_type_id.toString();
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		List<Lmo_article_typeDTO> ls=dao.findByTypename(_type_name);
			if(ls.size()>0){
				for(int i=0;i<ls.size();i++){
					if(ls.get(i).getType_father().equals(_type_father)){
						str="1";
					}else{str="2";}
				}
				if(str.equals("2")){
					Lmo_article_typeDTO dtoo=new Lmo_article_typeDTO();		
					dtoo.setType_id(type_id);
					dtoo.setType_name(_type_name);
					dtoo.setType_order(_type_order);                     
					dtoo.setType_desc(_type_desc);
					dtoo.setType_father(_type_father);	
					dao.createLmo_article_type(dtoo);
				}
				
				}
			else{
				Lmo_article_typeDTO dtoo=new Lmo_article_typeDTO();		
				dtoo.setType_id(type_id);
				dtoo.setType_name(_type_name);
				dtoo.setType_order(_type_order);                     
				dtoo.setType_desc(_type_desc);
				dtoo.setType_father(_type_father);	
				dao.createLmo_article_type(dtoo);	
			}
		return str;
	}
	
	public static String dogetname(String _type_id){	
		String type_name="";
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=dao.findByPrimaryKey(_type_id);                 
			type_name = dto.getType_name();
		return type_name;		
	}

	public static int dogetorder(String _type_id){	
		Integer type_order=null;
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=dao.findByPrimaryKey(_type_id);                 
		type_order=Integer.valueOf(dto.getType_order());
		return type_order;		
	}

	public static String dogetdesc(String _type_id){	
		String type_desc="";
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=dao.findByPrimaryKey(_type_id);                 
			type_desc = dto.getType_desc();
		return type_desc;		
	}

	public static String dogetfather(String _type_id){	
		String type_father="";
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=dao.findByPrimaryKey(_type_id);                 
			type_father = dto.getType_father();
			return type_father;		
}
	
	//--------------------------分类更新（编辑）------------------------------
	public void doupdate2(String _type_id,String _type_name,int _type_order,String _type_desc,String _type_father){	
		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
		Lmo_article_typeDTO dto=new Lmo_article_typeDTO();
			dto.setType_id(_type_id);
			dto.setType_name(_type_name);
			dto.setType_order(_type_order);
			dto.setType_desc(_type_desc);
			dto.setType_father(_type_father);
			dao.updateLmo_article_type(dto);
	}	
	
	//------------------------文章上传-------------------------
	public void shangchuan(List _ls,String _article_id,String _type,String _is_tuwen,String _title,String _keys,String _zuozhe,
						   String _from1,String _from2,String _intro,String _content,
						   String _picture,String _link,String _checkbox,Timestamp _time){
		
		Lmo_articleDAOIfc dao=DAOFactory.getInstance().createLmo_articleDAO();
		Lmo_articleDTO dto=new Lmo_articleDTO();
		for(int i=0;i<_ls.size();i++){
			String picture=(String)_ls.get(i);		
			String[] s=picture.split("\\.");
			Lmo_article_filesDAOIfc daoo=DAOFactory.getInstance().createLmo_article_filesDAO();
			Lmo_article_filesDTO dtoo=DAOFactory.getInstance().createLmo_article_filesDAO().findByPrimaryKey(s[0]);
			dtoo.setArticle_id(_article_id);
			dtoo.setFile_id(dtoo.getFile_id());
			dtoo.setFile_path(dtoo.getFile_path());
			dtoo.setFile_title(dtoo.getFile_title());
			dtoo.setFile_desc(dtoo.getFile_desc());
			daoo.updateLmo_article_files(dtoo);
		}
		
		dto.setArticle_id(_article_id);
		dto.setArticle_type_id(_type);
		dto.setArticle_title(_title);
		dto.setArticle_keys(_keys);
		dto.setArticle_from("("+_from2+")"+_from1);	
		dto.setArticle_intro(_intro);		
		dto.setArticle_zuozhe(_zuozhe);		
		dto.setArticle_content(_content);
		if(_is_tuwen.equals("1")){
			dto.setArticle_is_tuwen(1);
		}
		else{
			dto.setArticle_is_tuwen(0);
		}		
		dto.setArticle_picture(_picture);
		
		if(_checkbox.equals("1")){
			dto.setArticle_is_link(1);
		}
		else{
			dto.setArticle_is_link(0);
		}
		dto.setArticle_link_url(_link);
		dto.setArticle_is_tuijian(0000);
		dto.setArticle_hits(0000);
		dto.setArticle_fabu_time(_time);
		dto.setArticle_paixu(0000);
		dao.createLmo_article(dto);
/*		System.out.println("00000000"+_article_id+"11111111"+article_type_id+"2222222222"+_title
				+"3333333"+_keys+"44444444"+_from1+"555555555"+_from2+"66666666"+_intro
				+"777777777"+_zuozhe+"88888888"+_content+"99999999999"+_is_tuwen+"11111110000000"+_picture+
				"22222222000"+_checkbox+"33333333330000"+_link+"555555550000"+_time);
*/	
}

	//------------------------文章编辑-------------------------
	public void doarticleupdate(List _ls,String _article_id,String _type,String _is_tuwen,String _title,String _keys,String _zuozhe,
						   String _from,String _intro,String _content,
						   String _picture,String _link,String _checkbox,Timestamp _time){
		
		Lmo_articleDAOIfc dao=DAOFactory.getInstance().createLmo_articleDAO();
		Lmo_articleDTO dto=dao.findByPrimaryKey(_article_id);
		for(int i=0;i<_ls.size();i++){
			String picture=(String)_ls.get(i);		
			String[] s=picture.split("\\.");
			Lmo_article_filesDAOIfc daoo=DAOFactory.getInstance().createLmo_article_filesDAO();
			Lmo_article_filesDTO dtooo=daoo.findByPrimaryKey(s[0]);
			dtooo.setArticle_id(_article_id);
			dtooo.setFile_id(dtooo.getFile_id());
			dtooo.setFile_path(dtooo.getFile_path());
			dtooo.setFile_title(dtooo.getFile_title());
			dtooo.setFile_desc(dtooo.getFile_desc());
			daoo.updateLmo_article_files(dtooo);
		}
		dto.setArticle_id(_article_id);
		dto.setArticle_type_id(_type);
		dto.setArticle_title(_title);
		dto.setArticle_keys(_keys);
		dto.setArticle_from(_from);	
		dto.setArticle_intro(_intro);		
		dto.setArticle_zuozhe(_zuozhe);		
		dto.setArticle_content(_content);
		if(_is_tuwen.equals("1")){
			dto.setArticle_is_tuwen(1);
		}
		else{
			dto.setArticle_is_tuwen(dto.getArticle_is_tuwen());
		}
		if(_picture.equals("")||_picture.equals("null")||_picture==null){
			dto.setArticle_picture(dto.getArticle_picture());
		}
		else{
			dto.setArticle_picture(_picture);
		}	
		if(_checkbox.equals("1")){
			dto.setArticle_is_link(1);
		}
		else{
			dto.setArticle_is_link(0);
		}
		dto.setArticle_link_url(_link);
		dto.setArticle_is_tuijian(0000);
		dto.setArticle_hits(0000);
		dto.setArticle_fabu_time(_time);
		dto.setArticle_paixu(0000);
		dao.updateLmo_article(dto);
/*		System.out.println("00000000"+_article_id+"11111111"+article_type_id+"2222222222"+_title
				+"3333333"+_keys+"44444444"+_from1+"555555555"+_from2+"66666666"+_intro
				+"777777777"+_zuozhe+"88888888"+_content+"99999999999"+_is_tuwen+"11111110000000"+_picture+
				"22222222000"+_checkbox+"33333333330000"+_link+"555555550000"+_time);
*/	
}

	
	
	public static List getarticle(String _article_id){	
		List<Lmo_articleDTO> ls=DAOFactory.getInstance().createLmo_articleDAO().findByPrimaryKey2(_article_id);
		return ls;
	}

	//--------------------------更改浏览次数------------------------
	public static void dohits(String _article_id,int _hits){	
		Lmo_articleDAOIfc dao=DAOFactory.getInstance().createLmo_articleDAO();
		Lmo_articleDTO dto=dao.findByPrimaryKey(_article_id);
		//Lmo_articleDTO dtoo=new Lmo_articleDTO();
		dto.setArticle_id(_article_id);
		dto.setArticle_type_id(dto.getArticle_type_id());
		dto.setArticle_title(dto.getArticle_title());
		dto.setArticle_keys(dto.getArticle_keys());
		dto.setArticle_from(dto.getArticle_from());
		dto.setArticle_intro(dto.getArticle_intro());
		dto.setArticle_zuozhe(dto.getArticle_zuozhe());
		dto.setArticle_content(dto.getArticle_content());
		dto.setArticle_is_tuwen(dto.getArticle_is_tuwen());
		dto.setArticle_picture(dto.getArticle_picture());
		dto.setArticle_is_link(dto.getArticle_is_link());
		dto.setArticle_link_url(dto.getArticle_link_url());
		dto.setArticle_is_tuijian(dto.getArticle_is_tuijian());
		dto.setArticle_hits(_hits);
		dto.setArticle_fabu_time(dto.getArticle_fabu_time());
		dao.updateLmo_article(dto);
		return;
	}

	//--------------------------上传文件------------------------
	public static void chuanfile(String _newFileName,String _filename){
		Lmo_article_filesDAOIfc dao=DAOFactory.getInstance().createLmo_article_filesDAO();
		Lmo_article_filesDTO dto=new Lmo_article_filesDTO();
		dto.setArticle_id("noid");
		dto.setFile_id(_filename);
		dto.setFile_path(_newFileName);
		dto.setFile_title("");
		dto.setFile_desc("");
		dao.createLmo_article_files(dto);
	}
	
	//------------------------删除图片--------------------？？？？？？？？？？？？？？？？？？？？？
	public void dodelete2() {			
		Lmo_article_filesDAOIfc dao=DAOFactory.getInstance().createLmo_article_filesDAO();	
		List<Lmo_article_filesDTO> ls=dao.findByArticle_id("noid");
		String path=GetRealPath.getWebRootUrl();
		for(int i=0;i<ls.size();i++){		
			Lmo_article_filesDTO dto=ls.get(i);			
			//System.out.print("____1___"+path);
			File f = new File(path+dto.getFile_path());//相对路径删除不了--------------
			f.delete();
		}				
		dao.RemoveByArticle_id("noid");	
		return;
	}
	
	//------------------------删除文章和相关文件--------------------？？？？？？？？？？？？？？？？？？？？？
	public static String dodelete3(String _article_id) {	
		String article_type_id="";
		Lmo_article_filesDAOIfc dao=DAOFactory.getInstance().createLmo_article_filesDAO();	
		List<Lmo_article_filesDTO> ls=dao.findByArticle_id(_article_id);
		String path=GetRealPath.getWebRootUrl();
		for(int i=0;i<ls.size();i++){		
			Lmo_article_filesDTO dto=ls.get(i);			
			File f = new File(path+dto.getFile_path());//相对路径删除不了--------------			
			f.delete();
		}
		dao.RemoveByArticle_id(_article_id);
		Lmo_articleDAOIfc daoo=DAOFactory.getInstance().createLmo_articleDAO();	
		Lmo_articleDTO dtoo=daoo.findByPrimaryKey(_article_id);
		article_type_id =dtoo.getArticle_type_id();
		//System.out.print("____1___"+article_type_id);
		File ff = new File(path+"\\"+dtoo.getArticle_picture());//相对路径删除不了--------------			
		ff.delete();
		daoo.removeByPrimaryKey(_article_id);
		//System.out.print("___2____"+article_type_id);
		return article_type_id;
	}
}







/*public static void main(String[] args)
 {		
	
	/*  原来此处操作是：
	 * 		Lm_article_typeDAO dao=new Lm_article_typeDAO();
	 * 		Lm_article_typeDTO dto=new Lm_article_typeDTO();
	 * 	就此看来，如果service调用的DAO-DTO已经写死了，依赖性非常强。
	 * 	为了减少service对DAO的依赖性，引入了接口（Ifc）和工厂(Factory)，以使service中DAO-DTO不写死，方便使用其他的DAO-DTO，
	 * 	只要在properties配置中改变一点东西。
	 */
	
	//此句得到一个接口，这个接口是通过
	//1.调用DAOFactory.java中getInstance()方法，得到properties中的DAOFactory代表的工厂，其实是zfactory
	//2.调用zfactory.java中createLm_article_typeDAO()方法，得到Lm_article_typeDAO
/*		Lmo_article_typeDAOIfc dao=DAOFactory.getInstance().createLmo_article_typeDAO();
	Lmo_article_typeDTO dto=dao.findByPrimaryKey("4"); 
	System.out.println("type:"+dto.getType_name());
	
	Lmo_article_adminDAOIfc dao2=DAOFactory.getInstance().createLmo_article_adminDAO();
	Lmo_article_adminDTO dto2=dao2.findByPrimaryKey("2");
	System.out.println("admin:"+dto2.getAdmin_username());
	
	Lmo_article_filesDAOIfc dao3=DAOFactory.getInstance().createLmo_article_filesDAO();
	Lmo_article_filesDTO dto3=dao3.findByPrimaryKey("3");
	System.out.println("files:"+dto3.getArticle_id());
	
	Lmo_articleDAOIfc dao4=DAOFactory.getInstance().createLmo_articleDAO();
	Lmo_articleDTO dto4=dao4.findByPrimaryKey("1");
	System.out.println("article:"+dto4.getArticle_type_id());
	
		
}*/