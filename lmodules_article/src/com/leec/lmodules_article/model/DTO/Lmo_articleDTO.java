package com.leec.lmodules_article.model.DTO;
/** 
*kingbill 2006 3.21 all rights reserved 
*王俊标  strutsbook@126.com  
*数据库驱动直接连接 测试0.8版 
*/  
/* 

*/  
//lmo_articleDTO++++++++++++++++++++++++++++++++++++++++++++++++++
  public class Lmo_articleDTO implements java.io.Serializable
  {
  //fields------------------------------------------------
  private java.lang.String article_id;
  private java.lang.String article_type_id;
  private java.lang.String article_title;
  private java.lang.String article_keys;
  private java.lang.String article_from;
  private java.lang.String article_intro;
  private java.lang.String article_zuozhe;
  private java.lang.String article_content;
  private java.lang.Integer article_is_tuwen;
  private java.lang.String article_picture;
  private java.lang.Integer article_is_link;
  private java.lang.String article_link_url;
  private java.lang.Integer article_is_tuijian;
  private java.lang.Integer article_hits;
  private java.sql.Timestamp article_fabu_time;
  private java.lang.Integer article_paixu;
//setter--------------------------------

  public void setArticle_id(java.lang.String _article_id)
  {
    this.article_id = _article_id;
  }

  public void setArticle_type_id(java.lang.String _article_type_id)
  {
    this.article_type_id = _article_type_id;
  }

  public void setArticle_title(java.lang.String _article_title)
  {
    this.article_title = _article_title;
  }

  public void setArticle_keys(java.lang.String _article_keys)
  {
    this.article_keys = _article_keys;
  }

  public void setArticle_from(java.lang.String _article_from)
  {
    this.article_from = _article_from;
  }

  public void setArticle_intro(java.lang.String _article_intro)
  {
    this.article_intro = _article_intro;
  }

  public void setArticle_zuozhe(java.lang.String _article_zuozhe)
  {
    this.article_zuozhe = _article_zuozhe;
  }

  public void setArticle_content(java.lang.String _article_content)
  {
    this.article_content = _article_content;
  }

  public void setArticle_is_tuwen(java.lang.Integer _article_is_tuwen)
  {
    this.article_is_tuwen = _article_is_tuwen;
  }

  public void setArticle_picture(java.lang.String _article_picture)
  {
    this.article_picture = _article_picture;
  }

  public void setArticle_is_link(java.lang.Integer _article_is_link)
  {
    this.article_is_link = _article_is_link;
  }

  public void setArticle_link_url(java.lang.String _article_link_url)
  {
    this.article_link_url = _article_link_url;
  }

  public void setArticle_is_tuijian(java.lang.Integer _article_is_tuijian)
  {
    this.article_is_tuijian = _article_is_tuijian;
  }

  public void setArticle_hits(java.lang.Integer _article_hits)
  {
    this.article_hits = _article_hits;
  }

  public void setArticle_fabu_time(java.sql.Timestamp _article_fabu_time)
  {
    this.article_fabu_time = _article_fabu_time;
  }

  public void setArticle_paixu(java.lang.Integer _article_paixu)
  {
    this.article_paixu = _article_paixu;
  }
//getter--------------------------------

  public java.lang.String getArticle_id()
  {
    return this.article_id;
  }

  public java.lang.String getArticle_type_id()
  {
    return this.article_type_id;
  }

  public java.lang.String getArticle_title()
  {
    return this.article_title;
  }

  public java.lang.String getArticle_keys()
  {
    return this.article_keys;
  }

  public java.lang.String getArticle_from()
  {
    return this.article_from;
  }

  public java.lang.String getArticle_intro()
  {
    return this.article_intro;
  }

  public java.lang.String getArticle_zuozhe()
  {
    return this.article_zuozhe;
  }

  public java.lang.String getArticle_content()
  {
    return this.article_content;
  }

  public java.lang.Integer getArticle_is_tuwen()
  {
    return this.article_is_tuwen;
  }

  public java.lang.String getArticle_picture()
  {
    return this.article_picture;
  }

  public java.lang.Integer getArticle_is_link()
  {
    return this.article_is_link;
  }

  public java.lang.String getArticle_link_url()
  {
    return this.article_link_url;
  }

  public java.lang.Integer getArticle_is_tuijian()
  {
    return this.article_is_tuijian;
  }

  public java.lang.Integer getArticle_hits()
  {
    return this.article_hits;
  }

  public java.sql.Timestamp getArticle_fabu_time()
  {
    return this.article_fabu_time;
  }

  public java.lang.Integer getArticle_paixu()
  {
    return this.article_paixu;
  }
//toString--------------------------------

  public String toString()
  {
  String s=new String();
  s = s 
     + " article_id:"
     + this.article_id.toString()
     + " article_type_id:"
     + this.article_type_id.toString()
     + " article_title:"
     + this.article_title.toString()
     + " article_keys:"
     + this.article_keys.toString()
     + " article_from:"
     + this.article_from.toString()
     + " article_intro:"
     + this.article_intro.toString()
     + " article_zuozhe:"
     + this.article_zuozhe.toString()
     + " article_content:"
     + this.article_content.toString()
     + " article_is_tuwen:"
     + this.article_is_tuwen.toString()
     + " article_picture:"
     + this.article_picture.toString()
     + " article_is_link:"
     + this.article_is_link.toString()
     + " article_link_url:"
     + this.article_link_url.toString()
     + " article_is_tuijian:"
     + this.article_is_tuijian.toString()
     + " article_hits:"
     + this.article_hits.toString()
     + " article_fabu_time:"
     + this.article_fabu_time.toString()
     + " article_paixu:"
     + this.article_paixu.toString();
  return s;
  }
  }
