package com.stock.spring.service;


import com.stock.spring.domain.data.NewsUrlRecordRepository;
import com.stock.spring.domain.user.Report;
import com.stock.spring.domain.user.ReportRepository;
import com.stock.spring.web.dto.DeleteReportRequestDto;
import com.stock.spring.web.dto.PostReportResponseDto;
import com.stock.spring.web.dto.PostReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final ReportRepository reportRepository;
    private final NewsUrlRecordRepository newsUrlRecordRepository;

    @Transactional
    public Long saveReport(PostReportSaveRequestDto requestDto) {
        return reportRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public PostReportResponseDto findReportById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        return new PostReportResponseDto(entity);
    }

    @Transactional
    public Long deleteReport(Long id, DeleteReportRequestDto requestDto) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        if (entity.getPassword().equals(requestDto.getPassword())) {
            reportRepository.deleteById(id);
        } else{
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return id;
    }



}
