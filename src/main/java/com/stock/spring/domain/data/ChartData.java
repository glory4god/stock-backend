package com.stock.spring.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "chart_data")
public class ChartData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "open", nullable = false)
    private int open;

    @Column(name = "high", nullable = false)
    private int high;

    @Column(name = "low",nullable = false)
    private int low;

    @Column(name = "close", nullable = false)
    private int close;

    @Column(name = "volume", nullable = false)
    private int volume;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Builder
    public ChartData(String date, int high, int low, int open, int close, int volume, String companyName) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.companyName = companyName;
    }
}
