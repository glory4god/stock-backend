package com.stock.spring.web.dto.news;

import com.stock.spring.domain.data.news.NewsKeywordRecord;
import lombok.Getter;

@Getter
public class NewsKeywordRecordResponseDto {

    private String keyword;

    public NewsKeywordRecordResponseDto(NewsKeywordRecord entity) {
        this.keyword = entity.getKeyword();
    }

}
