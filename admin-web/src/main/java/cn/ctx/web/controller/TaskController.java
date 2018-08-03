package cn.ctx.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ctx.service.model.ScheduleJob;
import cn.ctx.service.quartz.QuartzCronDateUtils;
import cn.ctx.service.quartz.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	@Autowired
	private TaskService taskService;
 
    private ScheduledFuture<?> future;
 
 
    @RequestMapping("/startCron")
    @ResponseBody
    public String startCron() {
       future = threadPoolTaskScheduler.schedule(new MyRunnable(), new org.springframework.scheduling.support.CronTrigger("0/5 * * * * *"));
       System.out.println("DynamicTask.startCron()");
       return "startCron";
    }
 
    /**
    * @Title: stopCron
    * @Description: TODO(停止任务)
    * @author gyu
    * @date 2017年11月13日下午6:42:01
     */
    @RequestMapping("/stopCron")
    public String stopCron() throws InterruptedException, ExecutionException {
       if (future != null) {
           future.cancel(true);
       }
       System.out.println("停止任务:"+future);
       return "stopCron";
    }
 
    @RequestMapping("/changeCron")
    @ResponseBody
    public String startCron(String cron) throws ParseException, InterruptedException, ExecutionException {
       stopCron();
       future = threadPoolTaskScheduler.schedule(new MyRunnable(), DateUtils.addSeconds(new Date(), Integer.parseInt(cron)));
       System.out.println("开始执行任务");
       return "changeCron";
    }
 
    private class MyRunnable implements Runnable {
       @Override
       public void run() {
           System.out.println("运行时间:" + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()) +",数据");
       }
    }
    
    @RequestMapping("/add")
    @ResponseBody
    public String add(ScheduleJob job) throws SchedulerException {
    	job.setJobId(1L);
    	job.setJobName("test1");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run");
    	job.setIsConcurrent("0");
    	System.out.println("任务启动时间:"+QuartzCronDateUtils.getCron(DateUtils.addSeconds(new Date(), 10)));
    	job.setCronExpression(QuartzCronDateUtils.getCron(DateUtils.addSeconds(new Date(), 10)));
    	taskService.addJob(job);
       return "add";
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public String update(ScheduleJob job) throws SchedulerException {
    	job.setJobId(1L);
    	job.setJobName("test1");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run1");
    	System.out.println("任务启动时间:"+QuartzCronDateUtils.getCron(DateUtils.addSeconds(new Date(), 1)));
    	job.setCronExpression(QuartzCronDateUtils.getCron(DateUtils.addSeconds(new Date(), 1)));
    	taskService.updateJobCron(job);
       return "update";
    }
    
    
    @RequestMapping("/getAllJob")
    @ResponseBody
    public Object getAllJob() throws SchedulerException {
       return taskService.getAllJob();
    }

    @RequestMapping("/getRunningJob")
    @ResponseBody
    public Object getRunningJob() throws SchedulerException {
       return taskService.getRunningJob();
    }
    
    @RequestMapping("/pauseJob")
    @ResponseBody
    public Object pauseJob() throws SchedulerException {
    	ScheduleJob job=new ScheduleJob();
    	job.setJobId(1L);
    	job.setJobName("test");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run1");
    	return taskService.pauseJob(job);
    }
    
    @RequestMapping("/resumeJob")
    @ResponseBody
    public Object resumeJob() throws SchedulerException {
    	ScheduleJob job=new ScheduleJob();
    	job.setJobId(1L);
    	job.setJobName("test");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run1");
    	taskService.resumeJob(job);
    	return true;
    }

    @RequestMapping("/deleteJob")
    @ResponseBody
    public Object deleteJob() throws SchedulerException {
    	ScheduleJob job=new ScheduleJob();
    	job.setJobId(1L);
    	job.setJobName("test1");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run1");
    	taskService.deleteJob(job);
      	return true;
    }
    
    @RequestMapping("/runAJobNow")
    @ResponseBody
    public Object runAJobNow() throws SchedulerException {
    	ScheduleJob job=new ScheduleJob();
    	job.setJobId(1L);
    	job.setJobName("test1");
    	job.setJobGroup("g1");
    	job.setJobStatus("1");
    	job.setBeanClass("cn.ctx.admin.core.quartz.TaskTest");
    	job.setMethodName("run1");
    	taskService.runAJobNow(job);
      	return true;
    }
    
    
    
}
