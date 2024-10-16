package team2.WebSocket_QuerryDSL.message.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.config.BaseEntity;
import team2.WebSocket_QuerryDSL.user.domain.User;

@Entity
@Table(name = "MESSAGES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MESSAGE")
    private String message;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;
}
