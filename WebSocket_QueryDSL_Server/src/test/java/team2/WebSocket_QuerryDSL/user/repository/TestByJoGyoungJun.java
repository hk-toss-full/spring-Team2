package team2.WebSocket_QuerryDSL.user.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;
import team2.WebSocket_QuerryDSL.user.service.UserService;
import team2.WebSocket_QuerryDSL.user.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestByJoGyoungJun {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @BeforeEach
    void setUp(){
    userService = new UserServiceImpl(userRepository);
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
        UserResponse user = userService.getUserByName("조경준");
        assertNull(user);
    }
}
