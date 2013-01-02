package com.leec.lmodules_article.servlet;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import com.leec.lmodules_article.service.*;
import com.leec.lmodules_article.util.uuid.Uuid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * xhEditor文件上传的Java - Servlet实现.
 * @author easinchu
 *
 */
@SuppressWarnings({ "serial", "deprecation" })
public class Servlet extends HttpServlet{

	private static String baseDir = "/ARTICLE_IMG/";//上传文件存储目录
	private static String fileExt = "jpg,jpeg,bmp,gif,png";
	private static Long maxSize = 0l;	

	// 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存
	private static String dirType = "1";

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");		
			if(request.getParameter("method")!=null){
				String d=(String)request.getParameter("method");
				if(d.equals("select")){
						this.doselect(request, response);
				}else if(d.equals("delete")){
						this.dodelete(request, response);
				}else if(d.equals("add")){
						this.doadd(request, response);		
				}else if(d.equals("update2")){
						this.doupdate2(request, response);
				}else if(d.equals("shangchuan")){
						this.shangchuan(request, response);
				}else if(d.equals("articleupdate")){
						this.doarticleupdate(request, response);
				}else if(d.equals("delete3")){
						this.dodelete3(request, response);
				}else if(d.equals("login")){
						this.dologin(request, response);
				}else if(d.equals("password")){
						this.dopassword(request, response);
				}else if(d.equals("adduser")){
						this.doadduser(request, response);
				}else if(d.equals("deleteuser")){
						this.dodeleteuser(request, response);
				}else if(d.equals("updateuser")){
						this.doupdateuser(request, response);
				}      
			}else {
				this.init();
				this.doimg(request, response);
				}	
			}
	//修改管理员
	public void doupdateuser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 		
		String admin_id = (String)request.getParameter("uid");
		String admin_username = (String)request.getParameter("admin_username");
		String admin_pass = (String)request.getParameter("admin_pass");
		Service src=new Service();  
		src.doupdateuser(admin_id,admin_username,admin_pass); 
		PrintWriter out = response.getWriter();
		out.println("{\"statusCode\":\"200\",\"message\":\"\u64cd\u4f5c\u6210\u529f\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}
	
	//删除管理员
	public void dodeleteuser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 		
		String admin_id = (String)request.getParameter("uid");
		Service src=new Service(); 
		src.dodeleteuser(admin_id);
		PrintWriter out = response.getWriter();
		out.println("{\"statusCode\":\"200\",\"message\":\"\u64cd\u4f5c\u6210\u529f\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}
	
	//添加管理员
	public void doadduser(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		
		String admin_username = (String)request.getParameter("admin_username");
		String admin_pass = (String)request.getParameter("admin_pass");
				
		Service src=new Service();
		String change=src.doadduser(admin_username,admin_pass);
		PrintWriter out = response.getWriter();
		if(change.equals("true")){	
			out.println("{\"statusCode\":\"200\",\"message\":\"\u64cd\u4f5c\u6210\u529f\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}else{
			out.println("{\"statusCode\":\"300\",\"message\":\"\u64cd\u4f5c\u5931\u8d25\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}
	}
	
	//修改密码
	public void dopassword(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		String admin_username=(String)session.getAttribute("admin_username");
		
		String oldPassword = (String)request.getParameter("oldPassword");
		String newPassword = (String)request.getParameter("newPassword");
		String rnewPassword = (String)request.getParameter("rnewPassword");	
				
		Service src=new Service();
		String change=src.dopassword(admin_username,oldPassword,newPassword,rnewPassword);
		PrintWriter out = response.getWriter();
		if(change.equals("true")){	
			out.println("{\"statusCode\":\"200\",\"message\":\"\u64cd\u4f5c\u6210\u529f\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}else{
			out.println("{\"statusCode\":\"300\",\"message\":\"\u64cd\u4f5c\u5931\u8d25\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}");
		}
	}
	//登录
	public void dologin(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String admin_username = (String)request.getParameter("admin_username");
		String admin_pass = (String)request.getParameter("admin_pass");
		HttpSession session = request.getSession();			    						 		
		Service src=new Service();
		String admin_username2=src.dologin(admin_username,admin_pass);
		if(!admin_username2.equals("")){
			session.setAttribute("admin_username",admin_username2);
			response.sendRedirect("index.html");
		}else{
			response.sendRedirect("login.html");
		}
	}
	
	
	
	
	
	/**
	 * 文件上传初始化工作
	 */
	public void init() throws ServletException {
		/*获取web.xml中servlet的配置文件目录参数*/
		baseDir = this.getInitParameter("baseDir");
		
		/*获取文件上传存储的相当路径*/
		if (StringUtils.isBlank(baseDir)) baseDir = "/ARTICLE_IMG/";
		
		String realBaseDir = this.getServletConfig().getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}

		/*获取文件类型参数*/
		fileExt = this.getInitParameter("fileExt");
		if (StringUtils.isBlank(fileExt)) fileExt = "jpg,jpeg,gif,bmp,png";

		/*获取文件大小参数*/
		String maxSize_str = this.getInitParameter("maxSize");
		if (StringUtils.isNotBlank(maxSize_str)) maxSize = new Long(maxSize_str);
		
		/*获取文件目录类型参数*/
		dirType = this.getInitParameter("dirType");
		
		if (StringUtils.isBlank(dirType))
			dirType = "1";
		if (",0,1,2,3,".indexOf("," + dirType + ",") < 0)
			dirType = "1";
	}
	
	

	/**
	 * 上传文件数据处理过程
	 */
	@SuppressWarnings({"unchecked" })
	public void doimg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String err = "";
		String newFileName = "";

		DiskFileUpload upload = new DiskFileUpload();
		try {
			List<FileItem> items = upload.parseRequest(request);
			Map<String, Serializable> fields = new HashMap<String, Serializable>();
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField())
					fields.put(item.getFieldName(), item.getString());
				else
					fields.put(item.getFieldName(), item);
			}
			
			/*获取表单的上传文件*/
			FileItem uploadFile = (FileItem)fields.get("filedata");
			
			/*获取文件上传路径名称*/
			String fileNameLong = uploadFile.getName();
			//System.out.println("fileNameLong:" + fileNameLong);
			
			/*获取文件扩展名*/
			/*索引加1的效果是只取xxx.jpg的jpg*/
			String extensionName = fileNameLong.substring(fileNameLong.lastIndexOf(".") + 1);
			//System.out.println("extensionName:" + extensionName);
			
			/*检查文件类型*/
			if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0){
				printInfo(response, "不允许上传此类型的文件", "");
				return;
			}
			/*文件是否为空*/
			if (uploadFile.getSize() == 0){
				printInfo(response, "上传文件不能为空", "");
				return;
			}
			/*检查文件大小*/
			if (maxSize > 0 && uploadFile.getSize() > maxSize){
				printInfo(response, "上传文件的大小超出限制", "");
				return;
			}
			
			//0:不建目录, 1:按天存入目录, 2:按月存入目录, 3:按扩展名存目录.建议使用按天存.
			String fileFolder = "";
			if (dirType.equalsIgnoreCase("1"))
				fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());;
			if (dirType.equalsIgnoreCase("2"))
				fileFolder = new SimpleDateFormat("yyyyMM").format(new Date());
			if (dirType.equalsIgnoreCase("3"))
				fileFolder = extensionName.toLowerCase();
			
			/*文件存储的相对路径*/
			String saveDirPath = baseDir + fileFolder + "/";
			//System.out.println("saveDirPath:" + saveDirPath);
			
			/*文件存储在容器中的绝对路径*/
			String saveFilePath = this.getServletConfig().getServletContext().getRealPath("") + saveDirPath;
			//System.out.println("saveFilePath:" + saveFilePath);
			
			/*构建文件目录以及目录文件*/
			File fileDir = new File(saveFilePath);
			if (!fileDir.exists()) {fileDir.mkdirs();}
			
			/*重命名文件*/
			String filename = UUID.randomUUID().toString();
			File savefile = new File(saveFilePath + filename + "." + extensionName);
			
			/*存储上传文件*/
			//System.out.println(upload == null);
			uploadFile.write(savefile);
			
			//这个地方根据项目的不一样，需要做一些特别的定制。
			newFileName = "/lmodules_article" + saveDirPath + filename + "." + extensionName;
			//System.out.println("newFileName:" + newFileName);
			Service sc =new Service();
			sc.chuanfile(saveDirPath + filename + "." + extensionName,filename);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			newFileName = "";
			err = "错误: " + ex.getMessage();
		}
		printInfo(response, err, newFileName);
	}
	
	/**
	 * 使用I/O流输出 json格式的数据
	 * @param response
	 * @param err
	 * @param newFileName
	 * @throws IOException
	 */
	public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {
		PrintWriter out = response.getWriter();
		//String filename = newFileName.substring(newFileName.lastIndexOf("/") + 1);
		System.out.print("----------"+err+"------------"+newFileName);
		out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
		out.flush();
		out.close();
	}
	
	//检索---通过select获得type_id
	public void doselect(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String select=(String)request.getParameter("news");
			Service src=new Service();
			response.sendRedirect("list.jsp?_type_id="+src.doselect(select));
	}
	
	//------------------------删除分类--------------------
	public void dodelete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			
			request.setCharacterEncoding("UTF-8");	
			String type_id=(String)request.getParameter("_type_id");			
			Service src=new Service();
			response.sendRedirect("list.jsp?_type_id="+src.dodelete(type_id));
	}
	//-------------------------增加分类-------------------
	public void doadd(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");	
			if(request.getParameter("type_name").equals("")){
				response.sendRedirect("add.jsp?msg=a");
				return;
			}
			if(request.getParameter("type_order").equals("")){
				response.sendRedirect("add.jsp?msg=b");
				return;
			}
			if(request.getParameter("type_desc").equals("")){
				response.sendRedirect("add.jsp?msg=c");
				return;
			}					
			String type_name=(String)request.getParameter("type_name");
			Integer type_order=Integer.valueOf(request.getParameter("type_order"));
			String type_desc=(String)request.getParameter("type_desc");
			String type_father=(String)request.getParameter("type_father");					
			//System.out.println("-------11111---------"+type_father);		
			Service src=new Service();			
			if(!src.doadd(type_name,type_order,type_desc,type_father).equals("1")){
				response.sendRedirect("list.jsp?_type_id="+type_father);
						
			}else{
				response.sendRedirect("add.jsp?msg=1&_type_father="+type_father);	
				
			}
		
	}
	//--------------------------分类更新（编辑）------------------------------										
	public void doupdate2(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String type_id=(String)request.getParameter("type_id");
			String type_name=(String)request.getParameter("type_name");
			Integer type_order=Integer.valueOf(request.getParameter("type_order"));
			String type_desc=(String)request.getParameter("type_desc");
			String type_father=(String)request.getParameter("type_father");
            String age2=(String)request.getParameter("age2");
			/*if(username2.equals("")){
				response.sendRedirect("update.jsp?method=update&_userid="+userid2+"&msg=a");
				return;
			}
			if(pass2.equals("")){
				response.sendRedirect("update.jsp?method=update&_userid="+userid2+"&msg=b");
				return;
			}*/
//            System.out.println("00000000"+type_order);
			Service src=new Service();
			src.doupdate2(type_id,type_name,type_order,type_desc,type_father);
			response.sendRedirect("list.jsp?_type_id="+type_father);
	}		
	//------------------------文章上传-------------------------
	public void shangchuan(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String article_type_id=(String)request.getParameter("_article_type_id");
		
		int y=new Date().getYear()+1900;
		int m=new Date().getMonth()+1;
		int d=new Date().getDate();
		Timestamp time=new Timestamp(new Date().getTime());
		

        String article_id="";	
        String picture="";
        
		final long MAX_SIZE = 1 * 1024 * 1024;// 设置上传文件最大为 1M
		// 允许上传的文件格式的列表
		final String[] allowedExt = new String[] { "jpg","gif" };
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		dfif.setRepository(new File(request.getRealPath("/")
				+ "ARTICLE_IMG/images/"));// 设置存放临时文件的目录,web根目录下的Images目录
		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置最大上传尺寸
		sfu.setSizeMax(MAX_SIZE);
		sfu.setHeaderEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 从request得到 所有 上传域的列表
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
			//System.out.print("ggggggggggggggggggg"+fileList);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				out.println("文件尺寸超过规定大小:" + MAX_SIZE + "字节<p />");
				out.println("请重新操作，选择上传文件<p />");
				out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
//			    out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
				
				return;
			}
			e.printStackTrace();
		}
		// 没有文件上传
		if (fileList == null || fileList.size() == 0) {
			out.println("请选择上传文件<p />");
			out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
//			out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
			return;
		}
		// 得到所有上传的文件
		Iterator fileItr = fileList.iterator();
		
		//String type="";
		String is_tuwen="";
		String title="";
		String keys="";
		String zuozhe="";
		String from1="";
        String from2="";
        String intro="";
        String content="";
        String _pictures="";
        String link="";
        String checkbox="";
        List ls=new ArrayList();
		
		//循环处理所有文件
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			fileItem = (FileItem) fileItr.next();
//			System.out.print("ooooooooooooooooooo"+fileItem+"oooooooooooooooooooo");
			
			if(fileItem == null || fileItem.isFormField()){
                // 如果item是正常的表单域
                String name1 = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
//                if(name1.equals("type")&&value!=null&&!value.equals("")){
//                	type=value;
//                }
				if(name1.equals("title")&&value!=null&&!value.equals("")){
					title=value;
                }
				if(name1.equals("keys")&&value!=null&&!value.equals("")){
					keys=value;
                }
				if(name1.equals("zuozhe")&&value!=null&&!value.equals("")){
					zuozhe=value;
                }
				if(name1.equals("keys")&&value!=null&&!value.equals("")){
					keys=value;
                }
				if(name1.equals("from1")&&value!=null&&!value.equals("")){
					from1=value;
                }
				if(name1.equals("from2")&&value!=null&&!value.equals("")){
					from2=value;
                }
				if(name1.equals("intro")&&value!=null&&!value.equals("")){
					intro=value;
                }				
				if(name1.equals("content")&&value!=null&&!value.equals("")){
					content=value;
                }
				if(name1.equals("is_tuwen")&&value!=null&&!value.equals("")){
					is_tuwen=value;
                }
				if(name1.equals("link")&&value!=null&&!value.equals("")){
					link=value;
                }
				if(name1.equals("checkbox")&&value!=null&&!value.equals("")){
					checkbox=value;
                }
				if(name1.equals("_pictures")&&value!=null&&!value.equals("")){
					_pictures=value;
					ls.add(_pictures);
                }
            }			
			else{		

	//	   	 得到文件的完整路径
				String path = fileItem.getName();
	//			System.out.print("kkkkkkkkkkkkkkkkk"+path+"kkkkkkkkkkkkkkkkk");
				// 得到文件的大小
				long size = fileItem.getSize();
				//System.out.print("kkkkkkkkkkkkkkkkk"+is_tuwen+"kkkkkkkkkkkkkkkkk");//is_tuwen永远为0
				if (is_tuwen.equals("1")&&("".equals(path) ||size == 0)) {
					out.println("请重新操作，选择上传文件<p />");
					out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
	//				out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
					return;
				}
				// 得到去除路径的文件名
				String t_name = path.substring(path.lastIndexOf("\\") + 1);
				// 得到文件的扩展名(无扩展名时将得到全名)
				String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
				// 拒绝接受规定文件格式之外的文件类型
				int allowFlag = 0;
				int allowedExtCount = allowedExt.length;
				for (; allowFlag < allowedExtCount; allowFlag++) {
					if (allowedExt[allowFlag].equals(t_ext))
						break;
				}
				//System.out.print("---------------"+is_tuwen+"-------------------");
				if (is_tuwen.equals("1")&&allowFlag == allowedExtCount) {
					out.println("请重新操作，上传以下类型的文件<p />");
					for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
						out.println("*." + allowedExt[allowFlag]
								+ "&nbsp;&nbsp;&nbsp;");
					out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
	//				out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
					return;
				}
			
				Uuid uuid=new Uuid();
				Uuid _article_id=uuid.create();
				article_id=_article_id.toString();				
				// 根据系统时间生成上传后保存的文件名
				// 保存的最终文件完整路径,保存在web根目录下的Images目录下
				//System.out.println("++++++++++++++++"+request.getRealPath("/")+"++++++++++++++++");
				String u_name = request.getRealPath("/") + "ARTICLE_IMG/images/"
						+ article_id + "." + t_ext;
				if(!("".equals(path) ||size == 0)){
				try {
				    
					// 保存文件
					fileItem.write(new File(u_name));
					out.println("文件上传成功. 已保存为: " + article_id + "." + t_ext
							+ " &nbsp;&nbsp;文件大小: " + size + "字节<p />");
					//out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">继续上传</a>");
											
				} catch (Exception e) {
					e.printStackTrace();
				}}
				if(!("".equals(path) ||size == 0)){
					picture="ARTICLE_IMG/images/"+article_id + "." + t_ext;

				}

			
			} 
		}	
		//System.out.println("********************"+ls+"++++++++++++++++");
		//System.out.println("********************"+article_type_id+"******************");
		Service src=new Service(); 
		src.shangchuan(ls,article_id,article_type_id,is_tuwen,title,keys,zuozhe,from1,from2,intro,content,picture,link,checkbox,time);
		response.sendRedirect("articlelist.jsp?_type_id="+article_type_id);
}
	
	//--------------------------文章更新（编辑）------------------------------										
	public void doarticleupdate(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String article_id=(String)request.getParameter("_article_id");			
		String article_type_id=(String)request.getParameter("_article_type_id");
		
		int y=new Date().getYear()+1900;
		int m=new Date().getMonth()+1;
		int d=new Date().getDate();
		Timestamp time=new Timestamp(new Date().getTime());
		
		String picture="";
        
    	// 设置上传文件最大为 1M
		final long MAX_SIZE = 1 * 1024 * 1024;
		// 允许上传的文件格式的列表
		final String[] allowedExt = new String[] { "jpg","gif" };
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		dfif.setRepository(new File(request.getRealPath("/")
				+ "ARTICLE_IMG/images/"));// 设置存放临时文件的目录,web根目录下的Images目录
		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置最大上传尺寸
		sfu.setSizeMax(MAX_SIZE);
		sfu.setHeaderEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 从request得到 所有 上传域的列表
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
//			System.out.print("ggggggggggggggggggg"+fileList);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				out.println("文件尺寸超过规定大小:" + MAX_SIZE + "字节<p />");
				out.println("请重新操作，选择上传文件<p />");
				out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
//			    out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
				
				return;
			}
			e.printStackTrace();
		}
		// 没有文件上传
		if (fileList == null || fileList.size() == 0) {
			out.println("请选择上传文件<p />");
			out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
//			out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
			return;
		}
		// 得到所有上传的文件
		Iterator fileItr = fileList.iterator();
		
	
		String is_tuwen="";
		String title="";
		String keys="";
		String zuozhe="";
		String from="";
        String intro="";
        String content="";
        String _pictures="";
        String link="";
        String checkbox="";
        List ls=new ArrayList();
		
		//循环处理所有文件
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			fileItem = (FileItem) fileItr.next();
//			System.out.print("ooooooooooooooooooo"+fileItem+"oooooooooooooooooooo");
			
			if(fileItem == null || fileItem.isFormField()){
                // 如果item是正常的表单域
                String name1 = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                
				if(name1.equals("is_tuwen")&&value!=null&&!value.equals("")){
					is_tuwen=value;
                }
				if(name1.equals("title")&&value!=null&&!value.equals("")){
					title=value;
                }
				if(name1.equals("keys")&&value!=null&&!value.equals("")){
					keys=value;
                }
				if(name1.equals("zuozhe")&&value!=null&&!value.equals("")){
					zuozhe=value;
                }
				if(name1.equals("keys")&&value!=null&&!value.equals("")){
					keys=value;
                }
				if(name1.equals("from")&&value!=null&&!value.equals("")){
					from=value;
                }
				if(name1.equals("intro")&&value!=null&&!value.equals("")){
					intro=value;
                }				
				if(name1.equals("content")&&value!=null&&!value.equals("")){
					content=value;
                }
				if(name1.equals("link")&&value!=null&&!value.equals("")){
					link=value;
                }
				if(name1.equals("checkbox")&&value!=null&&!value.equals("")){
					checkbox=value;
                }
				if(name1.equals("_pictures")&&value!=null&&!value.equals("")){
					_pictures=value;
					ls.add(_pictures);
                }
            }
		else{		
			
//	   	 得到文件的完整路径
			String path = fileItem.getName();
//			System.out.print("kkkkkkkkkkkkkkkkk"+path+"kkkkkkkkkkkkkkkkk");
			// 得到文件的大小
			long size = fileItem.getSize();

			// 得到去除路径的文件名
			String t_name = path.substring(path.lastIndexOf("\\") + 1);
			// 得到文件的扩展名(无扩展名时将得到全名)
			String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
			// 拒绝接受规定文件格式之外的文件类型
			int allowFlag = 0;
			int allowedExtCount = allowedExt.length;
			for (; allowFlag < allowedExtCount; allowFlag++) {
				if (allowedExt[allowFlag].equals(t_ext))
					break;
			}//System.out.print("kkkkkkkkkkkkkkkkk"+is_tuwen+"kkkkkkkkkkkkkkkkk");
			if (is_tuwen.equals("1")&&allowFlag == allowedExtCount) {
				out.println("请重新操作，上传以下类型的文件<p />");
				for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
					out.println("*." + allowedExt[allowFlag]
							+ "&nbsp;&nbsp;&nbsp;");
				out.println("<a href=\"shangchuan.jsp\" target=\"_top\">返回</a>");
//				out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
				return;
			}
			
			// 根据系统时间生成上传后保存的文件名
			// 保存的最终文件完整路径,保存在web根目录下的Images目录下
			//System.out.println("++++++++++++++++"+request.getRealPath("/")+"++++++++++++++++");
			String u_name = request.getRealPath("/") + "ARTICLE_IMG/images/"
					+ article_id + "." + t_ext;
			if(!("".equals(path) ||size == 0)){
			try {
			    
				// 保存文件
				fileItem.write(new File(u_name));			        
				out.println("文件上传成功. 已保存为: " + article_id + "." + t_ext
						+ " &nbsp;&nbsp;文件大小: " + size + "字节<p />");
				//out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">继续上传</a>");
										
			} catch (Exception e) {
				e.printStackTrace();
			}}
			if(!("".equals(path) ||size == 0)){
				picture="ARTICLE_IMG/images/"+article_id + "." + t_ext;
			}
			
		}
	}		
		Service src=new Service(); 
		System.out.println("++++++++++++====="+picture);
		src.doarticleupdate(ls,article_id,article_type_id,is_tuwen,title,keys,zuozhe,from,intro,content,picture,link,checkbox,time);
		response.sendRedirect("articlelist.jsp?_type_id="+article_type_id);

	}
	
	//------------------------删除文章和相关文件--------------------
	public void dodelete3(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
			request.setCharacterEncoding("UTF-8");	
			String article_id=(String)request.getParameter("_article_id");			
			Service src=new Service();
			response.sendRedirect("articlelist.jsp?_type_id="+src.dodelete3(article_id));
	}
	
	

	
	
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	
	
/*	public void post(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		final long MAX_SIZE = 1 * 1024 * 1024;// 设置上传文件最大为 1M
		final String[] allowedExt = new String[] { "jpg","gif" };
		response.setContentType("text/jsp");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
		dfif.setRepository(new File(request.getRealPath("/")
				+ "imagesurl"));// 设置存放临时文件的目录,web根目录下的imagesurl目录

		// 用以上工厂实例化上传组件
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置最大上传尺寸
		sfu.setSizeMax(MAX_SIZE);
		sfu.setHeaderEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 从request得到 所有 上传域的列表
		List fileList = null;
		try {
			fileList = sfu.parseRequest(request);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				out.println("文件尺寸超过规定大小:" + MAX_SIZE + "字节<p />");
				out.println("请重新操作，选择上传文件<p />");
				//out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">返回</a>");
				//out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM>");
				return;
			}
			e.printStackTrace();
		}
		// 得到所有上传的文件
		Iterator fileItr = fileList.iterator();
		//定义用户ID

		//循环处理所有文件
		while (fileItr.hasNext()) {
			FileItem fileItem = null;
			String path = null;
			long size = 0;
			// 得到当前文件
			fileItem = (FileItem) fileItr.next();
			
			if(fileItem == null || fileItem.isFormField()){
	         // 如果item是正常的表单域
	               String name1 = fileItem.getFieldName();
	               String value = fileItem.getString("UTF-8");
			      System.out.print("表单域名为:"+name1+"表单域值为:"+value);
			}
	        else{
	          // 如果item是文件上传表单域
	                        
	        	//得到文件的完整路径
				path = fileItem.getName();				
				// 得到文件的大小
				size = fileItem.getSize();
				if ("".equals(path) || size == 0) {
					out.println("请重新操作，选择上传文件<p />");
//					out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">返回</a>");
					//out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
					return;
				}

				// 得到去除路径的文件名
				String t_name = path.substring(path.lastIndexOf("\\") + 1);
				// 得到文件的扩展名(无扩展名时将得到全名)
				String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
				// 拒绝接受规定文件格式之外的文件类型
				int allowFlag = 0;
				int allowedExtCount = allowedExt.length;
				for (; allowFlag < allowedExtCount; allowFlag++) {
					if (allowedExt[allowFlag].equals(t_ext))
						break;
				}
				if (allowFlag == allowedExtCount) {
					out.println("请重新操作，上传以下类型的文件<p />");
					for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
						out.println("*." + allowedExt[allowFlag]
								+ "&nbsp;&nbsp;&nbsp;");
//					out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">返回</a>");
					//out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
					return;
				}

				Uuid uuid=new Uuid();
				Uuid uu=uuid.create();
				String now = uu.toString();
				// 根据系统时间生成上传后保存的文件名
				String prefix = String.valueOf(now);
				// 根据上传上来的文件名命名（userid+客户端的当前时间）,如果上传文件名不为空，则保存文件
				
								 
				    // 保存的最终文件完整路径,保存在web根目录下的ImagesUploaded目录下
					System.out.println(request.getRealPath("/"));
					String u_name = request.getRealPath("/") + "imagesurl/"
							+ prefix + "." + t_ext;
					try {
					    
						// 保存文件
						fileItem.write(new File(u_name));			        
						out.println("文件上传成功. 已保存为: " + prefix + "." + t_ext
								+ " &nbsp;&nbsp;文件大小: " + size + "字节<p />");
						//out.println("<a href=\"example/commonsfileupload/upload.html\" target=\"_top\">继续上传</a>");
												
				System.out.print("mmmmmmmm"+u_name);
						Service src=new Service();	
						src.dopost("ImagesUploaded/"+prefix + "." + t_ext);
						//out.println("<FORM> <INPUT TYPE='BUTTON' VALUE='关闭返回' onClick='window.close()'> </FORM> ");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
	                    }

		
		
		
		Service src=new Service(); 
		src.post();
		response.sendRedirect("list.jsp");
}
	*/

			
		}
         



