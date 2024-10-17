package team2.WebSocket_QuerryDSL.chatroom.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.config.BaseEntity;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Table(name = "CHAT_ROOMS")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
    @Id
    @Column(name = "CHAT_ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "chatRooms")  // User 클래스에서 관리되는 관계
    @Builder.Default
    private List<User> users= new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    @Builder.Default
    private List<Message> messages = new ArrayList<>();
}
