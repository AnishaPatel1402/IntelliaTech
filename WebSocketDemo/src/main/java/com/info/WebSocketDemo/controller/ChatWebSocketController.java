package com.info.WebSocketDemo.controller;

import com.info.WebSocketDemo.dto.ChatMessageDTO;
import com.info.WebSocketDemo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final ChatService chatService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(ChatMessageDTO request) {
        return chatService.processMessage(request);
    }
}
