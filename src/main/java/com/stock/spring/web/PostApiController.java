package com.stock.spring.web;

import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.DeleteReportRequestDto;
import com.stock.spring.web.dto.NewsUrlRecordRequestDto;
import com.stock.spring.web.dto.PostReportResponseDto;
import com.stock.spring.web.dto.PostReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/stock/report/post")
    public PostReportResponseDto saveReport(@RequestBody PostReportSaveRequestDto requestDto) {
        Long id = postService.saveReport(requestDto);
        return postService.findReportById(id);
    }

    @GetMapping("/api/stock/report/post/{reportId}")
    public PostReportResponseDto findReportById(@PathVariable Long reportId) {
        return postService.findReportById(reportId);
    }

    @DeleteMapping("/api/stock/report/post/{reportId}")
    public Long deleteReport(@PathVariable Long reportId, @RequestBody DeleteReportRequestDto requestDto) {
        return postService.deleteReport(reportId, requestDto);
    }

    @PostMapping("/api/finance/url/post")
    public Long saveNewsUrl(@RequestBody NewsUrlRecordRequestDto requestDto) {
        return postService.saveNewsUrl(requestDto);
    }
}
