package com.stock.spring.web.dto;

import com.stock.spring.domain.company.Company;
import lombok.Getter;

@Getter
public class GetCompanyNameResponseDto {

    private String companyId;
    private String companyName;

    public GetCompanyNameResponseDto(Company entity) {
        this.companyId = entity.getCompanyId();
        this.companyName = entity.getName();
    }

}
