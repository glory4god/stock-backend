package com.stock.spring.web.controller;

import com.stock.spring.service.GetService;
import com.stock.spring.web.dto.ConvertCustomDataDto;
import com.stock.spring.web.dto.GetCompanyNameResponseDto;
import com.stock.spring.web.dto.GetDateRangeResponseDto;
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

    @GetMapping("/api/stock/companyname/{market}")
    public List<String> CompanyNameListByMarket(@PathVariable String market) {
        return getService.CompanyNameListByMarket(market);
    }

    @GetMapping("/api/stock/company/{market}")
    public List<GetCompanyNameResponseDto> CompanyListByMarket(@PathVariable String market) {
        return getService.CompanyListByMarket(market);
    }

    @GetMapping("/api/stock/data/condition/{companyName}")
    public List<GetDateResponseDto> dataListByDate(@PathVariable String companyName, @RequestParam(value = "start") String startDate, @RequestParam(value = "end") String endDate) {
        System.out.println(startDate + " " + endDate);
        return getService.dataListByDate(companyName, startDate, endDate);
    }

    @GetMapping("/api/stock/data/{companyName}")
    public List<GetDateRangeResponseDto> dateRangeByCompany(@PathVariable String companyName) {
        return getService.dateRangeByCompany(companyName);
    }

    @GetMapping("/api/stock/data/condition/custom/{companyName}")
    public List<ConvertCustomDataDto> getCustomDataByDate(@PathVariable String companyName, @RequestParam(value = "start") String startDate, @RequestParam(value = "end") String endDate) {
        System.out.println(startDate + " " + endDate);
        return getService.getCustomDataByDate(companyName, startDate, endDate);
    }




}
