package com.leec.lmodules_article.servlet;
import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import com.leec.lmodules_article.service.Service;

/**
* 定时器的任务
*/
public class Task1 extends TimerTask {

	private ServletContext context; 
    private static boolean isRunning = false;
   
    //public Task1(ServletContext context){
    // this.context = context; 
    // run();
   // } 

    public void run() {    	
        if (!isRunning) {
                isRunning = true;
                //process
                //System.out.println("Task1:正在执行任务");
				Service src=new Service();
				src.dodelete2();
                isRunning = false;                
                //context.log("Task1:文件删除完毕");
                //控制台上打印log：
                //2011-7-21 16:45:52 org.apache.catalina.core.ApplicationContext log
                //信息: TaskServlet1:指定任务执行结束
        } else {
            context.log("Task1:上一次任务执行还未结束");
        }        
      }

}
