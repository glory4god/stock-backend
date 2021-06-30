package com.stock.spring.service;

import com.stock.spring.domain.company.CompanyRepository;
import com.stock.spring.domain.data.Data;
import com.stock.spring.domain.data.DataRepository;
import com.stock.spring.web.dto.ConvertCustomDataDto;
import com.stock.spring.web.dto.GetCompanyNameResponseDto;
import com.stock.spring.web.dto.GetDateRangeResponseDto;
import com.stock.spring.web.dto.GetDateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetService {

    private final CompanyRepository companyRepository;
    private final DataRepository dataRepository;

    @Transactional(readOnly = true)
    public List<String> CompanyNameListByMarket(String market) {
        List<String> collect = companyRepository.findByMarket(market).stream()
                .map((c) -> c.getName())
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional(readOnly = true)
    public List<GetCompanyNameResponseDto> CompanyListByMarket(String market) {
        return companyRepository.findByMarket(market).stream()
                .map(GetCompanyNameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GetDateResponseDto> dataListByDate(String companyName, String startDate, String endDate) {
        List<GetDateResponseDto> list = dataRepository.dataListByDate(companyName, startDate, endDate).stream()
                .map(GetDateResponseDto::new)
                .collect(Collectors.toList());
        return list;
    }

    @Transactional(readOnly = true)
    public List<GetDateRangeResponseDto> dateRangeByCompany(String companyName) {
        Data start = dataRepository.dateStartByCompany(companyName);
        Data end = dataRepository.dateEndByCompany(companyName);

        List<GetDateRangeResponseDto> range = new ArrayList<>();
        range.add(new GetDateRangeResponseDto(start));
        range.add(new GetDateRangeResponseDto(end));

        return range;
    }

    @Transactional(readOnly = true)
    public List<ConvertCustomDataDto> getCustomDataByDate(String companyName, String startDate, String endDate) {
        List<Data> entity =  dataRepository.dataListByDate(companyName, startDate, endDate);
        return entity.stream().map((data) -> ConvertCustomDataDto.builder()
                .date(data.getDate())
                .open(data.getOpen())
                .close(data.getClose())
                .low(data.getLow())
                .high(data.getHigh())
                .build()
        ).collect(Collectors.toList());

    }
}
