package team2.WebSocket_QuerryDSL.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;

@Component
@RequiredArgsConstructor
public class ChatRoomInit {
    private final ChatRoomRepository chatRoomRepository;

    @Bean
    public void setChatRoomRepository(){
        ChatRoom chatRoom1 = ChatRoom.builder()
                .title("프론트엔드")
                .build();
        ChatRoom chatRoom2 = ChatRoom.builder()
                .title("백엔드")
                .build();
        ChatRoom chatRoom3 = ChatRoom.builder()
                .title("풀스택")
                .build();

        chatRoomRepository.save(chatRoom1);
        chatRoomRepository.save(chatRoom2);
        chatRoomRepository.save(chatRoom3);

    }
}
