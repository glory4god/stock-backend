package com.stock.spring.web;

import com.stock.spring.service.GetService;
import com.stock.spring.web.dto.GetCompanyNameResponseDto;
import com.stock.spring.web.dto.GetCompanyResponseDto;
import com.stock.spring.web.dto.GetDateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final GetService getService;

    @GetMapping("api/get/companyname/{market}")
    public List<GetCompanyNameResponseDto> CompanyNameListByMarket(@PathVariable String market) {
        return getService.CompanyNameListByMarket(market);
    }
    @GetMapping("api/get/company/{market}")
    public List<GetCompanyResponseDto> CompanyListByMarket(@PathVariable String market) {
        return getService.CompanyListByMarket(market);
    }

    @GetMapping("api/get/data")
    public List<GetDateResponseDto> dataListByDate(@RequestParam(value = "start") String startDate, @RequestParam(value = "end") String endDate) {
        System.out.println(startDate + " " + endDate);
        return getService.dataListByDate(startDate, endDate);
    }
}
