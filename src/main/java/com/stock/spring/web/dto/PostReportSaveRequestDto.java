package com.stock.spring.web.dto;

import com.stock.spring.domain.report.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostReportSaveRequestDto {

    private String userId;
    private String companyName;
    private String startDate;
    private String endDate;

    @Builder
    public PostReportSaveRequestDto(Report entity) {
        this.userId = entity.getUserId();
        this.companyName = entity.getCompanyName();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }

    public Report toEntity() {
        return Report.builder()
                .userId(userId)
                .companyName(companyName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
