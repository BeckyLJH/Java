package com.leec.lmodules_article.model.DTO;
/** 
*kingbill 2006 3.21 all rights reserved 
*王俊标  strutsbook@126.com  
*数据库驱动直接连接 测试0.8版 
*/  
/* 

*/  
//lmo_article_adminDTO++++++++++++++++++++++++++++++++++++++++++++++++++
  public class Lmo_article_adminDTO implements java.io.Serializable
  {
  //fields------------------------------------------------
  private java.lang.String admin_id;
  private java.lang.String admin_username;
  private java.lang.String admin_pass;
//setter--------------------------------

  public void setAdmin_id(java.lang.String _admin_id)
  {
    this.admin_id = _admin_id;
  }

  public void setAdmin_username(java.lang.String _admin_username)
  {
    this.admin_username = _admin_username;
  }

  public void setAdmin_pass(java.lang.String _admin_pass)
  {
    this.admin_pass = _admin_pass;
  }
//getter--------------------------------

  public java.lang.String getAdmin_id()
  {
    return this.admin_id;
  }

  public java.lang.String getAdmin_username()
  {
    return this.admin_username;
  }

  public java.lang.String getAdmin_pass()
  {
    return this.admin_pass;
  }
//toString--------------------------------

  public String toString()
  {
  String s=new String();
  s = s 
     + " admin_id:"
     + this.admin_id.toString()
     + " admin_username:"
     + this.admin_username.toString()
     + " admin_pass:"
     + this.admin_pass.toString();
  return s;
  }
  }
