package com.stock.spring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.spring.domain.user.ReportRepository;
import com.stock.spring.web.dto.PostReportSaveRequestDto;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest extends TestCase {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    PostApiController postApiController;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @After
    public void reportCleanUp() {
        reportRepository.deleteAll();
    }

    @Test
    public void 리포트저장_테스트() throws Exception {
        //given
        PostReportSaveRequestDto requestDto = PostReportSaveRequestDto.builder()
                .userId("hayoung")
                .password("123123")
                .companyName("삼성전자")
                .startDate("2021-05-06")
                .endDate("2021-05-28")
                .build();

        String url = "http://localhost:" + port + "/api/stock/report/post";


        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
        ;



    }

    @Test
    public void 리포트Id조회_테스트() throws Exception {

    }

    @Test
    public void 리포트삭제_테스트() throws Exception{

    }
}