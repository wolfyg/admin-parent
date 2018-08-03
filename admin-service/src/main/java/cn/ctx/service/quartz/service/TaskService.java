/**    
* @Title: TaskService.java  
* @Package cn.ctx.admin.core.quartz.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年11月14日 上午10:48:54  
* @version V1.0    
*/
package cn.ctx.service.quartz.service;

import java.util.List;

import org.quartz.SchedulerException;

import cn.ctx.service.model.ScheduleJob;

/**  
* @ClassName: TaskService  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author gyu
* @date 2017年11月14日 上午10:48:54  
*    
*/
public interface TaskService {

	/**
	* @Title: getAllJob
	* @Description: TODO(获取所有计划中的任务列表)
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:50:28
	 */
	List<ScheduleJob> getAllJob() throws SchedulerException;
	
	/**
	* @Title: getRunningJob
	* @Description: TODO(所有正在运行的job)
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:50:55
	 */
	List<ScheduleJob> getRunningJob() throws SchedulerException;
	
	/**
	* @Title: pauseJob
	* @Description: TODO(暂停一个job)
	* @param ScheduleJob
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:51:23
	 */
	boolean pauseJob(ScheduleJob scheduleJob) throws SchedulerException;
	
	/**
	* @Title: updateJobCron
	* @Description: TODO(更新job时间表达式)
	* @param updateJobCron
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:52:00
	 */
	void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException;
	
	/**
	* @Title: runAJobNow
	* @Description: TODO(立即执行job)
	* @param ScheduleJob
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:52:26
	 */
	void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;
	
	/**
	* @Title: addJob
	* @Description: TODO(添加任务)
	* @param ScheduleJob
	* @author gyu
	 * @throws SchedulerException 
	* @date 2017年11月14日上午10:52:44
	 */
	void addJob(ScheduleJob job) throws SchedulerException;
	
	/**
	* @Title: resumeJob
	* @Description: TODO(恢复一个job)
	* @param ScheduleJob
	* @author gyu
	* @date 2017年11月14日下午1:44:34
	 */
	void resumeJob(ScheduleJob scheduleJob) throws SchedulerException;
	
	/**
	* @Title: deleteJob
	* @Description: TODO(删除一个job)
	* @param ScheduleJob
	* @author gyu
	* @date 2017年11月14日下午1:45:37
	 */
	void deleteJob(ScheduleJob scheduleJob) throws SchedulerException;
}
