package com.stock.spring.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "high", nullable = false)
    private int high;

    @Column(name = "low",nullable = false)
    private int low;

    @Column(name = "open", nullable = false)
    private int open;

    @Column(name = "close", nullable = false)
    private int close;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Builder
    public Data(String date, int high, int low, int open, int close, String companyName) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.companyName = companyName;
    }
}
