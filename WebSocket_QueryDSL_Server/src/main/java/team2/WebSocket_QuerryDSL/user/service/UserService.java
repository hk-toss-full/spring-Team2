package team2.WebSocket_QuerryDSL.user.service;

import team2.WebSocket_QuerryDSL.user.dto.UserRequest;

import java.util.List;

public interface UserService {
    MessageResponse createUser(UserRequest request);
    MessageResponse getUserById(Long id);
    MessageResponse getUserByName(String name);
    List<MessageResponse> getAllUsers();
}
