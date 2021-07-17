package com.stock.spring.web.dto.news;

import com.stock.spring.domain.data.news.NewsUrlRecord;
import lombok.Getter;

@Getter
public class NewsUrlRecordResponseDto {

    private String title;
    private String link;
    private String originallink;
    private String description;
    private String pubDate;
    private String keyword;
    private String imageUrl;

    public NewsUrlRecordResponseDto(NewsUrlRecord entity) {
        this.title = entity.getTitle();
        this.link = entity.getLink();
        this.originallink = entity.getOriginallink();
        this.description = entity.getDescription();
        this.pubDate = entity.getPubDate();
        this.keyword = entity.getKeyword();
        this.imageUrl = entity.getImageUrl();
    }
}
