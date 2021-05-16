package com.stock.spring.service.get;

import com.stock.spring.domain.company.CompanyRepository;
import com.stock.spring.domain.data.DataRepository;
import com.stock.spring.web.dto.GetCompanyNameResponseDto;
import com.stock.spring.web.dto.GetCompanyResponseDto;
import com.stock.spring.web.dto.GetDateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetService {

    private final CompanyRepository companyRepository;
    private final DataRepository dataRepository;

    @Transactional(readOnly = true)
    public List<GetCompanyNameResponseDto> CompanyNameListByMarket(String market) {
        return companyRepository.findByMarket(market).stream()
                .map(GetCompanyNameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GetCompanyResponseDto> CompanyListByMarket(String market) {
        return companyRepository.findByMarket(market).stream()
                .map(GetCompanyResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GetDateResponseDto> dataListByDate(String startDate, String endDate) {
        return dataRepository.dataListByDate(startDate, endDate).stream()
                .map(GetDateResponseDto::new)
                .collect(Collectors.toList());
    }



}
