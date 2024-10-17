package team2.WebSocket_QuerryDSL.chatroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomRequest;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomResponse;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    @Override
    public ChatRoomResponse createChatRoom(ChatRoomRequest request) {
        ChatRoom chatRoom = request.toEntity();
        chatRoomRepository.save(chatRoom);
        return ChatRoomResponse.from(chatRoom);
    }

    @Override
    public List<ChatRoomResponse> getAllChatRooms() {
        return  chatRoomRepository
                .findAll()
                .stream()
                .map(ChatRoomResponse::from)
                .toList();
    }

    @Override
    public ChatRoomResponse getChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow();
        return ChatRoomResponse.from(chatRoom);
    }

    @Override
    public ChatRoomResponse getChatRoomByTitle(String title) {
        ChatRoom byName = chatRoomRepository.findFirstByTitle(title);
        return ChatRoomResponse.from(byName);
    }
}
