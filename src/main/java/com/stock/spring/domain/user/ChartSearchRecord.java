package com.stock.spring.domain.user;


import com.stock.spring.domain.BaseTimeCreateTImeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "chart_search_record")
public class ChartSearchRecord extends BaseTimeCreateTImeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @Builder
    public ChartSearchRecord(String companyName, String value, String graphEffect, String startDate, String endDate,String title, String content) {
        this.companyName = companyName;
        this.value = value;
        this.graphEffect = graphEffect;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.content = content;
    }
}
