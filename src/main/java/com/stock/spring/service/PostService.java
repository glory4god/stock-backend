package com.stock.spring.service;

import com.stock.spring.domain.data.bulletinBoard.BulletinBoard;
import com.stock.spring.domain.data.bulletinBoard.BulletinBoardRepository;
import com.stock.spring.domain.data.report.*;
import com.stock.spring.kakaoOAuth.KakaoUser;
import com.stock.spring.kakaoOAuth.KakaoUserRepository;
import com.stock.spring.web.dto.post.*;
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
    //    private final UserRepository userRepository;
    private final GoodDataRepository goodDataRepository;
    private final BadDataRepository badDataRepository;
    private final KakaoUserRepository kakaoUserRepository;
    private final BulletinBoardRepository bulletinBoardRepository;

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

    // ChartReport nickname 같은 List 조회
    @Transactional(readOnly = true)
    public List<ChartReportResponseDto> getChartReportListByUsername(Long userId) {
        String nickname = kakaoUserRepository.getOne(userId).getNickname();
        System.out.println(nickname);
        return chartReportRepository.findAllByUsername(nickname).stream()
                .map(ChartReportResponseDto::new)
                .collect(Collectors.toList());
    }

    //     ChartReport 종목별, 제목별, 유저이름 검색으로 조회 기능
    @Transactional(readOnly = true)
    public Object searchChartReportByUsername(String dbName, String column, String value, String sorted) {
        System.out.println(sorted);
        if (dbName.equals("chart")) {
            if (column.equals("user")) {
                return chartReportRepository.searchListByUsername(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else if (column.equals("company")) {
                return chartReportRepository.searchListByCompanyName(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else if (column.equals("content")) {
                return chartReportRepository.searchListByTitleAndContent(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                Map<String, String> failed = new HashMap<>();
                failed.put("failed", "condition is wrong");
                return failed;
            }
        } else {
            if (column.equals("title")) {
                return chartReportRepository.searchListByUsername(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else if (column.equals("content")) {
                return chartReportRepository.searchListByCompanyName(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else if (column.equals("title+content")) {
                return chartReportRepository.searchListByTitleAndContent(value, Sort.by(Sort.Direction.DESC, sorted)).stream()
                        .map(ChartReportResponseDto::new)
                        .collect(Collectors.toList());
            } else {
                Map<String, String> failed = new HashMap<>();
                failed.put("failed", "condition is wrong");
                return failed;
            }
        }

    }


    // 좋아요 싫어요 up down기능(추후에 같은 username 중복 안되게 만들기) => 변경함
    @Transactional
    public GoodOrBadDataResponseDto updateGoodById(String dbName, String value, GoodOrBadDataRequestDto requestDto) {

        if (dbName.equals("chart")) {
            Long reportId = requestDto.getReportId();
            GoodData goodEntity = goodDataRepository.findByUserIdAndReportIdAndDbName(requestDto.getUserId(), requestDto.getReportId(), dbName);
            BadData badEntity = badDataRepository.findByUserIdAndReportId(requestDto.getUserId(), requestDto.getReportId());

            int good = chartReportRepository.getReportById(reportId).getGood();
            int bad = chartReportRepository.getReportById(reportId).getBad();

            if (goodEntity == null && badEntity == null) {
                if (value.equals("good")) {
                    chartReportRepository.updateGoodById(reportId, good + 1);
                    GoodData res = GoodData.builder()
                            .userId(requestDto.getUserId())
                            .reportId(reportId)
                            .dbName(dbName)
                            .build();
                    goodDataRepository.save(res);
                    return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());

                } else {
                    chartReportRepository.updateBadById(reportId, bad + 1);
                    BadData res = BadData.builder()
                            .userId(requestDto.getUserId())
                            .reportId(reportId)
                            .build();
                    badDataRepository.save(res);
                    return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
                }
            } else if (goodEntity == null) {
                if (value.equals("good")) {
                    chartReportRepository.updateBadById(reportId, bad - 1);
                    chartReportRepository.updateGoodById(reportId, good + 1);
                    GoodData res = GoodData.builder()
                            .userId(requestDto.getUserId())
                            .reportId(reportId)
                            .dbName(dbName)
                            .build();
                    goodDataRepository.save(res);
                    badDataRepository.deleteById(badEntity.getId());
                    return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
                } else {
                    chartReportRepository.updateBadById(reportId, bad - 1);
                    badDataRepository.deleteById(badEntity.getId());
                    return new GoodOrBadDataResponseDto(0L, 0L);
                }
            } else if (badEntity == null) {
                if (value.equals("good")) {
                    chartReportRepository.updateGoodById(reportId, good - 1);
                    goodDataRepository.deleteById(goodEntity.getId());
                    return new GoodOrBadDataResponseDto(0L, 0L);
                } else {
                    chartReportRepository.updateBadById(reportId, bad + 1);
                    chartReportRepository.updateGoodById(reportId, good - 1);
                    BadData res = BadData.builder()
                            .userId(requestDto.getUserId())
                            .reportId(reportId)
                            .build();
                    badDataRepository.save(res);
                    goodDataRepository.deleteById(goodEntity.getId());
                    return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
                }
            } else {
                // 둘 다 눌러진 경우는 없을테지만 어케처리할지 고민
                return new GoodOrBadDataResponseDto(0L, 0L);
            }
        } else {
            Long boardId = requestDto.getReportId();
            GoodData goodEntity = goodDataRepository.findByUserIdAndReportIdAndDbName(requestDto.getUserId(), requestDto.getReportId(), dbName);

            int good = bulletinBoardRepository.getBulletinBoardById(boardId).getGood();

            if (goodEntity == null) {
                bulletinBoardRepository.updateGoodById(boardId, good + 1);
                GoodData res = GoodData.builder()
                        .userId(requestDto.getUserId())
                        .reportId(boardId)
                        .dbName(dbName)
                        .build();
                goodDataRepository.save(res);
                return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
            } else {
                bulletinBoardRepository.updateGoodById(boardId, good - 1);
                goodDataRepository.deleteById(goodEntity.getId());

                return new GoodOrBadDataResponseDto(0L, 0L);
            }
        }

    }

//    // 싫어요 up기능(추후에 같은 username 중복 안되게 만들기) => 위에서 통합해서 필요없어짐
//    @Transactional
//    public GoodOrBadDataResponseDto updateBadById(GoodOrBadDataRequestDto requestDto) {
//        Long reportId = requestDto.getReportId();
//        BadData entity1 = badDataRepository.findByUserIdAndReportId(requestDto.getUserId(), requestDto.getReportId());
//        GoodData entity2 = goodDataRepository.findByUserIdAndReportId(requestDto.getUserId(), requestDto.getReportId());
//
//        int good = chartReportRepository.getReportById(reportId).getGood();
//        int bad = chartReportRepository.getReportById(reportId).getBad();
//
//        if (entity1 == null && entity2 == null) {
//            chartReportRepository.updateBadById(reportId, bad + 1);
//            BadData res = BadData.builder()
//                    .userId(requestDto.getUserId())
//                    .reportId(reportId)
//                    .build();
//            badDataRepository.save(res);
//            return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
//
//        } else if (entity1 == null) {
//            chartReportRepository.updateBadById(reportId, bad + 1);
//            chartReportRepository.updateGoodById(reportId, good - 1);
//            BadData res = BadData.builder()
//                    .userId(requestDto.getUserId())
//                    .reportId(reportId)
//                    .build();
//            badDataRepository.save(res);
//            goodDataRepository.deleteById(entity2.getId());
//            return new GoodOrBadDataResponseDto(res.getUserId(), res.getReportId());
//        } else {
//            chartReportRepository.updateBadById(reportId, good - 1);
//            badDataRepository.deleteById(entity1.getId());
//            return new GoodOrBadDataResponseDto(0L, 0L);
//        }
//    }

    // 좋아요 or 싫어요 눌러져있는지 체크
    @Transactional(readOnly = true)
    public Map<String, Boolean> pressedCheck(Long userId, Long reportId, String dbName) {
        boolean good = goodDataRepository.existsByUserIdAndReportIdAndDbName(userId, reportId, dbName);
        boolean bad = badDataRepository.existsByUserIdAndReportId(userId, reportId);

        Map<String, Boolean> result = new HashMap<>();
        result.put("good", good);
        result.put("bad", bad);
        return result;
    }

    @Transactional(readOnly = true)
    public boolean bulletinBoardPressedCheck(Long userId, Long boardId, String dbName) {
        return goodDataRepository.existsByUserIdAndReportIdAndDbName(userId, boardId, dbName);

    }

    // 조회수 up
    @Transactional
    public String updateIncreaseViewsById(String dbName, Long id) {
        if (dbName.equals("chart")) {
            int views = chartReportRepository.getReportById(id).getViews();
            int value = chartReportRepository.updateViewsById(id, views + 1);

            if (value == 1) {
                return "up success!";
            } else {
                return "up failed!";
            }
        } else {
            int views = bulletinBoardRepository.getBulletinBoardById(id).getViews();
            int value = bulletinBoardRepository.updateViewsById(id, views + 1);

            if (value == 1) {
                return "up success!";
            } else {
                return "up failed!";
            }
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

    // report 삭제 기능 (userId가 같은지 비교)
    @Transactional
    public String deleteByUserId(Long reportId, Long userId) {
        ChartReport entity = chartReportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id : " + reportId));
        KakaoUser user = kakaoUserRepository.getOne(userId);
        if (entity.getUsername().equals(user.getNickname())) {
            chartReportRepository.deleteById(entity.getId());
            return "delete success";
        } else {
            return "delete failed";
        }
    }

    //freeboard 저장
    @Transactional
    public Long saveBulletinBoard(BulletinBoardSaveRequestDto requestDto) {
        return bulletinBoardRepository.save(requestDto.toEntity()).getId();
    }

    //freeboard 전체 리스트 조회
    @Transactional(readOnly = true)
    public List<BulletinBoardReponseDto> getBulletinBoardFindAll(String sorted) {
        return bulletinBoardRepository.findAll(Sort.by(Sort.Direction.DESC, sorted)).stream()
                .map((c) -> new BulletinBoardReponseDto(c, kakaoUserRepository.getKakaoById(c.getUserId()).getNickname()))
                .collect(Collectors.toList());
    }

    //freeboard id로 조회
    @Transactional(readOnly = true)
    public BulletinBoardReponseDto getBulletinBoardFindById(Long id) {
        BulletinBoard entity = bulletinBoardRepository.getBulletinBoardById(id);
        return new BulletinBoardReponseDto(entity, kakaoUserRepository.getKakaoById(entity.getUserId()).getNickname());
    }

    // freeboard 지우기
    @Transactional
    public String deleteBulletinByUserId(Long boardId, Long userId) {
        BulletinBoard entity = bulletinBoardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id : " + boardId));
        KakaoUser user = kakaoUserRepository.getOne(userId);
        if (entity.getUserId().equals(userId)) {
            bulletinBoardRepository.deleteById(boardId);
            return "delete success";
        } else {
            return "delete failed";
        }
    }

    @Transactional
    public String updateBulletinBoard(Long id, String title, String content) {
        int value = bulletinBoardRepository.updateBulletinBoardById(id, title, content);
        if (value == 1) {
            return "update success";
        } else {
            return "update failed";
        }
    }

}


