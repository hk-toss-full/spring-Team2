package team2.WebSocket_QuerryDSL.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.config.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "USER_CHAT_ROOM",  // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "USER_ID"),  // 유저 테이블의 조인 컬럼
            inverseJoinColumns = @JoinColumn(name = "CHAT_ROOM_ID")  // 채팅룸 테이블의 조인 컬럼
    )
    @Builder.Default
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Message> messages = new ArrayList<>();
}
