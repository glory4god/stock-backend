package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.report.GoodData;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GoodOrBadDataRequestDto {
    private Long userId;
    private Long reportId;

    public GoodData toEntity() {
        return GoodData.builder()
                .userId(userId)
                .reportId(reportId)
                .build();
    }

}
