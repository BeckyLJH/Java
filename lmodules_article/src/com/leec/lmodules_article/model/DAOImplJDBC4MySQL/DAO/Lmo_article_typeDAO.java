package com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO;

  import java.sql.*;
  import java.util.*;

import com.leec.lmodules_article.model.DAOIfc.Lmo_article_typeDAOIfc;
import com.leec.lmodules_article.model.DTO.*;
import com.leec.lmodules_article.util.Configuration.ConfigurationStaticFinal_lxfxy;
/** 
*kingbill 2006 3.21 all rights reserved 
*���  strutsbook@126.com 
*��ݿ���ֱ��l�� ����0.8�� 
*/  
/* 

*/  
//lmo_article_typeDAO++++++++++++++++++++++++++++++++++++++++++++++++++

public class Lmo_article_typeDAO implements Lmo_article_typeDAOIfc {
	
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#createLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 */
public void createLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql="insert into lmo_article_type (type_id,type_name,type_order,type_desc,type_father) values ("
        + "'" + lmo_article_type.getType_id() + "'" + ","
        + "'" + lmo_article_type.getType_name() + "'" + ","
        +       lmo_article_type.getType_order() + ","
        + "'" + lmo_article_type.getType_desc() + "'" + ","
        + "'" + lmo_article_type.getType_father() + "'"
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#updateLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 */
public void updateLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;

    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "update lmo_article_type set "
        + "type_name ='" + lmo_article_type.getType_name() + "',"
        + "type_order =" +lmo_article_type.getType_order() + ","
        + "type_desc ='" + lmo_article_type.getType_desc() + "',"
        + "type_father ='" + lmo_article_type.getType_father() + "'"
        + " where type_id = '" + lmo_article_type.getType_id() + "'"
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#updateLmo_article_types(java.util.Vector)
 */
public void updateLmo_article_types(Vector lmo_article_types)
  {
      Iterator it = lmo_article_types.iterator();
      while(it.hasNext())
      {
        Lmo_article_typeDTO lmo_article_type = (Lmo_article_typeDTO)it.next();
        updateLmo_article_type(lmo_article_type);
      }
  }
//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _type_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    String sql = "delete from lmo_article_type where type_id = '" + _type_id + "'";
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
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#removeLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 */
public void removeLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
  removeByPrimaryKey(lmo_article_type.getType_id());
  }
//RemoveByDTOs------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#removeLmo_article_types(java.util.Vector)
 */
public void removeLmo_article_types(Vector lmo_article_types)
  {
      Iterator it = lmo_article_types.iterator();
      while(it.hasNext())
      {
        Lmo_article_typeDTO lmo_article_type = (Lmo_article_typeDTO)it.next();
        removeLmo_article_type(lmo_article_type);
      }
  }
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_typeDTO findByPrimaryKey(java.lang.String _type_id)
  {
    Connection con=null;
    Statement  stmt=null;
    ResultSet  rs=null;
    Lmo_article_typeDTO lmo_article_type = new Lmo_article_typeDTO();
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url,username,password);
    stmt = con.createStatement();
    String sql = "select * from lmo_article_type where type_id = '" + _type_id + "'";
    rs = stmt.executeQuery(sql);
    while(rs.next())
    {
      lmo_article_type.setType_id(rs.getString("type_id"));
      lmo_article_type.setType_name(rs.getString("type_name"));
      lmo_article_type.setType_order(new Integer(rs.getInt("type_order")));
      lmo_article_type.setType_desc(rs.getString("type_desc"));
      lmo_article_type.setType_father(rs.getString("type_father"));
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
    return lmo_article_type;
  }
  }

//findByTypename------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_typeDTO> findByTypename(java.lang.String _type_name)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  List<Lmo_article_typeDTO> ls=new ArrayList<Lmo_article_typeDTO>();
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article_type where type_name = '" + _type_name + "'";
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
	Lmo_article_typeDTO lmo_article_type = new Lmo_article_typeDTO();  
    lmo_article_type.setType_id(rs.getString("type_id"));
    lmo_article_type.setType_name(rs.getString("type_name"));
    lmo_article_type.setType_order(new Integer(rs.getInt("type_order")));
    lmo_article_type.setType_desc(rs.getString("type_desc"));
    lmo_article_type.setType_father(rs.getString("type_father"));
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

//findByTypefather------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_typeDTO> findByTypefather(java.lang.String _type_father)
{
  Connection con=null;
  Statement  stmt=null;
  ResultSet  rs=null;
  
  List<Lmo_article_typeDTO> ls=new ArrayList<Lmo_article_typeDTO>();
  
  
  try{
  Class.forName(driver);
  con = DriverManager.getConnection(url,username,password);
  stmt = con.createStatement();
  String sql = "select * from lmo_article_type where type_father = '" + _type_father + "'";
  rs = stmt.executeQuery(sql);
  while(rs.next())
  {
	Lmo_article_typeDTO lmo_article_type = new Lmo_article_typeDTO(); 
    lmo_article_type.setType_id(rs.getString("type_id"));
    lmo_article_type.setType_name(rs.getString("type_name"));
    lmo_article_type.setType_order(new Integer(rs.getInt("type_order")));
    lmo_article_type.setType_desc(rs.getString("type_desc"));
    lmo_article_type.setType_father(rs.getString("type_father"));
    ls.add(lmo_article_type);
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
