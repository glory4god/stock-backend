package com.stock.spring.web;

import com.stock.spring.service.GetService;
import com.stock.spring.service.NewsGetService;
import com.stock.spring.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final GetService getService;
    private final NewsGetService newsGetService;

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

    @GetMapping("/api/finance/news/{keyword}")
    public Object getNews(@PathVariable String keyword) {
        return newsGetService.getNews(keyword);
    }
}
