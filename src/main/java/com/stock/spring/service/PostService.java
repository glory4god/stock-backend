package com.stock.spring.service;

import com.stock.spring.domain.user.ChartReportRepository;
import com.stock.spring.domain.user.UserRepository;
import com.stock.spring.web.dto.post.ChartReportResponseDto;
import com.stock.spring.web.dto.post.ChartReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    //    private final ReportRepository reportRepository;
    private final ChartReportRepository chartReportRepository;
    private final UserRepository userRepository;

    // report 관련 (여기 부분 애매 다르게 변경할 필요있음)
//    @Transactional
//    public Long saveReport(PostReportSaveRequestDto requestDto) {
//        return reportRepository.save(requestDto.toEntity()).getId();
//    }
//
//    @Transactional(readOnly = true)
//    public PostReportResponseDto findReportById(Long id) {
//        Report entity = reportRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
//        return new PostReportResponseDto(entity);
//    }
//
//    @Transactional
//    public Long deleteReport(Long id, DeleteReportRequestDto requestDto) {
//        Report entity = reportRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
    // UserInfo 테이븦 정리필요함 ( Id 찾은 후, 그 아이디의 패스워드와 request 패스워드 같은지 비교 )
//        UserInfo user = userRepository.findById(requestDto.getUserId());
//        if (user.getPassword().equals(requestDto.getPassword())) {
//            reportRepository.deleteById(id);
//        } else{
//            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
//        }
//        return id;
//    }

    // ChartReport 관련
    @Transactional
    public Long saveChartSearchRecord(ChartReportSaveRequestDto requestDto) {
        return chartReportRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getChartReportList() {
        List<ChartReportResponseDto> collect = chartReportRepository.findAll(Sort.by(Sort.Direction.DESC,"modifiedDate")).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional(readOnly = true)
    public ChartReportResponseDto getChartReportById (Long id) {
        return new ChartReportResponseDto(chartReportRepository.getReportById(id));
    }
}
