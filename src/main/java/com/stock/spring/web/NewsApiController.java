package com.stock.spring.web;

import com.stock.spring.service.NewsService;
import com.stock.spring.web.newsdto.NewsUrlRecordRequestDto;
import com.stock.spring.web.newsdto.NewsUrlRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NewsApiController {

    private final NewsService newsService;

    @GetMapping("/api/finance/news/{keyword}")
    public Object getNews(@PathVariable String keyword, @RequestParam(value = "sort") String sort) {
        return newsService.getNews(keyword, sort);
    }

    @GetMapping("/api/finance/news/popular/keyword")
    public List<String> getPopularKeyword() {
        return newsService.getPopularKeyword();
    }

    @GetMapping("/api/finance/news/popular/url")
    public List<NewsUrlRecordResponseDto> getPopularUrl() {
        return newsService.getPopularUrl();
    }

    @PostMapping("/api/finance/url/post")
    public Long saveNewsUrl(@RequestBody NewsUrlRecordRequestDto requestDto) {
        return newsService.saveNewsUrl(requestDto);
    }

}
