package team2.WebSocket_QuerryDSL.chatroom.response;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;

import java.util.List;

public record ChatRoomResponse(
        Long id,
        String title,
        List<User> users,
        List<Message> messages
) {
    public static ChatRoomResponse from(ChatRoom chatRoom) {
        return new ChatRoomResponse(
                chatRoom.getId(),
                chatRoom.getTitle(),
                chatRoom.getUsers(),
                chatRoom.getMessages());
    }
}
