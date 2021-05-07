package com.stock.spring.domain.company;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyInfoTest {

    @Autowired
    CompanyInfoRepository companyInfoRepository;

    @After
    public void cleanUp() {
        companyInfoRepository.deleteAll();
    }

    @Test
    public void 디비테이블생성테스트() {
        //given
        String companyId = "1";
        String name = "삼성전자";
        String market = "코스피";

        companyInfoRepository.save(CompanyInfo.builder()
                .companyId(companyId)
                .name(name)
                .market(market)
                .build());
//        when
        List<CompanyInfo> userList = companyInfoRepository.findAll();

//        then
        CompanyInfo companyInfo = userList.get(0);
        Assertions.assertThat(companyInfo.getCompanyId()).isEqualTo(companyId);
        Assertions.assertThat(companyInfo.getName()).isEqualTo(name);
        Assertions.assertThat(companyInfo.getMarket()).isEqualTo(market);
    }
}