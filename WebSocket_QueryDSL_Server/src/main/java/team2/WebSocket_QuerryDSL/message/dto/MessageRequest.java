package team2.WebSocket_QuerryDSL.message.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;

public record MessageRequest(
        Long id,
        String message,
        User user,
        ChatRoom CharRoom
) {
    public Message toEntity() {
        return new Message(id, message, user, CharRoom);
    }
}
