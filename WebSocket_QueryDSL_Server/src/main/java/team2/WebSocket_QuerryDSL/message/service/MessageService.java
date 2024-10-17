package team2.WebSocket_QuerryDSL.message.service;

import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse createMessage(MessageRequest request);
    List<MessageResponse> getAllMessages();
    MessageResponse getMessageById(Long id);
    Message toEntity(MessageRequest request);
    List<MessageResponse> getAllMessagesByUserId(Long userId);
    List<MessageResponse> getAllMessagesByChatRoomId(Long chatRoomId);
    List<MessageResponse> getAllMessagesByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}
