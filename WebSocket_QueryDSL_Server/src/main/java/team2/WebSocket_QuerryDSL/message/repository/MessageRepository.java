package team2.WebSocket_QuerryDSL.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<MessageResponse> findByChatRoom(ChatRoom chatRoom);
    List<MessageResponse> findByUser(User user);
    List<MessageResponse> findByChatRoomAndUser(ChatRoom chatRoom, User user);
}
