package com.stock.spring.web.dto.post;

import com.stock.spring.domain.user.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostReportSaveRequestDto {

    private String userId;
    private String title;
    private String content;

    @Builder
    public PostReportSaveRequestDto(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Report toEntity() {
        return Report.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }

}
