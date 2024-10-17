package team2.WebSocket_QuerryDSL.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public MessageResponse createUser(UserRequest request) {
        User entity = request.toEntity();
        userRepository.save(entity);
        return MessageResponse.from(entity);
    }

    @Override
    public MessageResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        return MessageResponse.from(user);
    }

    @Override
    public MessageResponse getUserByName(String name) {
        User user = userRepository.findFirstByName(name);
        return user != null ? MessageResponse.from(user) : null;
    }


    @Override
    public List<MessageResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(MessageResponse::from)
                .toList();
    }
}
