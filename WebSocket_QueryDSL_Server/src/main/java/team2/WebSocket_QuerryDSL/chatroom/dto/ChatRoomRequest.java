package team2.WebSocket_QuerryDSL.chatroom.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;

import java.util.ArrayList;

public record ChatRoomRequest(
        String title
) {
    public ChatRoom toEntity() {
        return ChatRoom.builder().title(title).build();
    }
}
