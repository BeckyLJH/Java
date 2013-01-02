package com.leec.lmodules_article.util.path;

/**
 * 
 * @author Leeyn
 * 
 * 注意，ava application模式运行与web application模式运行，
 * src下面目录的访问路径是不一样的，在web下没有src这个目录，
 * 算是web-inf这个目录
 *
 */
public class GetRealPath {
    /**
     * F:/XuJingJava/pjbq_etax/WebRoot
     * F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax
     * @return
     */
    
    public static String getWebRootUrl() {
	
	String rtn ="";
	
	java.io.File file = new java.io.File(GetRealPath.class.getResource("/")
		.getFile());	
	//new多一次就是上多一级目录
	file = new java.io.File(file.getParent());
	String str1=file.getParent();
	//工程根目录pjbq_etax路径(这个斜杠程序是不认的，也无法传值)F:\JavaSoft\tomcat-5.5.12\webapps\pjbq_etax
	//System.out.println(str1);
//	将字符串中的反斜杠("\")替换成双反斜杠("/") 
	rtn=str1.replaceAll("\\\\","/");     
	//工程根目录pjbq_etax路径F:/XuJingJava/pjbq_etax/WebRoot
        //System.out.println(str2);	
	return rtn;
	//使用参考
//	String path2=GetRealPath.getWebRootUrl();//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax
//	 System.out.println(path2+"/pjbq/certs/");//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/pjbq/certs/
    }
   /**
    * 取得src、WEB-INF目录路径，如果Java Application 运行，参数为"Src"，如果Java Web 运行，参数为"WebInf"。
    * @param SrcOrWebInf
    * @return 
    * Src——
    *      	F:/XuJingJava/pjbq_etax/src 
    *      	在web程序中不能用这个参数，否则返回错误路径F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/src
    * WebInf——
    *           F:/XuJingJava/pjbq_etax/WebRoot/WEB-INF     
    *           F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/WEB-INF
    */
    public static String getWebInfUrl() {
	
		String rtn="";
		java.io.File file = new java.io.File(GetRealPath.class.getResource("/")
			.getFile());	
		String str1=file.getParent();
//		将字符串中的反斜杠("\")替换成双反斜杠("/") 
		rtn=str1.replaceAll("\\\\","/");  
		return rtn;
	//使用参考
	//	String path2=GetRealPath.getWebRootUrl();//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax
	//	 System.out.println(path2+"/pjbq/certs/");//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/pjbq/certs/
    }    
    /**
     * 取得src、WEB-INF目录路径，如果Java Application 运行，参数为"Src"，如果Java Web 运行，参数为"Classes"。
     * @param SrcOrWebInf
     * @return 
     * Src——
     *      	F:/XuJingJava/pjbq_etax/src 
     *      	在web程序中不能用这个参数，否则返回错误路径F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/src
     * WebInf——
     *           F:/XuJingJava/pjbq_etax/WebRoot/WEB-INF     
     *           F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/WEB-INF
     */
    public static String getSrcOrClassesUrl(String SrcOrClasses) {
	
	String rtn ="您传进来的参数"+SrcOrClasses+"有误，getSrcOrWebInfUrl(String SrcOrWebInf)正确参数应该为Src/WebInf";
	if(SrcOrClasses.equals("Src")){
	        java.io.File file = new java.io.File(GetRealPath.class.getResource("/")
			.getFile());	
		file = new java.io.File(file.getParent()); 
		file = new java.io.File(file.getParent()); 
		String str1=file.getParent();
		//将字符串中的反斜杠("\")替换成双反斜杠("/") 
		rtn=str1.replaceAll("\\\\","/")+"/src";     
		return rtn;
	}
	if(SrcOrClasses.equals("Classes")){	    
		
		java.io.File file = new java.io.File(GetRealPath.class.getResource("/")
			.getFile());	
		String str1=file.getParent();
//		将字符串中的反斜杠("\")替换成双反斜杠("/") 
		rtn=str1.replaceAll("\\\\","/")+"/classes";  
		return rtn;
	}
	System.out.println(rtn);
	return "";
	//使用参考
	//	String path2=GetRealPath.getWebRootUrl();//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax
	//	 System.out.println(path2+"/pjbq/certs/");//F:/JavaSoft/tomcat-5.5.12/webapps/pjbq_etax/pjbq/certs/
    }   
    
    public static void main(String args[]) {

	java.io.File file = new java.io.File(GetRealPath.class.getResource("/")
		.getFile());
	
	//类目录WEB-INF路径 F:\XuJingJava\pjbq_etax\WebRoot\WEB-INF
	System.out.println(file.getParent());
	
	//new多一次就是上多一级目录
	file = new java.io.File(file.getParent());
	String str1=file.getParent();
	//工程根目录pjbq_etax路径(这个斜杠程序是不认的，也无法传值)F:\JavaSoft\tomcat-5.5.12\webapps\pjbq_etax
	//F:\XuJingJava\pjbq_etax\WebRoot
	System.out.println(str1);
	
//	将字符串中的反斜杠("\")替换成双反斜杠("/") 
	String str2=str1.replaceAll("\\\\","/");     
	//工程根目录pjbq_etax路径F:/XuJingJava/pjbq_etax/WebRoot
        System.out.println(str2);
        
	file = new java.io.File(file.getParent()); 
	//网站根目录F:\XuJingJava\pjbq_etax
	System.out.println(file.getParent());
	
	//file:/F:/XuJingJava/pjbq_etax/WebRoot/WEB-INF/classes/com/leec/pjbq/utils/
	System.out.println(GetRealPath.class.getResource(""));

	//file:/F:/XuJingJava/pjbq_etax/WebRoot/WEB-INF/classes/
	System.out.println(GetRealPath.class.getResource("/"));

	//file:/F:/XuJingJava/pjbq_etax/WebRoot/WEB-INF/classes/
	System.out.println(Thread.currentThread().getContextClassLoader()
		.getResource(""));
	/*//在web中（servlet/jsp）
	ServletContext context = getServletContext();
	 String  path = context.getRealPath("/") + "pjbq/certs/";
	 path=path.replaceAll("\\\\","/");
	 System.out.println(path);
	*/
    }

}
