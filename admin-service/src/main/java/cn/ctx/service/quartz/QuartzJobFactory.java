/**    
* @Title: QuartzJobFactory.java  
* @Package cn.ctx.admin.core.quartz  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月14日 上午10:34:11  
* @version V1.0    
*/
package cn.ctx.service.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.ctx.service.model.ScheduleJob;

/**  
* @ClassName: QuartzJobFactory  
* @Description: TODO(计划任务执行处 无状态)  
* @author gyu
* @date 2017年11月14日 上午10:34:11  
*    
*/
public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}
