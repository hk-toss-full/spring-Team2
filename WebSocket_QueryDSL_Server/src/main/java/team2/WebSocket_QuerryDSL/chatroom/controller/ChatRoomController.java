package team2.WebSocket_QuerryDSL.chatroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomRequest;
import team2.WebSocket_QuerryDSL.chatroom.dto.ChatRoomResponse;
import team2.WebSocket_QuerryDSL.chatroom.service.ChatRoomService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/api/v1/chatrooms")
    public ChatRoomResponse save(
            @RequestBody ChatRoomRequest chatRoomRequest
    ) {
        return chatRoomService.createChatRoom(chatRoomRequest);
    }

    @GetMapping("/api/v1/chatrooms")
    public List<ChatRoomResponse> getAll(@RequestParam(required = false) String title) {
        if (title != null) {
            ChatRoomResponse response = chatRoomService.getChatRoomByTitle(title);
            return response != null ? List.of(response) : Collections.emptyList();
        }
        return chatRoomService.getAllChatRooms();
    }
}
