package com.stock.spring.domain.data.report;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "good_data")
public class GoodData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "report_id")
    private Long reportId;

    @Builder
    public GoodData(Long userId, Long reportId) {
        this.userId = userId;
        this.reportId = reportId;
    }

}
