package com.stock.spring.web.dto;

import com.stock.spring.domain.company.Company;
import lombok.Getter;

@Getter
public class GetCompanyResponseDto {
    private String name;
    private String market;
    private String companyId;

    public GetCompanyResponseDto(Company entity) {
        this.name = entity.getName();
        this.market = entity.getMarket();
        this.companyId = entity.getCompanyId();
    }
}
