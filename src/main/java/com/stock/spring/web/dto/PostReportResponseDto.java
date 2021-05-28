package com.stock.spring.web.dto;

import com.stock.spring.domain.user.Report;
import lombok.Getter;

@Getter
public class PostReportResponseDto {

    private Long id;
    private String userId;
    private String password;
    private String companyName;
    private String startDate;
    private String endDate;

    public PostReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.companyName = entity.getCompanyName();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
