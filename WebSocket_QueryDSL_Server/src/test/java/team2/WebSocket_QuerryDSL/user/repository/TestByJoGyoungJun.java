package team2.WebSocket_QuerryDSL.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.chatroom.service.ChatRoomService;
import team2.WebSocket_QuerryDSL.chatroom.service.ChatRoomServiceImpl;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.service.UserService;
import team2.WebSocket_QuerryDSL.user.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestByJoGyoungJun {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatRoomService chatRoomService;
    @BeforeEach
    void setUp(){
    userService = new UserServiceImpl(userRepository);
    chatRoomService = new ChatRoomServiceImpl(chatRoomRepository);
    }
    @Test
    public void createUsers() {
        User user = User.builder().name("조경준").build();
        userRepository.save(user);

        assertNotNull(user.getName());
        assertEquals("조경준",user.getName());
        assertEquals(1, user.getId());
    }

    @Test
    public void FindUserByName() {
        MessageResponse user = userService.getUserByName("조경준");
        assertNull(user);
    }

    @Test
    public void createCharRoom() {
        ChatRoom chatRoom = ChatRoom.builder().title("백엔드").build();
        chatRoomRepository.save(chatRoom);

        assertNotNull(chatRoom.getTitle());
        assertEquals("백엔드",chatRoom.getTitle());
        assertEquals(1, chatRoom.getId());
    }
}
