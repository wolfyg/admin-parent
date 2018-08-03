package cn.ctx.common.framework.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketUtil {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<WebSocketUtil>();

    //线程安全的Map  
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();//建立连接的方法
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
	@OnOpen
    public void onOpen(Session session,@PathParam("userId")String userId) {
    	System.out.println(userId);
    	this.session=session;
        webSocketMap.put(userId,session);     //加入set中
        webSocketSet.add(this);     //加入到集合中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
        	sendToUserMessage("已连接id:"+userId,userId);
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (WebSocketUtil item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


	public  void sendToUserMessage(String message,String userId) throws IOException {
		webSocketMap.get(userId).getBasicRemote().sendText(message);
//		webSocketMap.get(userId).getAsyncRemote().sendText(message);
    }

	public  void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
//        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketUtil item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketUtil.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketUtil.onlineCount--;
    }
}