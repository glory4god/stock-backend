package com.stock.spring.web;

import com.stock.spring.domain.company.Company;
import com.stock.spring.domain.company.CompanyRepository;
import com.stock.spring.domain.data.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @LocalServerPort
    private int port;

    private static Logger log = LoggerFactory.getLogger(Slf4j.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void dataTable조회테스트() {
        //given
        String date1 = "2021-05-06";
        Data data = restTemplate.getForObject("http://localhost:8080/api/get/data/{date}", Data.class, date1);
        log.info("data: {}", data);
    }

    @Test
    public void testFindByMarket() throws Exception {
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

        String url1 = "http://localhost:" + port + "/api/get/company/kospi";
        String url2 = "http://localhost:" + port + "/api/get/company/kosdaq";

        //when
        ResponseEntity<String> response1 = restTemplate.getForEntity(url1, String.class);
        ResponseEntity<String> response2 = restTemplate.getForEntity(url2, String.class);

        //then
        Assertions.assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info(response1.toString());
        log.info(response2.toString());
    }

}