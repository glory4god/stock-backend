package com.stock.spring.domain.user;


import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportRepositoryTest  {

    @Autowired
    ReportRepository reportRepository;

    @After
    public void dataCleanUp() {
        reportRepository.deleteAll();
    }

    @Test
    public void 리포트테이블생성조회테스트() {

        String userId = "유하영";
        String companyName = "삼성전자";
        String startDate = "2021-05-06";
        String endDate = "2021-05-28";

        reportRepository.save(Report.builder()
                .userId(userId)
                .companyName(companyName)
                .startDate(startDate)
                .endDate(endDate)
                .build());

        List<Report> reportList = reportRepository.findAll();

        Report report = reportList.get(0);
        Assertions.assertThat(report.getUserId()).isEqualTo(userId);
        Assertions.assertThat(report.getCompanyName()).isEqualTo(companyName);

    }

    @Test
    public void BaseTimeEntity테스트() {

        LocalDateTime now = LocalDateTime.of(2021, 5, 28, 0, 0, 0);

        reportRepository.save(Report.builder()
                .userId("유하영")
                .companyName("삼성전자")
                .startDate("2021-05-06")
                .endDate("2021-05-28")
                .build());

        List<Report> reportList = reportRepository.findAll();

        Report report = reportList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>> createDate=" + report.getCreatedDate() + ">>>>>>>>>>>>>>>>> modifiedDate=" + report.getModifiedDate());

        Assertions.assertThat(report.getCreatedDate()).isAfter(now);
        Assertions.assertThat(report.getModifiedDate()).isAfter(now);

    }


}