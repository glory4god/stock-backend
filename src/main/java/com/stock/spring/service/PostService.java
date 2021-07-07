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
    // ChartReport save 
    @Transactional
    public Long saveChartSearchRecord(ChartReportSaveRequestDto requestDto) {
        return chartReportRepository.save(requestDto.toEntity()).getId();
    }
    // ChartReport 전체 List 조회 날짜 빠른순으로 정렬
    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getChartReportList() {
        // Sort.by 방법으로 쿼리문 안짜도 되네 ... 굿..!!!
        List<ChartReportResponseDto> collect = chartReportRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedDate")).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
        return collect;
    }
    // ChartReport id로 조회
    @Transactional(readOnly = true)
    public ChartReportResponseDto getChartReportById(Long id) {
        return new ChartReportResponseDto(chartReportRepository.getReportById(id));
    }

    // 좋아요 up기능(추후에 같은 username 중복 안되게 만들기)
    @Transactional
    public String updateGoodById(Long id, String value) {
        int good = chartReportRepository.getReportById(id).getGood();
        if (value.equals("up")){
            int result =  chartReportRepository.updateGoodById(id, good + 1);
            return "up success";
        } else if (value.equals("down")){
            int result =  chartReportRepository.updateGoodById(id, good - 1);
            return "down success";
        } else {
            return "only up & down query";
        }
    }

    // 싫어요 up기능(추후에 같은 username 중복 안되게 만들기)
    @Transactional
    public String updateBadById(Long id, String value) {
        int bad = chartReportRepository.getReportById(id).getBad();
        if (value.equals("up")){
            int result =  chartReportRepository.updateBadById(id, bad + 1);
            return "up success";
        } else if (value.equals("down")){
            int result =  chartReportRepository.updateBadById(id, bad - 1);
            return "down success";
        } else {
            return "only up & down query";
        }
    }

    // 조회수 up
    @Transactional
    public String updateIncreaseViewsById(Long id) {
        int views = chartReportRepository.getReportById(id).getViews();
        int value = chartReportRepository.updateViewsById(id, views + 1);

        if(value==1){
            return "up success!";
        } else {
            return "up failed!";
        }
    }



}
