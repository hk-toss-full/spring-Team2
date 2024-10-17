package team2.WebSocket_QuerryDSL.user.service;

import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
}
