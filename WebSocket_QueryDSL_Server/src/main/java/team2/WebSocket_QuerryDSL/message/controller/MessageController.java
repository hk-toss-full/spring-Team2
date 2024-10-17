package team2.WebSocket_QuerryDSL.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.WebSocket_QuerryDSL.message.dto.MessageRequest;
import team2.WebSocket_QuerryDSL.message.dto.MessageResponse;
import team2.WebSocket_QuerryDSL.message.repository.MessageRepository;
import team2.WebSocket_QuerryDSL.message.service.MessageService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;
    private final MessageRepository messageRepository;

    @PostMapping("/api/v1/messages")
    public team2.WebSocket_QuerryDSL.message.dto.MessageResponse save(
            @RequestBody MessageRequest request
    ){
        return messageService.createMessage(request);
    }

    @GetMapping("/api/v1/messages")
    public List<MessageResponse> getAll(@RequestParam(required = false) Long userId, Long chatRoomId) {
        if (userId != null && chatRoomId != null) {
            return messageService.getAllMessagesByChatRoomIdAndUserId(chatRoomId, userId);
        }
        if (userId != null) {
            return messageService.getAllMessagesByUserId(userId);
        }
        if (chatRoomId != null) {
            return messageService.getAllMessagesByChatRoomId(chatRoomId);
        }
        return messageService.getAllMessages(); // 전체 사용자 조회
    }


}
