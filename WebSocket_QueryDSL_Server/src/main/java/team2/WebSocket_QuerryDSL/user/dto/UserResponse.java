package team2.WebSocket_QuerryDSL.user.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        List<ChatRoom>chatRooms,
        List<Message> messages
) {
    public static UserResponse from(User user) {

        return new UserResponse(user.getId(), user.getName(), user.getChatRooms(), user.getMessages());
    }
}
