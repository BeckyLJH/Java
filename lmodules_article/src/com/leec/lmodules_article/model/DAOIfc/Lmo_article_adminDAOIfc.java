package com.leec.lmodules_article.model.DAOIfc;

import java.util.List;
import java.util.Vector;

import com.leec.lmodules_article.model.DTO.Lmo_article_adminDTO;

public interface Lmo_article_adminDAOIfc {

	//CreateByDTO------------------------------------------------
	public abstract void createLmo_article_admin(
			Lmo_article_adminDTO lmo_article_admin);

	//UpdateByDTO------------------------------------------------
	public abstract void updateLmo_article_admin(
			Lmo_article_adminDTO lmo_article_admin);

	//RemoveByPrimaryKey------------------------------------------------
	public abstract void removeByPrimaryKey(java.lang.String _admin_id);

	//RemoveByDTO------------------------------------------------
	public abstract void removeLmo_article_admin(
			Lmo_article_adminDTO lmo_article_admin);

	//findByPrimaryKey------------------------------------------------
	public abstract Lmo_article_adminDTO findByPrimaryKey(
			java.lang.String _admin_id);

	//findByAdmin_username------------------------------------------------
	public abstract List<Lmo_article_adminDTO> findByAdmin_username(
			java.lang.String _admin_username);
	
	//findall------------------------------------------------
	public abstract List<Lmo_article_adminDTO> findall();
}