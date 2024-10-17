package team2.WebSocket_QuerryDSL.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import team2.WebSocket_QuerryDSL.message.service.MessageService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;



}
