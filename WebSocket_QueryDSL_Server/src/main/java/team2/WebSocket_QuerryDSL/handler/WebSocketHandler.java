package team2.WebSocket_QuerryDSL.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] splitPayload = payload.split(":", 2);
        String username = splitPayload.length > 1 ? splitPayload[0] : "Anonymous";
        String userMessage = splitPayload.length > 1 ? splitPayload[1] : payload;
        // 클라이언트로부터 받은 메시지를 브로드캐스트하는 로직 추가
        broadcastMessage(new TextMessage(username + ": " + userMessage));
        System.out.println("Broadcast from " + username + ": " + userMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트가 연결되었을 때 동작할 로직
        sessions.add(session);
        System.out.println("Connected: " + session.getId());
        System.out.println("Connected: " + session.getLocalAddress());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 클라이언트가 연결을 종료했을 때 동작할 로직
        sessions.remove(session);
        System.out.println("Disconnected: " + session.getId());
    }

    private void broadcastMessage(TextMessage message) throws Exception {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            }
        }
    }
}
