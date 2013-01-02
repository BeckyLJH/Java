package com.leec.lmodules_article.model.DAOIfc;

import java.util.List;
import java.util.Vector;

import com.leec.lmodules_article.model.DTO.Lmo_articleDTO;

public interface Lmo_articleDAOIfc {

	//CreateByDTO------------------------------------------------
	public abstract void createLmo_article(Lmo_articleDTO lmo_article);

	//UpdateByDTO------------------------------------------------
	public abstract void updateLmo_article(Lmo_articleDTO lmo_article);

	//RemoveByPrimaryKey------------------------------------------------
	public abstract void removeByPrimaryKey(java.lang.String _article_id);

	//RemoveByDTO------------------------------------------------
	public abstract void removeLmo_article(Lmo_articleDTO lmo_article);

	//findByPrimaryKey------------------------------------------------
	public abstract Lmo_articleDTO findByPrimaryKey(java.lang.String _article_id);

	//findByArticle_type_id------------------------------------------------
	public abstract List<Lmo_articleDTO> findByArticle_type_id(java.lang.String _article_type_id);
	
	//findByPrimaryKey------------------------------------------------
	public abstract List<Lmo_articleDTO> findByPrimaryKey2(java.lang.String _article_id);
}