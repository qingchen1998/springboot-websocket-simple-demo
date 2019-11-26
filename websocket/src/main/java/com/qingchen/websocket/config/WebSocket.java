package com.qingchen.websocket.config;


import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Created by ChenJiaQiang on 2019/11/19 14:55
 * @ServerEndpoint  作用在类上  把当前类标识成一个WebSocket的服务端  这里value的配置  与  前端对应
 */
@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocket {

    // 存放每个客户端对应的WebSocket对象，线程安全
    private static ConcurrentHashMap<String, WebSocket> webSocketSet = new ConcurrentHashMap<>();

    // 与某个客户端的连接，通过它来给客户端发送数据
    private Session session;

    // 用户的唯一id
    private String id = "";

    /**
     * 连接成功
     *
     * @param session
     * @param id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        this.id = id;
        webSocketSet.put(id, this);    //加入到ConcurrentHashMap中
        System.out.println(id + "连接成功，当前在线人数为：" + webSocketSet.size());
    }

    /**
     * 退出连接
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this.id);
        System.out.println(id + "退出成功，当前在线人数为：" + webSocketSet.size());
    }

    /**
     * 群发
     *
     * @param message
     */
    public void GroupSending(String message) {
        for (String id : webSocketSet.keySet()) {
            try {
                webSocketSet.get(id).session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     *
     * @param id
     * @param message
     */
    public void AppointSending(String id, String message) {
        try {
            webSocketSet.get(id).session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
