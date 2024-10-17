package team2.WebSocket_QuerryDSL.message.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.repository.UserRepository;

@Builder
public record MessageRequest(
        String message,
        Long userId,
        Long chatRoomId
) {
}
