package com.leec.lmodules_article.model.DAOImplHibernate.DAO;

  import java.sql.*;
  import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

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

public class Lmo_article_filesDAO extends BaseHibernateDAO implements Lmo_article_filesDAOIfc {
	static String driver=ConfigurationStaticFinal_lxfxy.DB_DRIVER ;
	static String url =ConfigurationStaticFinal_lxfxy.DB_URL ;
	static String username = ConfigurationStaticFinal_lxfxy.DB_USERNAME;
	static String password =ConfigurationStaticFinal_lxfxy.DB_PASSWORD;
	
	public static final String ARTICLE_ID = "article_id";
	public static final String FILE_PATH = "file_path";
	public static final String FILE_TITLE = "file_title";
	public static final String FILE_DESC = "file_desc";
//CreateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#createLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void createLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().save(lmo_article_files);
	trans.commit();//提交事务
  }

//UpdateByDTO------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#updateLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void updateLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	getSession().merge(lmo_article_files);
	trans.commit();//提交事务
  }

//RemoveByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeByPrimaryKey(java.lang.String)
 */
public void removeByPrimaryKey(java.lang.String _file_id)
  {
	Transaction trans= getSession().beginTransaction();//开始事务
	String hql = "delete from Lmo_article_filesDTO where file_id =?";
	Query q = getSession().createQuery(hql) ;
	q.setString(0, _file_id);
    q.executeUpdate() ;
	trans.commit();//提交事务
  }

//RemoveByArticle_id------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeByPrimaryKey(java.lang.String)
*/
public void RemoveByArticle_id(java.lang.String _article_id)
{
	Transaction trans= getSession().beginTransaction();//开始事务
	String hql = "delete from Lmo_article_filesDTO where article_id =?";
	Query q = getSession().createQuery(hql) ;
	q.setString(0, _article_id);
    q.executeUpdate() ;
	trans.commit();//提交事务

}

//RemoveByLmo_article_files------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#removeLmo_article_files(com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO)
 */
public void removeLmo_article_files(Lmo_article_filesDTO lmo_article_files)
  {
	getSession().delete(lmo_article_files.getFile_id());
  }

//findByPrimaryKey------------------------------------------------
  /* (non-Javadoc)
 * @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#findByPrimaryKey(java.lang.String)
 */
public Lmo_article_filesDTO findByPrimaryKey(java.lang.String _file_id)
  {
	Lmo_article_filesDTO instance = (Lmo_article_filesDTO) getSession().get("com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO",_file_id);
	return instance;
  }

public List findByProperty(String propertyName, Object value) {

		String queryString = "from Lmo_article_filesDTO as model where model."+ propertyName + "= ?";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setParameter(0, value);
		return queryObject.list();	
}

//findByArticle_id------------------------------------------------
/* (non-Javadoc)
* @see com.leec.lmodules_article.model.DAOImplJDBC4MySQL.DAO.Lmo_article_filesDAOIfc#findByPrimaryKey(java.lang.String)
*/
public List<Lmo_article_filesDTO> findByArticle_id(java.lang.String _article_id)
{
	//List results = getSession().createCriteria("com.leec.lmodules_article.model.DAOImplHibernate.DAO.Lmo_article_files").add(Example.create(_article_id)).list();
	//return results;
	return findByProperty(ARTICLE_ID, _article_id);
}

public Lmo_article_typeDTO merge(Lmo_article_typeDTO detachedInstance) {
	Lmo_article_typeDTO result = (Lmo_article_typeDTO) getSession().merge(detachedInstance);
	return result;
}

  }
