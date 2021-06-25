package com.stock.spring.web.dto.post;

import com.stock.spring.domain.user.Report;
import lombok.Getter;

@Getter
public class PostReportResponseDto {

    private Long id;
    private String userId;
    private String title;
    private String content;

    public PostReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
