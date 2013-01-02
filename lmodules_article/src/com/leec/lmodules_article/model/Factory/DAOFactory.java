package com.leec.lmodules_article.model.Factory;

import com.leec.lmodules_article.model.DAOIfc.*;
import com.leec.lmodules_article.util.Configuration.*;



public abstract class DAOFactory {
	private static Object initLock = new Object();

	private static DAOFactory factory = null;

	public static DAOFactory getInstance() {
		if (factory == null) {
			synchronized (initLock) {
				if (factory == null) {
					try {
						/*杩斿洖DAOFactoryImpl_XXX瀹炰緥
						XXX鍦ㄤ唬鐮佷腑鎸囧畾鎴栬�呬粠閰嶇疆鏂囦欢鍔犺浇
						姣斿杩斿洖  new DAOFactoryImpl_kingdao()
						    鎴栬��  new DAOFactorImpl_ejb()*/
						//搴曞眰DAO寮�鍏筹紝閫氳繃閰嶇疆鏂囦欢鍘婚厤缃娇鐢ㄥ摢绉嶅簳灞傛柟妗�
						factory = (DAOFactory)Class.forName(ConfigurationStaticFinal_lxfxy.DAOFactory).newInstance();
						
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return factory;
	}

	//需要调用n次方法，以create出n个DAO。
	public abstract Lmo_article_typeDAOIfc createLmo_article_typeDAO();
	public abstract Lmo_article_adminDAOIfc createLmo_article_adminDAO();
	public abstract Lmo_article_filesDAOIfc createLmo_article_filesDAO();
	public abstract Lmo_articleDAOIfc createLmo_articleDAO();
	
}
