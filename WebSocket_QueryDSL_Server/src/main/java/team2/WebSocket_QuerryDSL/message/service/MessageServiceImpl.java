package team2.WebSocket_QuerryDSL.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.message.repository.MessageRepository;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public MessageResponse createMessage(MessageRequest request) {
        Message message = toEntity(request);
        messageRepository.save(message);
        return MessageResponse.from(message);
    }

    @Override
    public List<MessageResponse> getAllMessages() {
        return messageRepository.findAll().stream().map(MessageResponse::from).toList();
    }

    @Override
    public MessageResponse getMessageById(Long id) {
        Message message = messageRepository.findById(id).orElseThrow();
        return MessageResponse.from(message);
    }

    @Override
    public Message toEntity(MessageRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(request.chatRoomId()).orElseThrow();

        return Message
                .builder()
                .message(request.message())
                .user(user)
                .chatRoom(chatRoom)
                .build();
    }

    @Override
    public List<MessageResponse> getAllMessagesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return messageRepository.findByUser(user);
    }

    @Override
    public List<MessageResponse> getAllMessagesByChatRoomId(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        return messageRepository.findByChatRoom(chatRoom);
    }

    @Override
    public List<MessageResponse> getAllMessagesByChatRoomIdAndUserId(Long chatRoomId, Long userId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        return messageRepository.findByChatRoomAndUser(chatRoom, user);
    }
}
