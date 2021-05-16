package com.stock.spring.web;

import com.stock.spring.domain.company.Company;
import com.stock.spring.domain.company.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApiController apiController;

    @LocalServerPort
    private int port;

    private static Logger log = LoggerFactory.getLogger(Slf4j.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void dataTable조회테스트() throws Exception{
        //given
        String startDate = "2021-05-06";
        String endDate = "2021-05-07";
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/get/data")
                .param("start", startDate)
                .param("end", endDate))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void CompanyNameListByMarket() throws Exception {
        //given
        Company company1 = companyRepository.save(Company.builder()
                .companyId("1")
                .market("kospi")
                .name("samsung")
                .build());
        Company company2 = companyRepository.save(Company.builder()
                .companyId("2")
                .market("kosdaq")
                .name("kakaogames")
                .build());

        String url1 = "http://localhost:" + port + "/api/get/companyname/kospi";
        String url2 = "http://localhost:" + port + "/api/get/companyname/kosdaq";

        //when
        ResponseEntity<String> response1 = restTemplate.getForEntity(url1, String.class);
        ResponseEntity<String> response2 = restTemplate.getForEntity(url2, String.class);

        //then
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info(response1.toString());
        log.info(response2.toString());
    }
}