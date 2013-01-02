package com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO;

  import java.sql.*;
  import java.util.*;

import com.leec.lmodules_article.model.DAOIfc.Lmo_article_filesDAOIfc;
import com.leec.lmodules_article.model.DTO.*;
import com.leec.lmodules_article.util.Configuration.ConfigurationStaticFinal_lxfxy;
/** 
*kingbill 2006 3.21 all rights reserved 
*���  strutsbook@126.com 
*��ݿ���ֱ��l�� ����0.8�� 
*/  
/* 

*/  
//lmo_article_filesDAO++++++++++++++++++++++++++++++++++++++++++++++++++

public class Lmo_article_filesDAO implements Lmo_article_filesDAOIfc {
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#createLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void createLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql="insert into lmo_article_files (file_id,article_id,file_path,file_title,file_desc) values ("
        + "'" + lmo_article_files.getFile_id() + "'" + ","
        + "'" + lmo_article_files.getArticle_id() + "'" + ","
        + "'" + lmo_article_files.getFile_path() + "'" + ","
        + "'" + lmo_article_files.getFile_title() + "'" + ","
        + "'" + lmo_article_files.getFile_desc() + "'"
    +")";

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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#updateLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void updateLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "update lmo_article_files set "
        + "article_id ='" + lmo_article_files.getArticle_id() + "',"
        + "file_path ='" + lmo_article_files.getFile_path() + "',"
        + "file_title ='" + lmo_article_files.getFile_title() + "',"
        + "file_desc ='" + lmo_article_files.getFile_desc() + "'"
        + " where file_id = '" + lmo_article_files.getFile_id() + "'"
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#updateLmo_article_filess(java.util.Vector)
 */
public void updateLmo_article_filess(Vector lmo_article_filess)
  {
      Iterator it = lmo_article_filess.iterator();
      while(it.hasNext())
      {
        Lmo_article_filesDTO lmo_article_files = (Lmo_article_filesDTO)it.next();
        updateLmo_article_files(lmo_article_files);
      }
  }
//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _file_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "delete from lmo_article_files where file_id = '" + _file_id + "'";
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void removeLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
  removeByPrimaryKey(lmo_article_files.getFile_id());
  }
//RemoveByDTOs------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeLmo_article_filess(java.util.Vector)
 */
public void removeLmo_article_filess(Vector lmo_article_filess)
  {
      Iterator it = lmo_article_filess.iterator();
      while(it.hasNext())
      {
        Lmo_article_filesDTO lmo_article_files = (Lmo_article_filesDTO)it.next();
        removeLmo_article_files(lmo_article_files);
      }
  }
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_filesDTO findByPrimaryKey(java.lang.String _file_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    Lmo_article_filesDTO lmo_article_files = new Lmo_article_filesDTO();
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    stmt = con.createStatement();
    String sql = "select * from lmo_article_files where file_id = '" + _file_id + "'";
    rs = stmt.executeQuery(sql);
    while(rs.next())
    {
      lmo_article_files.setFile_id(rs.getString("file_id"));
      lmo_article_files.setArticle_id(rs.getString("article_id"));
      lmo_article_files.setFile_path(rs.getString("file_path"));
      lmo_article_files.setFile_title(rs.getString("file_title"));
      lmo_article_files.setFile_desc(rs.getString("file_desc"));
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
    return lmo_article_files;
  }
  }

//findByArticle_id------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_filesDTO> findByArticle_id(java.lang.String _article_id)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  
  List<Lmo_article_filesDTO> ls=new ArrayList<Lmo_article_filesDTO>();
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article_files where article_id = '" + _article_id + "'";
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
	  Lmo_article_filesDTO lmo_article_files = new Lmo_article_filesDTO();  
    lmo_article_files.setFile_id(rs.getString("file_id"));
    lmo_article_files.setArticle_id(rs.getString("article_id"));
    lmo_article_files.setFile_path(rs.getString("file_path"));
    lmo_article_files.setFile_title(rs.getString("file_title"));
    lmo_article_files.setFile_desc(rs.getString("file_desc"));
    ls.add(lmo_article_files);
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

//RemoveByPrimaryKey------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeByPrimaryKey(java.lang.String)
*/
public void RemoveByArticle_id(java.lang.String _article_id)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  String sql = "delete from lmo_article_files where article_id ='" + _article_id + "'";
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



  }
