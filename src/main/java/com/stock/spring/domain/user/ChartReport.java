package com.stock.spring.domain.user;


import com.stock.spring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "chart_report")
public class ChartReport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String username;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "graph_effect", nullable = false)
    private String graphEffect;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "good", nullable = false)
    private int good;

    @Column(name = "bad", nullable = false)
    private int bad;

    @Column(name = "views", nullable = false)
    private int views;


    @Builder
    public ChartReport(String username, String companyName, String value, String graphEffect,
                       String startDate, String endDate, String title, String content, int good, int bad, int views) {
        this.username = username;
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
        this.good = good;
        this.bad = bad;
        this.views = views;
    }
}
