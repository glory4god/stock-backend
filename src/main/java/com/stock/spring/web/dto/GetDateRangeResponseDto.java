package com.stock.spring.web.dto;

import com.stock.spring.domain.data.Data;
import lombok.Getter;

@Getter
public class GetDateRangeResponseDto {

    private String date;
    private String companyName;

    public GetDateRangeResponseDto(Data entity) {
        this.date = entity.getDate();
        this.companyName = entity.getCompanyName();
    }

}
