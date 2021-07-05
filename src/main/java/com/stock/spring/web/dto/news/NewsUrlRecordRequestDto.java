package com.stock.spring.web.dto.news;

import com.stock.spring.domain.data.NewsUrlRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewsUrlRecordRequestDto {

    private String title;
    private String link;
    private String originallink;
    private String pubDate;
    private String description;
    private String keyword;

    @Builder
    public NewsUrlRecordRequestDto(String title, String link,String originallink,String pubDate, String description, String keyword) {
        this.title = title;
        this.link = link;
        this.originallink = originallink;
        this.description = description;
        this.pubDate = pubDate;
        this.keyword = keyword;
    }

    public NewsUrlRecord toEntity(String imageUrl) {
        return NewsUrlRecord.builder()
                .title(title)
                .link(link)
                .originallink(originallink)
                .pubDate(pubDate)
                .description(description)
                .keyword(keyword)
                .imageUrl(imageUrl)
                .build();
    }

}
