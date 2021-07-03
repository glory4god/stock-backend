package com.stock.spring.websocket.web;

import com.stock.spring.websocket.domain.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MessageRequestDto {
    private String username;
    private String content;

    @Builder
    public MessageRequestDto(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public Message toEntity() {
        return Message.builder()
                .username(username)
                .content(content)
                .build();
    }

}
