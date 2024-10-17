package team2.WebSocket_QuerryDSL.message.dto;

import lombok.Builder;
import lombok.Getter;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;

@Builder
public record MessageRequest(
        Long id,
        String message,
        User user,
        ChatRoom chatRoom
) {}
