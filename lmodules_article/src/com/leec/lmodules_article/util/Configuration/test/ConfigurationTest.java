package com.leec.lmodules_article.util.Configuration.test;


public class ConfigurationTest{   

    public static void main(String [] args){
    	//通过系统常量IP获得IP地址值
    	System.out.println(ConfigurationStaticFinal.DB_DRIVER);
    	//修改IP地址值
    	ConfigurationStaticFinal.CONF.saveProperty("systemName","广州理氏信息科技有限公司-一生创展实验室-ATM教学系统");
    	//重新读取配置文件获得最新的IP地址值
    	System.out.println(ConfigurationStaticFinal.CONF.findProperty("systemName"));
    }
}
