package team2.WebSocket_QuerryDSL.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.message.repository.MessageRepository;
import team2.WebSocket_QuerryDSL.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public String createMessage(Long userId, Long chatRoomId , String userMessage) {
        Message message = Message.builder()
                .user(userRepository.findById(userId).orElseThrow())
                .chatRoom(chatRoomRepository.findById(chatRoomId).orElseThrow())
                .message(userMessage)
                .build();
        messageRepository.save(message);

        return "okey";
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
