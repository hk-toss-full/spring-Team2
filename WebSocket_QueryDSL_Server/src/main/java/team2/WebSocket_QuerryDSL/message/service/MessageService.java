package team2.WebSocket_QuerryDSL.message.service;

import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse createMessage(MessageRequest messageRequest);
    List<MessageResponse> getAllMessages();
    MessageResponse getMessageById(Long id);
}
