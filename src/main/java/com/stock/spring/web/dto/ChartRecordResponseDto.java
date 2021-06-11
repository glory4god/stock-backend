package com.stock.spring.web.dto;

import com.stock.spring.domain.user.ChartSearchRecord;
import lombok.Getter;

@Getter
public class ChartRecordResponseDto {

    private String companyName;
    private String value;
    private String graphEffect;
    private String startDate;
    private String endDate;

    public ChartRecordResponseDto(ChartSearchRecord entity) {
        this.companyName = entity.getCompanyName();
        this.value = entity.getValue();
        this.graphEffect = entity.getGraphEffect();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
