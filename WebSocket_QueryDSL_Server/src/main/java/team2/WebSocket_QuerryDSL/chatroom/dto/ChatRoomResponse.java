package team2.WebSocket_QuerryDSL.chatroom.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserDto;

import java.util.List;

public record ChatRoomResponse(
        Long id,
        String title,
        List<UserDto> users,
        List<MessageResponse> messages
) {
    public static ChatRoomResponse from(ChatRoom chatRoom) {
        return new ChatRoomResponse(
                chatRoom.getId(),
                chatRoom.getTitle(),
                chatRoom.getUsers().stream().map(UserDto::from).toList(),
                chatRoom.getMessages().stream().map(MessageResponse::from).toList());
    }
}
