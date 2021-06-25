package com.stock.spring.domain.company;

import com.stock.spring.domain.data.DataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DataRepository dataRepository;

//    @After
//    public void companyCleanUp() {
//        companyRepository.deleteAll();
//    }

//    @After
//    public void dataCleanUp() {
//        dataRepository.deleteAll();
//    }

    @Test
    public void 리포트Id조회_테스트() throws Exception {

    }

//    @Test
//    public void 디비컴패니테이블생성테스트() {
//        //given
//        String companyId = "1";
//        String name = "삼성전자";
//        String market = "코스피";
//
//        companyRepository.save(Company.builder()
//                .companyId(companyId)
//                .name(name)
//                .market(market)
//                .build());
//        //when
//        List<Company> userList = companyRepository.findAll();
//
//        //then
//        Company company = userList.get(0);
//        Assertions.assertThat(company.getCompanyId()).isEqualTo(companyId);
//        Assertions.assertThat(company.getName()).isEqualTo(name);
//        Assertions.assertThat(company.getMarket()).isEqualTo(market);
//    }
//
//    @Test
//    public void 디비데이터테이블생성테스트 () {
//        //given
//        String date1 = "2021-05-06";
//        int high1 = 2000;
//        int low1 = 1900;
//        int open1 = 1990;
//        int close1 = 1960;
//
//        String date2 = "2021-05-07";
//        int high2 = 2002;
//        int low2 = 1910;
//        int open2 = 1970;
//        int close2 = 1940;
//
//        dataRepository.save(Data.builder()
//                .date(date1)
//                .high(high1)
//                .low(low1)
//                .open(open1)
//                .close(close1)
//                .build()
//        );
//        dataRepository.save(Data.builder()
//                .date(date2)
//                .high(high2)
//                .low(low2)
//                .open(open2)
//                .close(close2)
//                .build()
//        );
//        //when
//        List<Data> dataList = dataRepository.findAll();
//
//        //then
//        Data data1 = dataList.get(0);
//        Data data2 = dataList.get(1);
//
//        Assertions.assertThat(data1.getDate()).isEqualTo(date1);
//        Assertions.assertThat(data1.getHigh()).isEqualTo(high1);
//        Assertions.assertThat(data1.getLow()).isEqualTo(low1);
//
//        Assertions.assertThat(data2.getDate()).isEqualTo(date2);
//        Assertions.assertThat(data2.getHigh()).isEqualTo(high2);
//        Assertions.assertThat(data2.getLow()).isEqualTo(low2);
//
//    }
}