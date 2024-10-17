package team2.WebSocket_QuerryDSL.user.dto;

import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.util.List;

public record UserRequest(
        String name
) {
    public User toEntity(){
        return User.builder()
                .name(name)
                .build();
    }
}