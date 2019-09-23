package com.rio.proj.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketSender {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebSocketSender> webSocketSet =
            new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("connect client: " + getOnlineCount());
        try {
            sendMessage(String.valueOf(getOnlineCount()));
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("disconnect a client: " + getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Error");
        error.printStackTrace();
    }

    public void sendMessage (String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static void sendInfo(String message) throws IOException {
        for (WebSocketSender webSocketSender : webSocketSet) {
            try {
                webSocketSender.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketSender.onlineCount ++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketSender.onlineCount --;
    }
}
