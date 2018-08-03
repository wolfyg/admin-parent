/**    
* @Title: SendTask.java  
* @Package cn.ctx.service.job  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2018年3月2日 下午2:12:37  
* @version V1.0    
*/
package cn.ctx.service.job;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**  
* @ClassName: SendTask  
* @Description: TODO(发送任务)  
* @author gyu
* @date 2018年3月2日 下午2:12:37  
*    
*/
public class SendTask implements Runnable{

	public static Map<String,Object> taskMap=new ConcurrentHashMap<String,Object>(10);
	
	public static String IS_CANCEL="isCancel";
	
	public Map<String,Object> map;
	
	public SendTask(Map<String,Object> map) {
		this.map=map;
	}
	
	public static long getThreadId() {
		return Thread.currentThread().getId();
	}
	
	@Override
	public void run() {
		long time=(Long)map.get("time")-System.currentTimeMillis();
		taskMap.put(map.get("id").toString(), this);
		synchronized (this) {
			try {
				System.out.println("开始等待结束");
				this.wait(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if((Boolean)map.get(IS_CANCEL)) {
			System.out.println(map.toString());
		}else {
			System.out.println("任务取消");
		}
		taskMap.remove(map.get("id").toString());
	}
	
//	public static void main(String args[]) throws InterruptedException {
//		test1();
//		test2();
//		Thread.sleep(1000);
//		cancel();
//	}
	
	public static void cancel() {

        SendTask task = (SendTask) SendTask.taskMap.get("1");
		task.map.put("isCancel", false);
        synchronized (task) {
            task.notifyAll();
        }
        task = (SendTask) SendTask.taskMap.get("2");
//        task.map.put("isCancel", false);
        synchronized (task) {
        	task.notifyAll();
        }
	}
	
	public static void test1() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", 1);
		map.put("time", System.currentTimeMillis()+2000);
		map.put("msg", "我是测试1");
		map.put("isCancel", true);
		new Thread(new SendTask(map)).start();
	}

	public static void test2() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", 2);
		map.put("time", System.currentTimeMillis()+2000);
		map.put("msg", "我是测试2");
		map.put("isCancel", true);
		new Thread(new SendTask(map)).start();
	}
	
	
    public static int count = 0; 

    public static void inc() { 

        //这里延迟1毫秒，使得结果明显 
        try { 
            Thread.sleep(1); 
        } catch (InterruptedException e) { 
        } 

        count++; 
    } 

    public static void main(String[] args) { 

        //同时启动1000个线程，去进行i++计算，看看实际结果 

        for (int i = 0; i < 1000; i++) { 
            new Thread(new Runnable() { 
                @Override
                public void run() { 
                    inc(); 
                } 
            }).start(); 
        } 

        //这里每次运行的值都有可能不同,可能为1000 
        System.out.println("运行结果:Counter.count=" + count); 
    } 
}
