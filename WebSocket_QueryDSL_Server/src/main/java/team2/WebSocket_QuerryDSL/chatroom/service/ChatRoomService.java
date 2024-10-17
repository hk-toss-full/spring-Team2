package team2.WebSocket_QuerryDSL.chatroom.service;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoom getChatRoom(String roomName);
    ChatRoom createChatRoom(ChatRoom chatRoom);
    ChatRoom updateChatRoom(ChatRoom chatRoom);
    List<ChatRoom> getAllChatRooms();
}
