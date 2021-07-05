package com.stock.spring.web.dto.post;

import com.stock.spring.domain.user.ChartReport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ChartReportRequestDto {
    private String username;
    private String companyName;
    private String value;
    private String graphEffect;
    private String startDate;
    private String endDate;
    private String title;
    private String content;

    @Builder
    public ChartReportRequestDto(String username, String companyName, String value, String graphEffect, String startDate, String endDate, String title, String content) {
        this.username = username;
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }

    public ChartReport toEntity() {
        return ChartReport.builder()
                .username(username)
                .companyName(companyName)
                .value(value)
                .graphEffect(graphEffect)
                .startDate(startDate)
                .endDate(endDate)
                .title(title)
                .content(content)
                .good(0)
                .bad(0)
                .build();
    }

}
