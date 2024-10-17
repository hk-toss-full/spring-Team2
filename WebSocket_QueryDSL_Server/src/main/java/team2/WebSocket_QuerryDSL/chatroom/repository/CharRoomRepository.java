package team2.WebSocket_QuerryDSL.chatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;

public interface CharRoomRepository extends JpaRepository<ChatRoom, Long> {
}
