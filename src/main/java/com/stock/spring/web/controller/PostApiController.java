package com.stock.spring.web.controller;

import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.GetChatResponseDto;
import com.stock.spring.web.dto.post.*;
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

    @PostMapping("/api/user/chart-record/post")
    public Long saveChartSearchRecord(@RequestBody ChartRecordRequestDto requestDto) {
        return postService.saveChartSearchRecord(requestDto);
    }

    @GetMapping("/api/user/chart-records")
    public List<ChartRecordResponseDto> getChartRecordList() {
        return postService.getChartRecordList();
    }

    // Chat 관련 POST
    @PostMapping("/api/stock/data/chat/post")
    public Long saveChat(@RequestBody PostChatRequestDto requestDto) {
        return postService.saveChat(requestDto);
    }

    @GetMapping("/api/stock/data/chat")
    public List<GetChatResponseDto> getChatList() {
        return postService.getChatList();
    }
}
