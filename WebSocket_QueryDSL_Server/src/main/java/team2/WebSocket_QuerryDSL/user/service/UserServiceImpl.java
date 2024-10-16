package team2.WebSocket_QuerryDSL.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.chatroom.domain.ChatRoom;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomIdRequest;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomRequest;
import team2.WebSocket_QuerryDSL.chatroom.repository.ChatRoomRepository;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;
import team2.WebSocket_QuerryDSL.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;


    @Override
    public UserResponse createUser(UserRequest request) {
        User entity = request.toEntity();
        userRepository.save(entity);
        return UserResponse.from(entity);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        return UserResponse.from(user);
    }

    @Override
    public UserResponse getUserByName(String name) {
        User user = userRepository.findFirstByName(name);
        return user != null ? UserResponse.from(user) : null;
    }


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    @Override
    public UserResponse updateUserAddChatRoom(Long id, ChatRoomIdRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(request.chatRoomId()).orElseThrow();
        user.addChatRoom(chatRoom);
        user = userRepository.save(user);
        return UserResponse.from(user);
    }
}
