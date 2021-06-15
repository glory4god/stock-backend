package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.Chat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetChatResponseDto {
    private String userName;
    private String content;
    private LocalDateTime createDate;

    public GetChatResponseDto(Chat entity) {
        this.userName = entity.getUserName();
        this.content = entity.getContent();
        this.createDate = entity.getCreateDate();
    }
}
