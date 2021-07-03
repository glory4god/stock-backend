package com.stock.spring.websocket.web;

import lombok.Getter;

@Getter
public class MessageResponseDto {
    private String username;
    private String content;
    private String date;

    public MessageResponseDto(String username, String content, String date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }
}
