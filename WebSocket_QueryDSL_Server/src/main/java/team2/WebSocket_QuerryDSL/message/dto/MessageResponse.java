package team2.WebSocket_QuerryDSL.message.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomDto;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserDto;

public record MessageResponse(
    Long id,
    String message,
    UserDto user,
    ChatRoomDto chatRoom
) {
    public static MessageResponse from(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getMessage(),
                UserDto.from(message.getUser()),
                ChatRoomDto.from(message.getChatRoom())
                );
    }
}
