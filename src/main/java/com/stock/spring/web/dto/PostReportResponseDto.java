package com.stock.spring.web.dto;

import com.stock.spring.domain.report.Report;
import lombok.Getter;

@Getter
public class PostReportResponseDto {

    private Long id;
    private String userId;
    private String companyName;
    private String startDate;
    private String endDate;

    public PostReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.companyName = entity.getCompanyName();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
