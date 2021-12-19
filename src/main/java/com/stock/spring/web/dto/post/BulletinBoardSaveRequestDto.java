package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.bulletinBoard.BulletinBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BulletinBoardSaveRequestDto {
    private Long userId;
    private String title;
    private String content;

    @Builder
    public BulletinBoardSaveRequestDto(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;

    }
    public BulletinBoard toEntity() {
        return BulletinBoard.builder()
                .userId(userId)
                .content(content)
                .title(title)
                .good(0)
                .views(0)
                .build();
    }


}

