package com.leec.lmodules_article.model.DAOImplHibernate.DAO;

  import java.sql.*;
  import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

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

public class Lmo_articleDAO extends BaseHibernateDAO implements Lmo_articleDAOIfc {
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
	
	public static final String ARTICLE_TYPE_ID = "article_type_id";
	public static final String ARTICLE_ID = "article_id";
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#createLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void createLmo_article(Lmo_articleDTO lmo_article)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().save(lmo_article);
	trans.commit();//提交事务
  }

//UpdateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#updateLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void updateLmo_article(Lmo_articleDTO lmo_article)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().merge(lmo_article);
	trans.commit();//提交事务
  }
//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _article_id)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	String hql = "delete from Lmo_articleDTO where article_id =?";
	Query q = getSession().createQuery(hql) ;
	q.setString(0, _article_id);
    q.executeUpdate() ;
	trans.commit();//提交事务
  }
//RemoveByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#removeLmo_article(com.leec.lmodules_article.model.DTO.Lmo_articleDTO)
 */
public void removeLmo_article(Lmo_articleDTO lmo_article)
  {
	getSession().delete(lmo_article.getArticle_id());
  }
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_articleDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_articleDTO findByPrimaryKey(java.lang.String _article_id)
  {
	Lmo_articleDTO instance = (Lmo_articleDTO) getSession().get("com.leec.lmodules_article.model.DTO.Lmo_articleDTO",_article_id);
	return instance;
  }

public List findByProperty(String propertyName, Object value) {

	String queryString = "from Lmo_articleDTO as model where model."+ propertyName + "= ?";
	Query queryObject = getSession().createQuery(queryString);
	queryObject.setParameter(0, value);
	return queryObject.list();	
}

//findByArticle_type_id------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_typeDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_articleDTO> findByArticle_type_id(java.lang.String _article_type_id)
{
	return findByProperty(ARTICLE_TYPE_ID, _article_type_id);
}

public List<Lmo_articleDTO> findByPrimaryKey2(java.lang.String _article_id)
{
	return findByProperty(ARTICLE_ID, _article_id);
}

public Lmo_article_typeDTO merge(Lmo_article_typeDTO detachedInstance) {
	Lmo_article_typeDTO result = (Lmo_article_typeDTO) getSession().merge(detachedInstance);
	return result;
}
  }
