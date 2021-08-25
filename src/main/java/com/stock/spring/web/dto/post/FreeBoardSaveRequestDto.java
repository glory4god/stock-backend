package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.freeBoard.FreeBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FreeBoardSaveRequestDto {
    private String username;
    private String title;
    private String content;

    @Builder
    public FreeBoardSaveRequestDto(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;

    }
    public FreeBoard toEntity() {
        return FreeBoard.builder()
                .username(username)
                .content(content)
                .title(title)
                .good(0)
                .views(0)
                .build();
    }


}

