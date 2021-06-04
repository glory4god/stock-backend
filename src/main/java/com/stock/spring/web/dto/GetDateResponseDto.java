package com.stock.spring.web.dto;

import com.stock.spring.domain.data.Data;
import lombok.Getter;

@Getter
public class GetDateResponseDto {
    private String date;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private String companyName;

    public GetDateResponseDto(Data entity) {
        this.date = entity.getDate();
        this.open = entity.getOpen();
        this.high = entity.getHigh();
        this.low = entity.getLow();
        this.close = entity.getClose();
        this.volume = entity.getVolume();
        this.companyName = entity.getCompanyName();
    }
}
