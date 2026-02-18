package com.info.WebSocketDemo.service;

import com.info.WebSocketDemo.dto.ChatMessageDTO;
import com.info.WebSocketDemo.entity.ChatMessage;
import com.info.WebSocketDemo.mapper.ChatMessageMapper;
import com.info.WebSocketDemo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository repository;
    private final ChatMessageMapper mapper;

    public ChatMessageDTO processMessage(ChatMessageDTO request) {

        ChatMessage entity = mapper.toEntity(request);
        ChatMessage saved = repository.save(entity);

        return mapper.toDTO(saved);
    }
}

