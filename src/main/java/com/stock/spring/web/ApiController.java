package com.stock.spring.web;

import com.stock.spring.service.get.GetService;
import com.stock.spring.web.dto.GetCompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final GetService getService;

    @GetMapping("api/get/company/{market}")
    public List<GetCompanyResponseDto> findByMarket(@PathVariable String market) {
        return getService.findByMarket(market);
    }



}
