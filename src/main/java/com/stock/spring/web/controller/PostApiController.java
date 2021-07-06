package com.stock.spring.web.controller;

import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.post.ChartReportSaveRequestDto;
import com.stock.spring.web.dto.post.ChartReportResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/api/v1/user/chart-report/post")
    public Long saveChartSearchRecord(@RequestBody ChartReportSaveRequestDto requestDto) {
        return postService.saveChartSearchRecord(requestDto);
    }

    @GetMapping("/api/v1/user/chart-report")
    public List<ChartReportResponseDto> getChartRecordList() {
        return postService.getChartReportList();
    }

    @GetMapping("api/v1/user/chart-report/{id}")
    public ChartReportResponseDto getChartById(@PathVariable Long id) {
        return postService.getChartReportById(id);
    }
}
