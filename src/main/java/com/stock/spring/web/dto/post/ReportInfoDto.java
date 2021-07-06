package com.stock.spring.web.dto.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportInfoDto {
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private int good;
    private int bad;
    private int views;

    public ReportInfoDto(String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate, int good
            , int bad, int views) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.good = good;
        this.bad = bad;
        this.views = views;

    }
}
