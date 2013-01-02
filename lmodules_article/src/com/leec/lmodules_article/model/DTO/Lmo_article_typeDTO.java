package com.leec.lmodules_article.model.DTO;
/** 
*kingbill 2006 3.21 all rights reserved 
*���  strutsbook@126.com  
*��ݿ���ֱ��l�� ����0.8�� 
*/  
/* 

*/  
//lmo_article_typeDTO++++++++++++++++++++++++++++++++++++++++++++++++++
  public class Lmo_article_typeDTO implements java.io.Serializable
  {
  //fields------------------------------------------------
  private java.lang.String type_id;
  private java.lang.String type_name;
  private java.lang.Integer type_order;
  private java.lang.String type_desc;
  private java.lang.String type_father;
//setter--------------------------------

  public void setType_id(java.lang.String _type_id)
  {
    this.type_id = _type_id;
  }

  public void setType_name(java.lang.String _type_name)
  {
    this.type_name = _type_name;
  }

  public void setType_order(java.lang.Integer _type_order)
  {
    this.type_order = _type_order;
  }

  public void setType_desc(java.lang.String _type_desc)
  {
    this.type_desc = _type_desc;
  }

  public void setType_father(java.lang.String _type_father)
  {
    this.type_father = _type_father;
  }
//getter--------------------------------

  public java.lang.String getType_id()
  {
    return this.type_id;
  }

  public java.lang.String getType_name()
  {
    return this.type_name;
  }

  public java.lang.Integer getType_order()
  {
    return this.type_order;
  }

  public java.lang.String getType_desc()
  {
    return this.type_desc;
  }

  public java.lang.String getType_father()
  {
    return this.type_father;
  }
//toString--------------------------------

  public String toString()
  {
  String s=new String();
  s = s 
     + " type_id:"
     + this.type_id.toString()
     + " type_name:"
     + this.type_name.toString()
     + " type_order:"
     + this.type_order.toString()
     + " type_desc:"
     + this.type_desc.toString()
     + " type_father:"
     + this.type_father.toString();
  return s;
  }
  }
