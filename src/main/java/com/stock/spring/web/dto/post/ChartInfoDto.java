package com.stock.spring.web.dto.post;

import lombok.Getter;

@Getter
public class ChartInfoDto {
    private String companyName;
    private String value;
    private String graphEffect;
    private String startDate;
    private String endDate;

    public ChartInfoDto(String companyName, String value, String graphEffect, String startDate, String endDate) {
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
