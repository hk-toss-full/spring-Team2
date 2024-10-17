package team2.WebSocket_QuerryDSL.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.WebSocket_QuerryDSL.user.dto.UserRequest;
import team2.WebSocket_QuerryDSL.user.service.UserService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public MessageResponse save(
            @RequestBody UserRequest request
    ){
        return userService.createUser(request);
    }

    @GetMapping("/api/v1/users")
    public List<MessageResponse> getAll(@RequestParam(required = false) String name) {
        if (name != null) {
            MessageResponse userResponse = userService.getUserByName(name);
            return userResponse != null ? List.of(userResponse) : Collections.emptyList();
        }
        return userService.getAllUsers(); // 전체 사용자 조회
    }


}