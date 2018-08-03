/**    
* @Title: BioQueue.java  
* @Package cn.ctx.admin.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author gyu
* @date 2017年12月27日 上午10:56:40  
* @version V1.0    
*/
package cn.ctx.common.framework.util;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
* @ClassName: BioQueue  
* @Description: TODO(阻塞队列)  
* @author gyu
* @date 2017年12月27日 上午10:56:40  
*    
*/
public class BioQueue {
	private Logger log=LoggerFactory.getLogger(BioQueue.class);
    //队列大小
    public static final int QUEUE_MAX_SIZE = 1;
    private static BioQueue alarmMessageQueue = null;
    //阻塞队列
    private BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<Object>(QUEUE_MAX_SIZE);

    private BioQueue() {
    }

    public static BioQueue getInstance() {
    	if(alarmMessageQueue==null) {
    		alarmMessageQueue=new BioQueue();
    	}
        return alarmMessageQueue;
    }
    /**
     * 消息入队
     * @param <T>
     * @param <T>
     * @param log
     * @return
     */
    public boolean push(Object obj) {
    	try {
            return this.blockingQueue.add(obj);//队列满了就抛出异常，不阻塞
    	}catch(Exception e){
    		log.error(e.getMessage());
    		return false;
    	}
    }
    /**
     * 消息出队
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public <T> T poll() {
        T result = null;
        try {
            result = (T) this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void queueEach() {
    	for(Iterator<?> it=blockingQueue.iterator();it.hasNext();) {
    		System.out.println(it.next());
    	}
    }
}
