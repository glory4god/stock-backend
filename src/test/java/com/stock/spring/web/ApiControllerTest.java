package com.stock.spring.web;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ApiController apiController;

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
}
