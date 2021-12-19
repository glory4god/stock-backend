package com.stock.spring.web.controller;

import com.stock.spring.service.PostService;
import com.stock.spring.web.dto.post.*;
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
    public List<ChartReportResponseDto> getChartReportList(@RequestParam(value = "sorted") String sorted) throws InterruptedException {

        Thread.sleep(500);
        return postService.getChartReportList(sorted);
    }

    //report search 조건으로 조회
    @GetMapping("api/v1/board/search")
    public Object searchChartReport(@RequestParam(value = "dbname") String dbName, @RequestParam(value = "column") String column, @RequestParam(value = "value") String value, @RequestParam(value = "sorted") String sorted) {
        return postService.searchChartReportByUsername(dbName, column, value, sorted);
    }


    // report id로 조회
    @GetMapping("/api/v1/user/chart-report/{id}")
    public ChartReportResponseDto getChartById(@PathVariable Long id) {

        return postService.getChartReportById(id);
    }

    //report username 으로 조회
    @GetMapping("/api/v1/user/chart-report/username")
    public List<ChartReportResponseDto> getChartListByUsername(@RequestParam(value = "id") Long userId) throws InterruptedException {
        Thread.sleep(1000);
        return postService.getChartReportListByUsername(userId);
    }

    // 좋아요 기능 (추후에 같은 아이디로 추천하면 안되게 변경) => 변경 완료
    @PatchMapping("/api/v1/user/board/patch/{dbName}/{value}")
    public GoodOrBadDataResponseDto updateGoodById(@PathVariable String dbName, @PathVariable String value, @RequestBody GoodOrBadDataRequestDto requestDto) {
        return postService.updateGoodById(dbName, value, requestDto);
    }

    // 좋아요 눌러져있는지 여부 판단
    @GetMapping("/api/v1/user/chart-report/press")
    public Map<String, Boolean> pressedCheck(@RequestParam(value = "user") Long userId, @RequestParam(value = "report") Long reportId) {
        return postService.pressedCheck(userId, reportId, "chart");
    }

    @GetMapping("/api/v1/bulletinboard/pressed")
    public boolean bulletinBoardPressedCheck(@RequestParam(value = "user") Long userId, @RequestParam(value = "board") Long boardId) {
        return postService.bulletinBoardPressedCheck(userId, boardId, "bulletin");
    }

    // 조회수 기능
    @PatchMapping("/api/v1/board/views/{dbName}/{id}")
    public Map<String, String> updateIncreaseViewsById(@PathVariable String dbName, @PathVariable Long id) {
        Map<String, String> result = new HashMap<>();
        result.put("result", postService.updateIncreaseViewsById(dbName, id));
        return result;
    }

    // 컬럼명으로 정렬된 리스트 return api
    @GetMapping("/api/v1/user/chart-report/sorted/{companyName}")
    public List<ChartReportResponseDto> sortedBy(@PathVariable String companyName, @RequestParam(value = "sorted") String column) throws InterruptedException {
        System.out.println(column);
        Thread.sleep(1000);
        return postService.getSortedByCompany(companyName, column);
    }

    @DeleteMapping("/api/v1/user/chart-report/post/{reportId}/{userId}")
    public String deleteByUserId(@PathVariable Long reportId, @PathVariable Long userId) throws InterruptedException {
        Thread.sleep(1000);
        return postService.deleteByUserId(reportId, userId);
    }

    @PostMapping("/api/v1/bulletinboard/post")
    public Long saveBulletinBoard(@RequestBody BulletinBoardSaveRequestDto requestDto) {
        return postService.saveBulletinBoard(requestDto);
    }

    @GetMapping("/api/v1/bulletinboard")
    public List<BulletinBoardReponseDto> getBulletinBoardFindAll(@RequestParam(value = "sorted") String sorted) throws InterruptedException {
        Thread.sleep(1000);
        return postService.getBulletinBoardFindAll(sorted);
    }

    @GetMapping("/api/v1/bulletinboard/{id}")
    public BulletinBoardReponseDto getBulletinBoardfindById(@PathVariable Long id) {
        return postService.getBulletinBoardFindById(id);
    }

    @DeleteMapping("/api/v1/bulletinboard/post/{boardId}/{userId}")
    public String deleteBulletinByUserId(@PathVariable Long boardId, @PathVariable Long userId) throws InterruptedException {
        Thread.sleep(1000);
        return postService.deleteBulletinByUserId(boardId, userId);
    }

    @PatchMapping("/api/v1/bulletinboard/update/{boardId}")
    public String updateBulletinBoard(@PathVariable Long boardId, @RequestBody BulletinBoardSaveRequestDto requestDto) throws InterruptedException {
        Thread.sleep(1000);
        return postService.updateBulletinBoard(boardId, requestDto.getTitle(), requestDto.getContent());
    }

}
