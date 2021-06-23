package com.stock.spring.web.controller;

import com.stock.spring.service.NewsService;
import com.stock.spring.web.dto.news.NewsUrlRecordRequestDto;
import com.stock.spring.web.dto.news.NewsUrlRecordResponseDto;
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
    @GetMapping("/api/finance/url/{keyword}")
    public List<NewsUrlRecordResponseDto> getPopularUrlByKeyword(@PathVariable String keyword){
        return newsService.getPopularUrlByKeyword(keyword);
    }

}
