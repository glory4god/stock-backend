package com.stock.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConvertCustomDataDto {

    private String date;
    private List<Integer> openClose;
    private List<Integer> lowHigh;

    @Builder
    public ConvertCustomDataDto(String date, int open, int close, int low, int high) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(open);
        list1.add(close);

        List<Integer> list2 = new ArrayList<>();
        list2.add(low);
        list2.add(high);

        this.date = date;
        this.openClose = list1;
        this.lowHigh = list2;

    }

}
