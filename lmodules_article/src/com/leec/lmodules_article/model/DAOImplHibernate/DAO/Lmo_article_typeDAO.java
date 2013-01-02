package com.leec.lmodules_article.model.DAOImplHibernate.DAO;

  import java.sql.*;
  import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

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

public class Lmo_article_typeDAO extends BaseHibernateDAO implements Lmo_article_typeDAOIfc {
	
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
	
	public static final String TYPE_FATHER = "type_father";
	public static final String TYPE_NAME = "type_name";
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#createLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 */
public void createLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().save(lmo_article_type);
	trans.commit();//提交事务
  }

//UpdateByDTO------------------------------------------------???????????????????????????????????????
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#updateLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 * @如果用update()或者saveorupdate()
 * @报错：a different object with the same identifier value was already associated wit
 * 
 */
public void updateLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().merge(lmo_article_type);
	trans.commit();//提交事务
  }

//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _type_id)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	String hql = "delete from Lmo_article_typeDTO where type_id =?";
	Query q = getSession().createQuery(hql) ;
	q.setString(0, _type_id);
    q.executeUpdate() ;
	trans.commit();//提交事务
	
	
	//getSession().delete(_type_id);
  }
//RemoveByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#removeLmo_article_type(com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO)
 */
public void removeLmo_article_type(Lmo_article_typeDTO lmo_article_type)
  {
	getSession().delete(lmo_article_type.getType_id());
  }

//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_typeDTO findByPrimaryKey(java.lang.String _type_id)
  {
	Lmo_article_typeDTO instance = (Lmo_article_typeDTO) getSession().get("com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO",_type_id);
	return instance;
  }

public List findByProperty(String propertyName, Object value) {

	String queryString = "from Lmo_article_typeDTO as model where model."+ propertyName + "= ?";
	Query queryObject = getSession().createQuery(queryString);
	queryObject.setParameter(0, value);
	return queryObject.list();	
}
//findByTypename------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_typeDTO> findByTypename(java.lang.String _type_name)
{
	return findByProperty(TYPE_NAME, _type_name);

}

//findByTypefather------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_typeDTO> findByTypefather(java.lang.String _type_father)
{
	return findByProperty(TYPE_FATHER, _type_father);
}

public Lmo_article_typeDTO merge(Lmo_article_typeDTO detachedInstance) {
	Lmo_article_typeDTO result = (Lmo_article_typeDTO) getSession().merge(detachedInstance);
	return result;
}

  }
