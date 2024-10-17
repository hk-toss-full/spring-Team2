package team2.WebSocket_QuerryDSL.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.message.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
