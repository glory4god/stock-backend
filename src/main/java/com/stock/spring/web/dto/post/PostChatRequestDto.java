package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostChatRequestDto {

    private String userName;
    private String content;

    public PostChatRequestDto(Chat entity) {
        this.userName = entity.getUserName();
        this.content = entity.getContent();
    }

    public Chat toEntity() {
        return Chat.builder()
                .userName(userName)
                .content(content)
                .build();
    }
}
