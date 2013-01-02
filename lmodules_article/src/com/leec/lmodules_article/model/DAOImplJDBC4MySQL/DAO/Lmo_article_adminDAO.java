package com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO;

  import java.sql.*;
  import java.util.*;

import com.leec.lmodules_article.model.DAOIfc.Lmo_article_adminDAOIfc;
import com.leec.lmodules_article.model.DTO.*;
import com.leec.lmodules_article.util.Configuration.ConfigurationStaticFinal_lxfxy;
/** 
*kingbill 2006 3.21 all rights reserved 
*���  strutsbook@126.com 
*��ݿ���ֱ��l�� ����0.8�� 
*/  
/* 

*/  
//lmo_article_adminDAO++++++++++++++++++++++++++++++++++++++++++++++++++

public class Lmo_article_adminDAO implements Lmo_article_adminDAOIfc {
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#createLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
public void createLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql="insert into lmo_article_admin (admin_id,admin_username,admin_pass) values ("
        + "'" + lmo_article_admin.getAdmin_id() + "'" + ","
        + "'" + lmo_article_admin.getAdmin_username() + "'" + ","
        + "'" + lmo_article_admin.getAdmin_pass() + "'"
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#updateLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
public void updateLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "update lmo_article_admin set "
        + "admin_username ='" + lmo_article_admin.getAdmin_username() + "',"
        + "admin_pass ='" + lmo_article_admin.getAdmin_pass() + "'"
        + " where admin_id = '" + lmo_article_admin.getAdmin_id() + "'"
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#updateLmo_article_admins(java.util.Vector)
 */
public void updateLmo_article_admins(Vector lmo_article_admins)
  {
      Iterator it = lmo_article_admins.iterator();
      while(it.hasNext())
      {
        Lmo_article_adminDTO lmo_article_admin = (Lmo_article_adminDTO)it.next();
        updateLmo_article_admin(lmo_article_admin);
      }
  }
//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _admin_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "delete from lmo_article_admin where admin_id = '" + _admin_id + "'";
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#removeLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
public void removeLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
  {
  removeByPrimaryKey(lmo_article_admin.getAdmin_id());
  }
//RemoveByDTOs------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#removeLmo_article_admins(java.util.Vector)
 */
public void removeLmo_article_admins(Vector lmo_article_admins)
  {
      Iterator it = lmo_article_admins.iterator();
      while(it.hasNext())
      {
        Lmo_article_adminDTO lmo_article_admin = (Lmo_article_adminDTO)it.next();
        removeLmo_article_admin(lmo_article_admin);
      }
  }
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_adminDTO findByPrimaryKey(java.lang.String _admin_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    Lmo_article_adminDTO lmo_article_admin = new Lmo_article_adminDTO();
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    stmt = con.createStatement();
    String sql = "select * from lmo_article_admin where admin_id = '" + _admin_id + "'";
    rs = stmt.executeQuery(sql);
    while(rs.next())
    {
      lmo_article_admin.setAdmin_id(rs.getString("admin_id"));
      lmo_article_admin.setAdmin_username(rs.getString("admin_username"));
      lmo_article_admin.setAdmin_pass(rs.getString("admin_pass"));
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
    return lmo_article_admin;
  }
  }

//findByAdmin_username------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_adminDTO> findByAdmin_username(java.lang.String _admin_username)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  List<Lmo_article_adminDTO> ls=new ArrayList<Lmo_article_adminDTO>();

  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article_admin where admin_username = '" + _admin_username + "'";
  rs = stmt.executeQuery(sql); 
  while(rs.next())
  {
	Lmo_article_adminDTO lmo_article_admin = new Lmo_article_adminDTO(); 
    lmo_article_admin.setAdmin_id(rs.getString("admin_id"));
    lmo_article_admin.setAdmin_username(rs.getString("admin_username"));
    lmo_article_admin.setAdmin_pass(rs.getString("admin_pass"));
    
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

//findall------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_adminDTO> findall()
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  List<Lmo_article_adminDTO> ls=new ArrayList<Lmo_article_adminDTO>();
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article_admin";
  rs = stmt.executeQuery(sql);
  
  while(rs.next())
  {
	Lmo_article_adminDTO lmo_article_admin = new Lmo_article_adminDTO();  
    lmo_article_admin.setAdmin_id(rs.getString("admin_id"));
    lmo_article_admin.setAdmin_username(rs.getString("admin_username"));
    lmo_article_admin.setAdmin_pass(rs.getString("admin_pass"));
    ls.add(lmo_article_admin);
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
