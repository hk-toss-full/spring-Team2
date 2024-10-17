package team2.WebSocket_QuerryDSL.chatroom.service;

import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomRequest;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomResponse;

import java.util.List;

public interface ChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomRequest request);
    List<ChatRoomResponse> getAllChatRooms();
    ChatRoomResponse getChatRoomById(Long id);
    ChatRoomResponse getChatRoomByTitle(String title);
}
