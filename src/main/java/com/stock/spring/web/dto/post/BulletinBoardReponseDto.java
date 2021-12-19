package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.bulletinBoard.BulletinBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BulletinBoardReponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int good;
    private int views;

    public BulletinBoardReponseDto(BulletinBoard entity, String nickname) {
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
