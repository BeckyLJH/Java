package com.leec.lmodules_article.model.DAOIfc;

import java.util.List;
import java.util.Vector;

import com.leec.lmodules_article.model.DTO.Lmo_article_filesDTO;

public interface Lmo_article_filesDAOIfc {

	//CreateByDTO------------------------------------------------
	public abstract void createLmo_article_files(
			Lmo_article_filesDTO lmo_article_files);

	//UpdateByDTO------------------------------------------------
	public abstract void updateLmo_article_files(
			Lmo_article_filesDTO lmo_article_files);

	//RemoveByPrimaryKey------------------------------------------------
	public abstract void removeByPrimaryKey(java.lang.String _file_id);

	//RemoveByDTO------------------------------------------------
	public abstract void removeLmo_article_files(
			Lmo_article_filesDTO lmo_article_files);

	//findByPrimaryKey------------------------------------------------
	public abstract Lmo_article_filesDTO findByPrimaryKey(
			java.lang.String _file_id);
	
	//findByArticle_id------------------------------------------------
	public abstract List<Lmo_article_filesDTO> findByArticle_id(
			java.lang.String _article_id);
	
	//RemoveByArticle_id------------------------------------------------
	public abstract void RemoveByArticle_id(java.lang.String _article_id);

}