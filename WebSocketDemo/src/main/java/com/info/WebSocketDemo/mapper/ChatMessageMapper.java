package com.info.WebSocketDemo.mapper;

import com.info.WebSocketDemo.dto.ChatMessageDTO;
import com.info.WebSocketDemo.entity.ChatMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatMessageMapper {

    public ChatMessage toEntity(ChatMessageDTO dto) {
        return ChatMessage.builder()
                .sender(dto.getSender())
                .content(dto.getContent())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ChatMessageDTO toDTO(ChatMessage entity) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setSender(entity.getSender());
        dto.setContent(entity.getContent());
        return dto;
    }
}
