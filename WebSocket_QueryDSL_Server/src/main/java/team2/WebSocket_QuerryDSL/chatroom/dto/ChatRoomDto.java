package team2.WebSocket_QuerryDSL.chatroom.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserDto;

public record ChatRoomDto (
        Long id,
        String title
) {
    public static ChatRoomDto from(ChatRoom chatRoom) {
        return new ChatRoomDto(chatRoom.getId(), chatRoom.getTitle());
    }
}
