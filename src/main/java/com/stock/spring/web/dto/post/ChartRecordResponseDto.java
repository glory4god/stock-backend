package com.stock.spring.web.dto.post;

import com.stock.spring.domain.user.ChartSearchRecord;
import lombok.Getter;

@Getter
public class ChartRecordResponseDto {

    private Long id;
    private String companyName;
    private String value;
    private String graphEffect;
    private String startDate;
    private String endDate;
    private String title;
    private String content;

    public ChartRecordResponseDto(ChartSearchRecord entity) {
        this.companyName = entity.getCompanyName();
        this.value = entity.getValue();
        this.graphEffect = entity.getGraphEffect();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
