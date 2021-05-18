package com.stock.spring.service;

import com.stock.spring.domain.company.CompanyRepository;
import com.stock.spring.domain.data.DataRepository;
import com.stock.spring.domain.user.Report;
import com.stock.spring.domain.user.ReportRepository;
import com.stock.spring.web.dto.GetCompanyNameResponseDto;
import com.stock.spring.web.dto.GetCompanyResponseDto;
import com.stock.spring.web.dto.GetDateResponseDto;
import com.stock.spring.web.dto.PostReportResponseDto;
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
    private final ReportRepository reportRepository;

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

    @Transactional(readOnly = true)
    public PostReportResponseDto findReportById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        return new PostReportResponseDto(entity);
    }

}
