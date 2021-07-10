package com.stock.spring.web.controller;

import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.post.ChartReportResponseDto;
import com.stock.spring.web.dto.post.ChartReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    // report 관련 POST
//    @PostMapping("/api/stock/report/post")
//    public PostReportResponseDto saveReport(@RequestBody PostReportSaveRequestDto requestDto) {
//        Long id = postService.saveReport(requestDto);
//        return postService.findReportById(id);
//    }
//
//    @GetMapping("/api/stock/report/post/{reportId}")
//    public PostReportResponseDto findReportById(@PathVariable Long reportId) {
//        return postService.findReportById(reportId);
//    }
//
//    @DeleteMapping("/api/stock/report/post/{reportId}")
//    public Long deleteReport(@PathVariable Long reportId, @RequestBody DeleteReportRequestDto requestDto) {
//        return postService.deleteReport(reportId, requestDto);
//    }

    // report POST
    @PostMapping("/api/v1/user/chart-report/post")
    public Long saveChartRecord(@RequestBody ChartReportSaveRequestDto requestDto) {
        return postService.saveChartRecord(requestDto);
    }

    // report 전체 List 조회
    @GetMapping("/api/v1/user/chart-report/sort-all")
    public List<ChartReportResponseDto> getChartRecordList(@RequestParam(value = "sorted") String sorted) {
        return postService.getChartReportList(sorted);
    }

    // report id로 조회
    @GetMapping("/api/v1/user/chart-report/{id}")
    public ChartReportResponseDto getChartById(@PathVariable Long id) {
        return postService.getChartReportById(id);
    }


    // 좋아요 기능 (추후에 같은 아이디로 추천하면 안되게 변경)
    @PatchMapping("/api/v1/user/chart-report/good/{id}")
    public  Map<String, String> updateGoodById(@PathVariable Long id, @RequestParam(value = "value") String value) {
        Map<String, String> result = new HashMap<>();
        result.put("result",postService.updateGoodById(id ,value));
        return result;
    }

    // 싫어요 기능 (추후에 같은 아이디로 추천하면 안되게 변경)
    @PatchMapping("/api/v1/user/chart-report/bad/{id}")
    public  Map<String, String> updateBadById(@PathVariable Long id,  @RequestParam(value = "value") String value) {
        Map<String, String> result = new HashMap<>();
        result.put("result", postService.updateBadById(id , value));
        return result;
    }

    // 조회수 기능
    @PatchMapping("/api/v1/user/chart-report/views/{id}")
    public Map<String, String> updateIncreaseViewsById(@PathVariable Long id) {
        Map<String, String> result = new HashMap<>();
        result.put("result", postService.updateIncreaseViewsById(id));
        return result;
    }

//     컬럼명으로 정렬된 리스트 return api
    @GetMapping("/api/v1/user/chart-report/sorted/{companyName}")
    public List<ChartReportResponseDto> sortedBy(@PathVariable String companyName, @RequestParam(value = "sorted") String column) {
        System.out.println(column);
        return postService.getSortedByCompany(companyName,column);
    }

}
