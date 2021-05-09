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

    @Builder
    public Data(String date, int high, int low, int open, int close) {
        this.date = date;
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
    }
}
