package com.stock.spring.web.dto.post;

import com.stock.spring.domain.user.ChartSearchRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ChartRecordRequestDto {
    private String companyName;
    private String value;
    private String graphEffect;
    private String startDate;
    private String endDate;
    private String title;
    private String content;

    @Builder
    public ChartRecordRequestDto(String companyName, String value, String graphEffect, String startDate, String endDate, String title, String content) {
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    public ChartSearchRecord toEntity() {
        return ChartSearchRecord.builder()
                .companyName(companyName)
                .value(value)
                .graphEffect(graphEffect)
                .startDate(startDate)
                .endDate(endDate)
                .title(title)
                .content(content)
                .build();
    }

}
