package team2.WebSocket_QuerryDSL.user.service;

import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomIdRequest;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
    UserResponse getUserByName(String name);
    List<UserResponse> getAllUsers();
    UserResponse updateUserAddChatRoom(Long id, ChatRoomIdRequest request);
}
