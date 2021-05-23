package com.stock.spring.web;

import com.stock.spring.service.GetService;
import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.PostReportResponseDto;
import com.stock.spring.web.dto.PostReportSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;
    private final GetService getService;

    @PostMapping("/api/report/post")
    public PostReportResponseDto saveReport(@RequestBody PostReportSaveRequestDto requestDto) {
        Long id = postService.saveReport(requestDto);
        return getService.findReportById(id);
    }
}
