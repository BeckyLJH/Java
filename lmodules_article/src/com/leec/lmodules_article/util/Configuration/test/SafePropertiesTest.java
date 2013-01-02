package com.leec.lmodules_article.util.Configuration.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.leec.lmodules_article.util.Configuration.SafeProperties;

public class SafePropertiesTest{
 public static void main(String[] args) throws FileNotFoundException,IOException { 
 	   File f=new File("test.properties"); 
	   FileInputStream input = new FileInputStream(f);
       SafeProperties safeProp = new SafeProperties();
       safeProp.load(input);
       input.close();
       
       /*官方Propertise类的getProperty方法，如配置值是中文字符譬如"广州理氏信息科技
         有限公司-OA系统"，读回来的会是乱码"??????"，因此，手写配置项，务必将配置值
         用工具转换编码，譬如"\u674E\u8FDC\u5FF5"，再写在配置文件中(SafeProperties的
         put方法则可直接自动转换保存到配置文件)*/
       System.out.println(safeProp.getProperty("systemName"));
       
	   /*目前SafeProperties未能写入中文字符，不过，在这里写入中文字符也没什么意义，配置文件本
	     来就是需要手动配置，然后提供给程序读取配置信息的*/
       //safeProp.addComment("以下是系统管理员信息");
       //safeProp.addComment("New Comment");
       
       //put的方法会自动将中文字符"李远念"转换编码为"\u674E\u8FDC\u5FF5"保存到配置文件
       safeProp.put("admin", "钟小燕");
       //保存进去的是"\u674E\u8FDC\u5FF5"，官方Propertise类的getProperty方法就可读出中文
       System.out.println(safeProp.getProperty("admin"));
       FileOutputStream output = new FileOutputStream(f);
       safeProp.store(output, null);
       output.close();
}

} 

