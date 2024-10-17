package team2.WebSocket_QuerryDSL.message.service;

import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    String createMessage(Long userId, Long CharRoomId, String userMessage);
    List<MessageResponse> getAllMessages();
    MessageResponse getMessageById(Long id);
}
