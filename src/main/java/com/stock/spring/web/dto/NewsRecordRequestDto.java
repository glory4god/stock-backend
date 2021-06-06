package com.stock.spring.web.dto;

import com.stock.spring.domain.data.NewsRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewsRecordRequestDto {
    private String keyword;

    @Builder
    public NewsRecordRequestDto(String keyword) {
        this.keyword = keyword;
    }

    public NewsRecord toEntity() {
        return NewsRecord.builder()
                .keyword(keyword)
                .build();

    }
}
