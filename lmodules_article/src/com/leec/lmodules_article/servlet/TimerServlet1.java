package com.leec.lmodules_article.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
 * 定时执行操作
 * @author leeyn 
 */
public class TimerServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Timer timer1 = null;
	private Task1 task1;

	/**
	 * Constructor of the object.
	 */
	public TimerServlet1() {
		super();
	}

	/**
	 * Destruction of the servlet.
	 */
	public void destroy() {
		super.destroy();
		if (timer1 != null) {
			timer1.cancel();
			System.out.println("TimerServlet1定时器已销毁");
		}
	}

	// init方法启动定时器
	public void init()
	throws ServletException {		
		// (true为用定时间刷新缓存)
		String startTask =getInitParameter("startTask");
		//String path = request.getRealPath("/");
		
		// 设置首次执行时间；
		String sendmailhour = "03";
		String sendmailminutes = "00";
		String sendmailsecond = "00";
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sendmailhour));
		cal.set(Calendar.MINUTE, Integer.parseInt(sendmailminutes));
		cal.set(Calendar.SECOND, Integer.parseInt(sendmailsecond));
		System.out.println("TimerServlet1_current time:" + cal.getTime());
		//Thu Jul 21 01:35:30 CST 2011
		
		// 定时刷新时间(分钟)
		Long delay = Long.parseLong(getInitParameter("intervalTime"));
		
		// 启动定时器
		
		
		if (startTask.equals("true")) {
			timer1 = new Timer(true);
			task1 = new Task1();
			System.out.println("TimerServlet1定时器已启动");
			
			//timer1.schedule(task1, cal.getTime(), delay * 60 * 1000);// 此例以后过delay分钟执行一次任务；
			
			timer1.schedule(task1, cal.getTime(), 24 * 60 * 60 * 1000);// 此例以后过24小时执行一次任务；
			// 由于timer初始化时，如果定时器定的当天的时间已经过去了马上执行一次任务，
			// 如果当天的时间还不到，则等时间到了 再执行任务；
			// 定时器调度语句，其中TestTask是自定义需要被调度的执行任务
			// cal.getTime()为首次执行时间；
			
			//timer1.schedule(task1, 0, 24 * 60 * 60 * 1000);// 马上进行首次运行，24小时执行一次；
			System.out.println("TimerServlet1已经添加任务调度表");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}