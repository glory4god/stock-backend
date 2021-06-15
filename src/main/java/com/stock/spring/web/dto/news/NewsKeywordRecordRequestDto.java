package com.stock.spring.web.dto.news;

import com.stock.spring.domain.data.NewsKeywordRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewsKeywordRecordRequestDto {
    private String keyword;

    @Builder
    public NewsKeywordRecordRequestDto(String keyword) {
        this.keyword = keyword;
    }

    public NewsKeywordRecord toEntity() {
        return NewsKeywordRecord.builder()
                .keyword(keyword)
                .build();

    }
}
