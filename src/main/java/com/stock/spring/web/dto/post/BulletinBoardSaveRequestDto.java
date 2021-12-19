package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.freeBoard.FreeBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FreeBoardSaveRequestDto {
    private Long userId;
    private String title;
    private String content;

    @Builder
    public FreeBoardSaveRequestDto(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;

    }
    public FreeBoard toEntity() {
        return FreeBoard.builder()
                .userId(userId)
                .content(content)
                .title(title)
                .good(0)
                .views(0)
                .build();
    }


}

