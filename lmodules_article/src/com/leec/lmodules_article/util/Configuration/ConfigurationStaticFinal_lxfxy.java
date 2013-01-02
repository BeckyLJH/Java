package com.leec.lmodules_article.util.Configuration;
import com.leec.lmodules_article.util.Configuration.Configuration;
import com.leec.lmodules_article.util.path.GetRealPath;

//应用系统自己的配置类，定义为常量，便于调用

//注意，还应该学习fromLUCS的类，调用配置可直接调用对象，不仅仅是字符串

public class ConfigurationStaticFinal_lxfxy{    
	// 配置文件路径、配置操作对象定义为常量.
    public static final String FILE_PATH = GetRealPath.getSrcOrClassesUrl("Classes")+"/lmodules_article.properties/lxfxy.properties";
    public static final Configuration CONF;
    // 各类配置定义为常量.修改要重启该类才能生效
    public static final String DB_DRIVER;
    public static final String DB_URL;
    public static final String DB_USERNAME;
	public static final String DB_PASSWORD;
	public static final String DAOFactory;
	
	static
    {
        CONF = new Configuration(FILE_PATH);        
        DB_DRIVER = CONF.findProperty("dbDriver");
        DB_URL = CONF.findProperty("dbUrl");
        DB_USERNAME = CONF.findProperty("dbUserName");
        DB_PASSWORD = CONF.findProperty("dbPassword");
        DAOFactory = CONF.findProperty("DAOFactory");
    }
    public static void main(String [] args){
    	//System.out.println(IP);
    	//CONF.saveProperty("ip","192.168.0.111");
    	//System.out.println(CONF.findProperty("ip"));
    }
}
