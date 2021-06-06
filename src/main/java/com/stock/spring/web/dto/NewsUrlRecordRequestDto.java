package com.stock.spring.web.dto;

import com.stock.spring.domain.data.NewsUrlRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NewsUrlRecordRequestDto {

    private String url;

    @Builder
    public NewsUrlRecordRequestDto(String url) {
        this.url = url;
    }

    public NewsUrlRecord toEntity() {
        return NewsUrlRecord.builder()
                .url(url)
                .build();
    }

}
