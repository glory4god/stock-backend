package com.stock.spring.web.dto.post;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChartSortedColumnDto {
    private String column;

    @Builder
    public ChartSortedColumnDto(String column) {
        this.column = column;
    }
}
