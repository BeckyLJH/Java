package com.leec.lmodules_article.model.DTO;
/** 
*kingbill 2006 3.21 all rights reserved 
*王俊标  strutsbook@126.com  
*数据库驱动直接连接 测试0.8版 
*/  
/* 

*/  
//lmo_article_filesDTO++++++++++++++++++++++++++++++++++++++++++++++++++
  public class Lmo_article_filesDTO implements java.io.Serializable
  {
  //fields------------------------------------------------
  private java.lang.String file_id;
  private java.lang.String article_id;
  private java.lang.String file_path;
  private java.lang.String file_title;
  private java.lang.String file_desc;
//setter--------------------------------

  public void setFile_id(java.lang.String _file_id)
  {
    this.file_id = _file_id;
  }

  public void setArticle_id(java.lang.String _article_id)
  {
    this.article_id = _article_id;
  }

  public void setFile_path(java.lang.String _file_path)
  {
    this.file_path = _file_path;
  }

  public void setFile_title(java.lang.String _file_title)
  {
    this.file_title = _file_title;
  }

  public void setFile_desc(java.lang.String _file_desc)
  {
    this.file_desc = _file_desc;
  }
//getter--------------------------------

  public java.lang.String getFile_id()
  {
    return this.file_id;
  }

  public java.lang.String getArticle_id()
  {
    return this.article_id;
  }

  public java.lang.String getFile_path()
  {
    return this.file_path;
  }

  public java.lang.String getFile_title()
  {
    return this.file_title;
  }

  public java.lang.String getFile_desc()
  {
    return this.file_desc;
  }
//toString--------------------------------

  public String toString()
  {
  String s=new String();
  s = s 
     + " file_id:"
     + this.file_id.toString()
     + " article_id:"
     + this.article_id.toString()
     + " file_path:"
     + this.file_path.toString()
     + " file_title:"
     + this.file_title.toString()
     + " file_desc:"
     + this.file_desc.toString();
  return s;
  }
  }
