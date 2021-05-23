package com.stock.spring.service;


import com.stock.spring.domain.report.ReportRepository;
import com.stock.spring.web.dto.PostReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final ReportRepository reportRepository;

    @Transactional
    public Long saveReport(PostReportSaveRequestDto requestDto) {
        return reportRepository.save(requestDto.toEntity()).getId();
    }


}
