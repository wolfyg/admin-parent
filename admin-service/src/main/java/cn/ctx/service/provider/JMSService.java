package cn.ctx.service.provider;

import java.util.Map;

import javax.jms.Destination;

public interface JMSService {
	
	/**
	* @Title: sendSMS
	* @Description: TODO(发送消息)
	* @param Map
	* @author gyu
	* @date 2017年12月13日下午1:59:01
	 */
	public Map<String,Object> sendSMS(Destination destination,Map<String,Object> map);
	
	/**
	* @Title: outQueue
	* @Description: TODO(返回的消息)
	* @author gyu
	* @date 2017年12月13日下午1:58:39
	 */
	public void outQueue(Map<String,Object> map);
	
	/**
	* @Title: receiveQueue
	* @Description: TODO(接收消息)
	* @param map
	* @author gyu
	* @date 2018年1月27日下午5:56:11
	 */
	public Object receiveQueue(Map<String,Object> map);

	/**
	* @Title: receiveTopic
	* @Description: TODO(接收消息)
	* @param map
	* @author gyu
	* @date 2018年1月27日下午5:56:11
	 */
	public Object receiveTopic(Map<String,Object> map);
}
