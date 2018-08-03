package cn.ctx.service.provider.impl;

import java.io.IOException;
import java.util.Map;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import cn.ctx.common.framework.websocket.WebSocketUtil;
import cn.ctx.service.provider.JMSService;

@Service
public class JMSServiceImpl implements JMSService{

	@Autowired
	JmsMessagingTemplate jmsTemplate;
	
	private WebSocketUtil mywebSocket;
	
	public Map<String, Object> sendSMS(Destination destination, Map<String, Object> map) {
		jmsTemplate.convertAndSend(destination, map);
		return map;
	}

	@JmsListener(destination = "out.topic",containerFactory = "jmsListenerContainerTopic")
	public void outQueue(Map<String, Object> map) {
		System.out.println("我收到了topic消息回复:"+map.toString());
	}

	@JmsListener(destination = "out.queue",containerFactory = "jmsListenerContainerQueue")
	public void outTopic(Map<String, Object> map) {
		System.out.println("我收到了queue消息回复:"+map.toString());
	}

	@JmsListener(destination = "sample.queue",containerFactory = "jmsListenerContainerQueue")
	@SendTo(value="out.queue")
	@Override
	public Object receiveQueue(Map<String, Object> map) {
		System.out.println("我接收到了queue消息："+map);
		try {
//			mywebSocket.sendToUserMessage("我接收到了queue消息："+map.toString(),"yg");
			mywebSocket.sendInfo("我接收到了queue消息："+map.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@JmsListener(destination = "sample.topic",containerFactory = "jmsListenerContainerTopic")
	@SendTo(value="out.topic")
	@Override
	public Object receiveTopic(Map<String, Object> map) {
		System.out.println("我接收到了topic消息："+map);
		try {
//			mywebSocket.sendToUserMessage("我接收到了topic消息："+map,"yg");
			mywebSocket.sendInfo("我接收到了topic消息："+map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
