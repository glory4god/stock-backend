package com.stock.spring.web;

import com.stock.spring.domain.data.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ApiControllerTest {
    private static Logger log = LoggerFactory.getLogger(Slf4j.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void dataTable조회테스트 () {
        //given
        String date1 = "2021-05-06";
        Data data = restTemplate.getForObject("http://localhost:8080/api/get/data/{date}", Data.class, date1);
        log.info("data: {}", data);
    }
}
