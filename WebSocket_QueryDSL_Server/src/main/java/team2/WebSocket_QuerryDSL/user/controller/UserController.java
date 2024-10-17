package team2.WebSocket_QuerryDSL.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.dto.UserResponse;
import team2.WebSocket_QuerryDSL.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public UserResponse save(
            @RequestBody UserRequest request
    ){
        return userService.createUser(request);
    }

    @GetMapping("/api/v1/users")
    public List<UserResponse> getAll(){
        return userService.getAllUsers();
    }


}