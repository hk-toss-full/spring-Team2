package team2.WebSocket_QuerryDSL.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.message.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    @Override
    public MessageResponse createMessage(MessageRequest messageRequest) {
        Message entity = messageRequest.toEntity();
        return null;
    }

    @Override
    public List<MessageResponse> getAllMessages() {
        return List.of();
    }

    @Override
    public MessageResponse getMessageById(Long id) {
        return null;
    }
}
