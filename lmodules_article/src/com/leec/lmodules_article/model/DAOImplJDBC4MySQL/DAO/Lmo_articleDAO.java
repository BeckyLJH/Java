package com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO;

  import java.sql.*;
  import java.util.*;

import com.leec.lmodules_article.model.DAOIfc.Lmo_articleDAOIfc;
import com.leec.lmodules_article.model.DTO.*;
import com.leec.lmodules_article.util.Configuration.ConfigurationStaticFinal_lxfxy;
/** 
*kingbill 2006 3.21 all rights reserved 
*���  strutsbook@126.com 
*��ݿ���ֱ��l�� ����0.8�� 
*/  
/* 

*/  
//lmo_articleDAO++++++++++++++++++++++++++++++++++++++++++++++++++

public class Lmo_articleDAO implements Lmo_articleDAOIfc {
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#createLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void createLmo_article(Lmo_articleDTO lmo_article)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql="insert into lmo_article (article_id,article_type_id,article_title,article_keys,article_from,article_intro,article_zuozhe,article_content,article_is_tuwen,article_picture,article_is_link,article_link_url,article_is_tuijian,article_hits,article_fabu_time,article_paixu) values ("
        + "'" + lmo_article.getArticle_id() + "'" + ","
        + "'" + lmo_article.getArticle_type_id() + "'" + ","
        + "'" + lmo_article.getArticle_title() + "'" + ","
        + "'" + lmo_article.getArticle_keys() + "'" + ","
        + "'" + lmo_article.getArticle_from() + "'" + ","
        + "'" + lmo_article.getArticle_intro() + "'" + ","
        + "'" + lmo_article.getArticle_zuozhe() + "'" + ","
        + "'" + lmo_article.getArticle_content() + "'" + ","
        +       lmo_article.getArticle_is_tuwen() + ","
        + "'" + lmo_article.getArticle_picture() + "'" + ","
        +       lmo_article.getArticle_is_link() + ","
        + "'" + lmo_article.getArticle_link_url() + "'" + ","
        +       lmo_article.getArticle_is_tuijian() + ","
        +       lmo_article.getArticle_hits() + ","
        + "'" + lmo_article.getArticle_fabu_time() + "'" + ","
        +       lmo_article.getArticle_paixu()
        + ")";

    stmt = con.createStatement();
    stmt.execute(sql);

    }
    catch(Exception sqlex1)
    {
    sqlex1.printStackTrace();
    }
    finally
    {
    if (con != null) { 
    try {
         con.close();
    }
    catch(SQLException sqlex2) 
    {
    sqlex2.printStackTrace();
    }
    }
    }
  }

//UpdateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#updateLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void updateLmo_article(Lmo_articleDTO lmo_article)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "update lmo_article set "
        + "article_type_id ='" + lmo_article.getArticle_type_id() + "',"
        + "article_title ='" + lmo_article.getArticle_title() + "',"
        + "article_keys ='" + lmo_article.getArticle_keys() + "',"
        + "article_from ='" + lmo_article.getArticle_from() + "',"
        + "article_intro ='" + lmo_article.getArticle_intro() + "',"
        + "article_zuozhe ='" + lmo_article.getArticle_zuozhe() + "',"
        + "article_content ='" + lmo_article.getArticle_content() + "',"
        + "article_is_tuwen =" +lmo_article.getArticle_is_tuwen() + ","
        + "article_picture ='" + lmo_article.getArticle_picture() + "',"
        + "article_is_link =" +lmo_article.getArticle_is_link() + ","
        + "article_link_url ='" + lmo_article.getArticle_link_url() + "',"
        + "article_is_tuijian =" +lmo_article.getArticle_is_tuijian() + ","
        + "article_hits =" +lmo_article.getArticle_hits() + ","
        + "article_fabu_time ='" + lmo_article.getArticle_fabu_time() + "',"
        + "article_paixu =" + lmo_article.getArticle_paixu()
        + " where article_id = '" + lmo_article.getArticle_id() + "'"
        ;
    stmt = con.createStatement();
    stmt.executeUpdate(sql);

    }
    catch(Exception sqlex1)
    {
    sqlex1.printStackTrace();
    }
    finally
    {
    if (con != null) { 
    try {
         con.close();
    }
    catch(SQLException sqlex2) 
    {
    sqlex2.printStackTrace();
    }
    }
    }
  }
//UpdateByDTOs------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#updateLmo_articles(java.util.Vector)
 */
public void updateLmo_articles(Vector lmo_articles)
  {
      Iterator it = lmo_articles.iterator();
      while(it.hasNext())
      {
        Lmo_articleDTO lmo_article = (Lmo_articleDTO)it.next();
        updateLmo_article(lmo_article);
      }
  }
//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _article_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "delete from lmo_article where article_id = '" + _article_id + "'";
    stmt = con.createStatement();
    stmt.execute(sql);

    }
    catch(Exception sqlex1)
    {
    sqlex1.printStackTrace();
    }
    finally
    {
    if (con != null) { 
    try {
         con.close();
    }
    catch(SQLException sqlex2) 
    {
    sqlex2.printStackTrace();
    }
    }
    }
  }
//RemoveByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#removeLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void removeLmo_article(Lmo_articleDTO lmo_article)
  {
  removeByPrimaryKey(lmo_article.getArticle_id());
  }
//RemoveByDTOs------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#removeLmo_articles(java.util.Vector)
 */
public void removeLmo_articles(Vector lmo_articles)
  {
      Iterator it = lmo_articles.iterator();
      while(it.hasNext())
      {
        Lmo_articleDTO lmo_article = (Lmo_articleDTO)it.next();
        removeLmo_article(lmo_article);
      }
  }
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_articleDTO findByPrimaryKey(java.lang.String _article_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    Lmo_articleDTO lmo_article = new Lmo_articleDTO();
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    stmt = con.createStatement();
    String sql = "select * from lmo_article where article_id = '" + _article_id + "'";
    rs = stmt.executeQuery(sql);
    while(rs.next())
    {
      lmo_article.setArticle_id(rs.getString("article_id"));
      lmo_article.setArticle_type_id(rs.getString("article_type_id"));
      lmo_article.setArticle_title(rs.getString("article_title"));
      lmo_article.setArticle_keys(rs.getString("article_keys"));
      lmo_article.setArticle_from(rs.getString("article_from"));
      lmo_article.setArticle_intro(rs.getString("article_intro"));
      lmo_article.setArticle_zuozhe(rs.getString("article_zuozhe"));
      lmo_article.setArticle_content(rs.getString("article_content"));
      lmo_article.setArticle_is_tuwen(new Integer(rs.getInt("article_is_tuwen")));
      lmo_article.setArticle_picture(rs.getString("article_picture"));
      lmo_article.setArticle_is_link(new Integer(rs.getInt("article_is_link")));
      lmo_article.setArticle_link_url(rs.getString("article_link_url"));
      lmo_article.setArticle_is_tuijian(new Integer(rs.getInt("article_is_tuijian")));
      lmo_article.setArticle_hits(new Integer(rs.getInt("article_hits")));
      lmo_article.setArticle_fabu_time(rs.getTimestamp("article_fabu_time"));
      lmo_article.setArticle_paixu(new Integer(rs.getInt("article_paixu")));
    }

    }
    catch(Exception sqlex1)
    {
    sqlex1.printStackTrace();
    }
    finally
    {
    if (con != null) { 
    try {
         con.close();
    }
    catch(SQLException sqlex2) 
    {
    sqlex2.printStackTrace();
    }
    }
    return lmo_article;
  }
  }

//findByArticle_type_id------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_articleDTO> findByArticle_type_id(java.lang.String _article_type_id)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  
  List<Lmo_articleDTO> ls=new ArrayList<Lmo_articleDTO>();
  
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article where article_type_id = '" + _article_type_id + "'";
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
	Lmo_articleDTO lmo_article = new Lmo_articleDTO(); 
    lmo_article.setArticle_id(rs.getString("article_id"));
    lmo_article.setArticle_type_id(rs.getString("article_type_id"));
    lmo_article.setArticle_title(rs.getString("article_title"));
    lmo_article.setArticle_keys(rs.getString("article_keys"));
    lmo_article.setArticle_from(rs.getString("article_from"));
    lmo_article.setArticle_intro(rs.getString("article_intro"));
    lmo_article.setArticle_zuozhe(rs.getString("article_zuozhe"));
    lmo_article.setArticle_content(rs.getString("article_content"));
    //lmo_article.setArticle_is_tuwen(rs.getString("type_father"));
    lmo_article.setArticle_picture(rs.getString("article_picture"));
    //lmo_article.setArticle_is_link(rs.getString("type_father"));
    lmo_article.setArticle_link_url(rs.getString("article_link_url"));
    lmo_article.setArticle_is_tuijian(new Integer(rs.getInt("article_is_tuijian")));
    lmo_article.setArticle_hits(new Integer(rs.getInt("article_hits")));
    lmo_article.setArticle_fabu_time(rs.getTimestamp("article_fabu_time"));
    lmo_article.setArticle_paixu(new Integer(rs.getInt("article_paixu")));
    ls.add(lmo_article);
  }
  }
  catch(Exception sqlex1)
  {
  sqlex1.printStackTrace();
  }
  finally
  {
  if (con != null) { 
  try {
       con.close();
  }
  catch(SQLException sqlex2) 
  {
  sqlex2.printStackTrace();
  }
  }
  return ls;
}
}

public List<Lmo_articleDTO> findByPrimaryKey2(java.lang.String _article_id)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  Lmo_articleDTO lmo_article = new Lmo_articleDTO();
  List<Lmo_articleDTO> ls=new ArrayList<Lmo_articleDTO>();
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article where article_id = '" + _article_id + "'";
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
    lmo_article.setArticle_id(rs.getString("article_id"));
    lmo_article.setArticle_type_id(rs.getString("article_type_id"));
    lmo_article.setArticle_title(rs.getString("article_title"));
    lmo_article.setArticle_keys(rs.getString("article_keys"));
    lmo_article.setArticle_from(rs.getString("article_from"));
    lmo_article.setArticle_intro(rs.getString("article_intro"));
    lmo_article.setArticle_zuozhe(rs.getString("article_zuozhe"));
    lmo_article.setArticle_content(rs.getString("article_content"));
    lmo_article.setArticle_is_tuwen(new Integer(rs.getInt("article_is_tuwen")));
    lmo_article.setArticle_picture(rs.getString("article_picture"));
    lmo_article.setArticle_is_link(new Integer(rs.getInt("article_is_link")));
    lmo_article.setArticle_link_url(rs.getString("article_link_url"));
    lmo_article.setArticle_is_tuijian(new Integer(rs.getInt("article_is_tuijian")));
    lmo_article.setArticle_hits(new Integer(rs.getInt("article_hits")));
    lmo_article.setArticle_fabu_time(rs.getTimestamp("article_fabu_time"));
    lmo_article.setArticle_paixu(new Integer(rs.getInt("article_paixu")));
    ls.add(lmo_article);
  }

  }
  catch(Exception sqlex1)
  {
  sqlex1.printStackTrace();
  }
  finally
  {
  if (con != null) { 
  try {
       con.close();
  }
  catch(SQLException sqlex2) 
  {
  sqlex2.printStackTrace();
  }
  }
  return ls;
}
}


  }
