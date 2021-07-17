package com.stock.spring.web.dto.post;

import com.stock.spring.domain.data.report.ChartReport;
import lombok.Getter;

@Getter
public class ChartReportResponseDto {

    private Long id;
    private String username;
    private ChartInfoDto chart;
    private ReportInfoDto report;

    public ChartReportResponseDto(ChartReport entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.chart = new ChartInfoDto(entity.getCompanyName(), entity.getValue(), entity.getGraphEffect(), entity.getStartDate(), entity.getEndDate());
        this.report = new ReportInfoDto(entity.getTitle(), entity.getContent(), entity.getCreatedDate(), entity.getModifiedDate(), entity.getGood(), entity.getBad(), entity.getViews());
    }

}
