package com.stock.spring.service;

import com.stock.spring.domain.data.ChatRepository;
import com.stock.spring.domain.user.ChartSearchRecordRepository;
import com.stock.spring.domain.user.Report;
import com.stock.spring.domain.user.ReportRepository;
import com.stock.spring.domain.user.UserRepository;
import com.stock.spring.web.dto.GetChatResponseDto;
import com.stock.spring.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final ReportRepository reportRepository;
    private final ChartSearchRecordRepository chartSearchRecordRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    // report 관련 (여기 부분 애매 다르게 변경할 필요있음)
    @Transactional
    public Long saveReport(PostReportSaveRequestDto requestDto) {
        return reportRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public PostReportResponseDto findReportById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
        return new PostReportResponseDto(entity);
    }

    @Transactional
    public Long deleteReport(Long id, DeleteReportRequestDto requestDto) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        // UserInfo 테이븦 정리필요함 ( Id 찾은 후, 그 아이디의 패스워드와 request 패스워드 같은지 비교 )
//        UserInfo user = userRepository.findById(requestDto.getUserId());
//        if (user.getPassword().equals(requestDto.getPassword())) {
//            reportRepository.deleteById(id);
//        } else{
//            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
//        }
        return id;
    }

    // ChartSearch 관련
    @Transactional
    public Long saveChartSearchRecord(ChartRecordRequestDto requestDto) {
        return chartSearchRecordRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ChartRecordResponseDto> getChartRecordList() {
        List<ChartRecordResponseDto> collect = chartSearchRecordRepository.findAll().stream()
                .map(ChartRecordResponseDto::new)
                .collect(Collectors.toList());
        return collect;
    }

    // Chat 관련
    @Transactional
    public Long saveChat(PostChatRequestDto requestDto) {
        return chatRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<GetChatResponseDto> getChatList() {
        List<GetChatResponseDto> chatList = chatRepository.findLatest().stream()
                .map(GetChatResponseDto::new)
                .collect(Collectors.toList());
        return chatList;
    }
}
