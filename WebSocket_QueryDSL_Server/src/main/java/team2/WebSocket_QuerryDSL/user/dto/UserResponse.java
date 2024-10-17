package team2.WebSocket_QuerryDSL.user.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomDto;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        List<ChatRoomDto>chatRooms,
        List<MessageResponse> messages
) {
    public static UserResponse from(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getChatRooms().stream().map(ChatRoomDto::from).toList(),
                user.getMessages().stream().map(MessageResponse::from).toList());
    }
}
