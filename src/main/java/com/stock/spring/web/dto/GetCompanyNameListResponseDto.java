package com.stock.spring.web.dto;

import com.stock.spring.domain.company.Company;
import lombok.Getter;

@Getter
public class GetCompanyNameListResponseDto {
    private String companyName;

    public GetCompanyNameListResponseDto(Company entity) {
        this.companyName = entity.getName();
    }
}
