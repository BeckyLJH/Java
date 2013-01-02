package com.leec.lmodules_article.model.DAOIfc;

import java.util.List;
import java.util.Vector;

import com.leec.lmodules_article.model.DTO.Lmo_article_typeDTO;

public interface Lmo_article_typeDAOIfc {

	//CreateByDTO------------------------------------------------
	public abstract void createLmo_article_type(
			Lmo_article_typeDTO lmo_article_type);

	//UpdateByDTO------------------------------------------------
	public abstract void updateLmo_article_type(
			Lmo_article_typeDTO lmo_article_type);

	//RemoveByPrimaryKey------------------------------------------------
	public abstract void removeByPrimaryKey(java.lang.String _type_id);

	//RemoveByDTO------------------------------------------------
	public abstract void removeLmo_article_type(
			Lmo_article_typeDTO lmo_article_type);

	//findByPrimaryKey------------------------------------------------
	public abstract Lmo_article_typeDTO findByPrimaryKey(
			java.lang.String _type_id);

	//findByTypename------------------------------------------------
	public abstract List<Lmo_article_typeDTO> findByTypename(
			java.lang.String _type_name);
	

	//findByTypefather------------------------------------------------
	public abstract List<Lmo_article_typeDTO> findByTypefather(
			java.lang.String _type_father);
	
}

