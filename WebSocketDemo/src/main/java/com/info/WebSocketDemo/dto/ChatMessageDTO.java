package com.info.WebSocketDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessageDTO {
    private String sender;
    private String content;
}
