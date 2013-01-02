package com.leec.lmodules_article.model.DAOImplHibernate.zFactory;

import com.leec.lmodules_article.model.DAOIfc.*;
import com.leec.lmodules_article.model.DAOImplHibernate.DAO.*;
import com.leec.lmodules_article.model.Factory.DAOFactory;

public class zFactory extends DAOFactory{
	
	//n个DAO，n个接口，这里需要n个create方法。
	public Lmo_article_typeDAOIfc createLmo_article_typeDAO() {
		return new Lmo_article_typeDAO();
	}
	public Lmo_article_adminDAOIfc createLmo_article_adminDAO() {
		return new Lmo_article_adminDAO();
	}
	public Lmo_article_filesDAOIfc createLmo_article_filesDAO() {
		return new Lmo_article_filesDAO();
	}
	public Lmo_articleDAOIfc createLmo_articleDAO() {
		return new Lmo_articleDAO();
	}
	
}
