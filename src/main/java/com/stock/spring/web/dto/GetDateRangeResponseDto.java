package com.stock.spring.web.dto;

import com.stock.spring.domain.data.ChartData;
import lombok.Getter;

@Getter
public class GetDateRangeResponseDto {

    private String date;
    private String companyName;

    public GetDateRangeResponseDto(ChartData entity) {
        this.date = entity.getDate();
        this.companyName = entity.getCompanyName();
    }

}
