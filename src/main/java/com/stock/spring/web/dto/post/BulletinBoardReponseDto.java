package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.freeBoard.FreeBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FreeBoardReponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int good;
    private int views;

    public FreeBoardReponseDto(FreeBoard entity, String nickname) {
        this.id = entity.getId();
        this.username = nickname;
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.good = entity.getGood();
        this.views = entity.getViews();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
