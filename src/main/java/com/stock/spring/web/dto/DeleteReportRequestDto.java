package com.stock.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteReportRequestDto {

    private String userId;
    private String password;

    @Builder
    public DeleteReportRequestDto(String userId, String password ) {
        this.userId = userId;
        this.password = password;
    }

}
