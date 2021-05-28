package com.stock.spring.web.dto;

import com.stock.spring.domain.user.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostReportSaveRequestDto {

    private String userId;
    private String password;
    private String companyName;
    private String startDate;
    private String endDate;

    @Builder
    public PostReportSaveRequestDto(String userId, String password, String companyName, String startDate, String endDate) {
        this.userId = userId;
        this.password = password;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Report toEntity() {
        return Report.builder()
                .userId(userId)
                .password(password)
                .companyName(companyName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
