package team2.WebSocket_QuerryDSL.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.service.MessageService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
            // 브로드캐스트 메시지 전송
            broadcastMessage(new TextMessage(payload));
            // JSON 데이터를 파싱
            JsonNode jsonNode = objectMapper.readTree(payload);
            Long userId = jsonNode.get("userId").asLong();
            Long chatRoomId = jsonNode.get("chatRoomId").asLong();
            String chatMessage = jsonNode.get("message").asText();
            MessageRequest build = MessageRequest.builder()
                                                 .message(chatMessage)
                                                 .userId(userId)
                                                 .chatRoomId(chatRoomId)
                                                 .build();
            // 메시지 저장
            messageService.createMessage(build);
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