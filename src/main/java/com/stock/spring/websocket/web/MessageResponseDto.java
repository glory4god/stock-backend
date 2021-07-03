package com.stock.spring.websocket.web;

import com.stock.spring.websocket.domain.Message;
import lombok.Getter;

@Getter
public class MessageResponseDto {
    private String username;
    private String content;
    private String date;

    public MessageResponseDto(Message entity) {
        this.username = entity.getUsername();
        this.content = entity.getContent();
        this.date = entity.getDate();
    }
}
