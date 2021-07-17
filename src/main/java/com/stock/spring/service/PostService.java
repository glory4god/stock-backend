package com.stock.spring.service;

import com.stock.spring.domain.data.report.*;
import com.stock.spring.domain.user.UserRepository;
import com.stock.spring.web.dto.GoodOrBadDataResponseDto;
import com.stock.spring.web.dto.post.ChartReportResponseDto;
import com.stock.spring.web.dto.post.ChartReportSaveRequestDto;
import com.stock.spring.web.dto.post.GoodOrBadDataRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    //    private final ReportRepository reportRepository;
    private final ChartReportRepository chartReportRepository;
    private final UserRepository userRepository;
    private final GoodDataRepository goodDataRepository;
    private final BadDataRepository badDataRepository;

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
    public Long saveChartRecord(ChartReportSaveRequestDto requestDto) {
        return chartReportRepository.save(requestDto.toEntity()).getId();
    }

    // ChartReport 전체 List 조회 날짜 빠른순으로 정렬
    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getChartReportList(String sorted) {
        // Sort.by 방법으로 쿼리문 안짜도 되네 ... 굿..!!!
        return chartReportRepository.findAll(Sort.by(Sort.Direction.DESC, sorted)).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
    }

    // ChartReport id로 조회
    @Transactional(readOnly = true)
    public ChartReportResponseDto getChartReportById(Long id) {
        return new ChartReportResponseDto(chartReportRepository.getReportById(id));
    }

    // 좋아요 up기능(추후에 같은 username 중복 안되게 만들기)
    @Transactional
    public GoodOrBadDataResponseDto updateGoodById(GoodOrBadDataRequestDto requestDto) {
        Long reportId = requestDto.getReportId();
        GoodData entity = goodDataRepository.findByUserIdAndReportId(requestDto.getUserId(), requestDto.getReportId());

        int good = chartReportRepository.getReportById(reportId).getGood();

        if (entity == null) {
            chartReportRepository.updateGoodById(reportId, good + 1);
            GoodData res = GoodData.builder()
                    .userId(requestDto.getUserId())
                    .reportId(reportId)
                    .build();
            goodDataRepository.save(res);
            return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());

        } else {
            chartReportRepository.updateGoodById(reportId, good - 1);
            goodDataRepository.deleteById(entity.getId());
            return new GoodOrBadDataResponseDto(0L, 0L);
        }
    }

    // 싫어요 up기능(추후에 같은 username 중복 안되게 만들기)
    @Transactional
    public GoodOrBadDataResponseDto updateBadById(GoodOrBadDataRequestDto requestDto) {
        Long reportId = requestDto.getReportId();
        BadData entity = badDataRepository.findByUserIdAndReportId(requestDto.getUserId(), requestDto.getReportId());

        int good = chartReportRepository.getReportById(reportId).getGood();

        if (entity == null) {
            chartReportRepository.updateGoodById(reportId, good + 1);
            BadData res = BadData.builder()
                    .userId(requestDto.getUserId())
                    .reportId(reportId)
                    .build();
            badDataRepository.save(res);
            return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());

        } else {
            chartReportRepository.updateGoodById(reportId, good - 1);
            badDataRepository.deleteById(entity.getId());
            return new GoodOrBadDataResponseDto(0L, 0L);
        }
    }

    // 좋아요 or 싫어요 눌러져있는지 체크
    @Transactional
    public Map<String, Boolean> pressedCheck(Long userId, Long reportId) {
        boolean good = goodDataRepository.existsByUserIdAndReportId(userId, reportId);
        boolean bad = badDataRepository.existsByUserIdAndReportId(userId, reportId);

        Map<String, Boolean> result = new HashMap<>();
        result.put("good", good);
        result.put("bad", bad);
        return result;
    }

    // 조회수 up
    @Transactional
    public String updateIncreaseViewsById(Long id) {
        int views = chartReportRepository.getReportById(id).getViews();
        int value = chartReportRepository.updateViewsById(id, views + 1);

        if (value == 1) {
            return "up success!";
        } else {
            return "up failed!";
        }
    }

    // sorted에 column을 넣으면 그 순서로 정렬 시켜줌
    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getSortedByCompany(String companyName, String sorted) {
        return chartReportRepository.findByName(companyName, Sort.by(Sort.Direction.DESC, sorted)).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getSortedAll(String sorted) {
        return chartReportRepository.findAll(Sort.by(Sort.Direction.DESC, sorted)).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
    }


}


