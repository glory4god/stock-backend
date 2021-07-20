package com.stock.spring.web.dto.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GoodOrBadDataResponseDto {
    private Long userId;
    private Long reportId;

    @Builder
    public GoodOrBadDataResponseDto(Long userId, Long reportId) {
        this.userId = userId;
        this.reportId = reportId;

    }

}

