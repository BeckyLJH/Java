package com.leec.lmodules_article.util.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.Vector;
public class Configuration{    
	private File f;
    private SafeProperties safeProp;
    private FileInputStream inputFile;
    private FileOutputStream outputFile; 

	/** *//**
     * 初始化Configuration类
     * @param filePath 要读取的配置文件的路径+名称
     */
    public Configuration(String filePath)
    {
    	f=new File(filePath);
        safeProp = new SafeProperties();
        try {
            inputFile = new FileInputStream(filePath);
            safeProp.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }//end ReadConfigInfo()
	 /**
	  * 读取配置项
	  * @param key-属性名称
	  * @param method-web or aplication
	  * @return 属性值
	  */
	 public String findProperty(String key) {	  
	   return ISO2GB(safeProp.getProperty(key));//一般的配置文件是iso2的编码格式,将他转换为gb2312	  
	 }	
	 /**
	  * 设置配置文件
	  * @param key-属性名称
	  * @param value-属性值
	  */
	 public void saveProperty(String key, String value) {	  
	  try{
	  	  value = replaceBacklashToDouble(value);
		  safeProp.put(key, value);
		  outputFile = new FileOutputStream(f);
	      safeProp.store(outputFile, null);
	      outputFile.close();
	  }catch (FileNotFoundException ex) {
            System.out.println("写入属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
	  
	 }
	 
	/**
	 * @param string
	 */
	 public void addComment(String comment) {	  
	  	safeProp.addComment(comment);
	 }
	
	 /**   
	  *   将ISO-8859-1字符编码转为GB2312字符编码   
	  *   @param   source   要进行转码的字符串   
	  *   @return   转码后的字符串   
	  */
	 private String ISO2GB(String source) {
	  if (source == null || source.length() == 0) {
	   return "";
	  }
	
	  String target = source;
	  try {
	   target = new String(source.getBytes("8859_1"), "GB2312");
	  } catch (Exception e) {
	   e.printStackTrace();
	   System.out.println("由ISO-8859-1到GB2312转码失败！");
	  }
	  return target;
	 }
	
	 /**   
	  *   将ISO-8859-1字符编码转为UTF-8字符编码   
	  *   @param   source   要进行转码的字符串   
	  *   @return   转码后的字符串   
	  */
	 private String ISO2UTF8(String source) {
	  if (source == null || source.length() == 0) {
	   return "";
	  }
	
	  String target = source;
	  try {
	   target = new String(source.getBytes("8859_1"), "UTF-8");
	  } catch (Exception e) {
	   e.printStackTrace();
	   System.out.println("由ISO-8859-1到UTF-8转码失败！");
	  }
	  return target;
	 }
	 
	 /**   
	  *   将字符串中的反斜杠("\")替换成双反斜杠("\\")   
	  *   @param   pStr   需要替换的字符串   
	  *   @return   替换后的字符串   
	  */
	 private String replaceBacklashToDouble(String pStr) {
	  if (pStr.length() < 1) {
	   return "";
	  }
	  StringTokenizer stk = new StringTokenizer(pStr, "\\", true);
	  Vector v = new Vector();
	  while (stk.hasMoreTokens()) {
	   v.addElement(stk.nextToken());
	  }
	
	  String strResult = v.get(0).toString();
	  for (int i = 1; i < v.size(); i++) {
	   if (v.get(i).toString().trim().equals("\\")) {
	    strResult += "\\" + v.get(i).toString();
	   } else {
	    strResult += v.get(i).toString();
	   }
	  }
	  return strResult;
	 }
}
