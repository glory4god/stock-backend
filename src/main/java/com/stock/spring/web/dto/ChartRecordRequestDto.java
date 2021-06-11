package com.stock.spring.web.dto;

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

    @Builder
    public ChartRecordRequestDto(String companyName, String value, String graphEffect, String startDate, String endDate) {
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ChartSearchRecord toEntity() {
        return ChartSearchRecord.builder()
                .companyName(companyName)
                .value(value)
                .graphEffect(graphEffect)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
