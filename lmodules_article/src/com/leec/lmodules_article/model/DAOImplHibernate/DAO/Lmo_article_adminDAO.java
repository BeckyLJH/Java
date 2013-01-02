package com.leec.lmodules_article.model.DAOImplHibernate.DAO;

  import java.sql.*;
  import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;

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

public class Lmo_article_adminDAO extends BaseHibernateDAO implements Lmo_article_adminDAOIfc {
	public static final String ADMIN_USERNAME = "admin_username";
	
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#createLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
	public void createLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
	  {		
		 	Transaction trans= getSession().beginTransaction();//开始事务
			getSession().save(lmo_article_admin);	
			trans.commit();//提交事务
	  }

//UpdateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#updateLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
	public void updateLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
	  {
		Transaction trans= getSession().beginTransaction();//开始事务
		getSession().merge(lmo_article_admin);
		trans.commit();//提交事务
	  }

//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#removeByPrimaryKey(java.lang.String)
 */
	public void removeByPrimaryKey(java.lang.String _admin_id)
	  {
		Transaction trans= getSession().beginTransaction();//开始事务
		String hql = "delete from Lmo_article_adminDTO where admin_id =?";
		Query q = getSession().createQuery(hql) ;
		q.setString(0, _admin_id);
	    q.executeUpdate() ;
		trans.commit();//提交事务
	  }
	
//RemoveByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#removeLmo_article_admin(com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO)
 */
	public void removeLmo_article_admin(Lmo_article_adminDTO lmo_article_admin)
	  {
		getSession().delete(lmo_article_admin.getAdmin_id());
	  }
	
//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_adminDTO findByPrimaryKey(java.lang.String _admin_id)
  {
	Lmo_article_adminDTO instance = (Lmo_article_adminDTO) getSession().get("com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO",_admin_id);
	return instance;
  }

//findByAdmin_username------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_adminDTO> findByAdmin_username(java.lang.String _admin_username)
{
	return findByProperty(ADMIN_USERNAME, _admin_username);
	
}
public List findByProperty(String propertyName, Object value) {

	String queryString = "from Lmo_article_adminDTO as model where model."+ propertyName + "= ?";
	Query queryObject = getSession().createQuery(queryString);
	queryObject.setParameter(0, value);
	return queryObject.list();	
}

//findall------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_adminDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_adminDTO> findall()
{
	String queryString = "from Lmo_article_adminDTO";
	Query queryObject = getSession().createQuery(queryString);
	return queryObject.list();
}
public Lmo_article_typeDTO merge(Lmo_article_typeDTO detachedInstance) {
	Lmo_article_typeDTO result = (Lmo_article_typeDTO) getSession().merge(detachedInstance);
	return result;
}

  }
