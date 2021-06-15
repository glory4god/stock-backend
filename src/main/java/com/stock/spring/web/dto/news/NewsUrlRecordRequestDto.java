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

    @Builder
    public NewsUrlRecordRequestDto(String title, String link,String originallink,String pubDate, String description) {
        this.title = title;
        this.link = link;
        this.originallink = originallink;
        this.description = description;
        this.pubDate = pubDate;
    }

    public NewsUrlRecord toEntity() {
        return NewsUrlRecord.builder()
                .title(title)
                .link(link)
                .originallink(originallink)
                .pubDate(pubDate)
                .description(description)
                .build();
    }

}
