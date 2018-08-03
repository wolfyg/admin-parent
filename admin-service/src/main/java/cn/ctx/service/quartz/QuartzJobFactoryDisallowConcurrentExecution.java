/**    
* @Title: QuartzJobFactoryDisallowConcurrentExecution.java  
* @Package cn.ctx.admin.core.quartz  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月14日 上午10:39:09  
* @version V1.0    
*/
package cn.ctx.service.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.ctx.service.model.ScheduleJob;

/**
* @ClassName: QuartzJobFactoryDisallowConcurrentExecution  
* @Description: TODO(若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作)  
* @author gyu
* @date 2017年11月14日 下午2:33:23  
*
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}
