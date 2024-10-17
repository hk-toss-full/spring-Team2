package team2.WebSocket_QuerryDSL.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import team2.WebSocket_QuerryDSL.message.service.MessageService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@Component
//@RequiredArgsConstructor
//public class WebSocketHandler extends TextWebSocketHandler {
//
//    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
//
//    private final MessageService messageService;
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        String[] splitPayload = payload.split(":", 2);
//        String username = splitPayload.length > 1 ? splitPayload[0] : "Anonymous";
//        String userMessage = splitPayload.length > 1 ? splitPayload[1] : payload;
//        messageService.createMessage(username, userMessage);
//        // 클라이언트로부터 받은 메시지를 브로드캐스트하는 로직 추가
//        broadcastMessage(new TextMessage(username + ": " + userMessage));
//        System.out.println("Broadcast from " + username + ": " + userMessage);
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // 클라이언트가 연결되었을 때 동작할 로직
//        sessions.add(session);
//        System.out.println("Connected: " + session.getId());
//        System.out.println("Connected: " + session.getLocalAddress());
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        // 클라이언트가 연결을 종료했을 때 동작할 로직
//        sessions.remove(session);
//        System.out.println("Disconnected: " + session.getId());
//    }
//
//    private void broadcastMessage(TextMessage message) throws Exception {
//        synchronized (sessions) {
//            for (WebSocketSession session : sessions) {
//                if (session.isOpen()) {
//                    session.sendMessage(message);
//                }
//            }
//        }
//    }
//}



@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    private final MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();  // JSON 파서를 위한 ObjectMapper 추가

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println(payload);
        try {
            // JSON 데이터를 파싱
            JsonNode jsonNode = objectMapper.readTree(payload);
            Long userId = jsonNode.get("userId").asLong();
            Long chatRoomId = jsonNode.get("chatRoomId").asLong();
            String chatMessage = jsonNode.get("message").asText();
            System.out.println(chatMessage);
//            String username = jsonNode.has("username") ? jsonNode.get("username").asText() : "Anonymous";
//            String userMessage = jsonNode.has("message") ? jsonNode.get("message").asText() : "";
//            int chatRoom = jsonNode.has("chatRoom") ? jsonNode.get("chatRoom").asInt() : 0;

            // 메시지 저장
            messageService.createMessage(userId,chatRoomId, chatMessage);

            // 브로드캐스트 메시지 전송
            broadcastMessage(new TextMessage(payload));
            System.out.println("Broadcast from " + payload);
        } catch (Exception e) {
            System.out.println("Invalid message format: " + payload);
            session.sendMessage(new TextMessage("Error: Invalid message format"));
        }
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