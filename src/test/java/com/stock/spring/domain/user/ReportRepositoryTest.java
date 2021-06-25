package com.stock.spring.domain.user;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportRepositoryTest  {

    @Autowired
    ReportRepository reportRepository;

//    @Test
//    public void 리포트테이블생성조회테스트() {
//
//        String userId = "유하영";
//        String companyName = "삼성전자";
//        String startDate = "2021-05-06";
//        String endDate = "2021-05-28";
//        String password = "123123";
//
//        reportRepository.save(Report.builder()
//                .userId(userId)
//                .companyName(companyName)
//                .password(password)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build());
//
//        List<Report> reportList = reportRepository.findAll();
//
//        Report report = reportList.get(0);
//        Assertions.assertThat(report.getUserId()).isEqualTo(userId);
//        Assertions.assertThat(report.getCompanyName()).isEqualTo(companyName);
//
//    }

//    @Test
//    public void BaseTimeEntity테스트() {
//
//        LocalDateTime now = LocalDateTime.of(2021, 5, 28, 0, 0, 0);
//
//        reportRepository.save(Report.builder()
//                .userId("유하영")
//                .companyName("삼성전자")
//                .password("123123")
//                .startDate("2021-05-06")
//                .endDate("2021-05-28")
//                .build());
//
//        List<Report> reportList = reportRepository.findAll();
//
//        Report report = reportList.get(0);
//
//        System.out.println(">>>>>>>>>>>>>>>>>> createDate=" + report.getCreatedDate() + ">>>>>>>>>>>>>>>>> modifiedDate=" + report.getModifiedDate());
//
//        Assertions.assertThat(report.getCreatedDate()).isAfter(now);
//        Assertions.assertThat(report.getModifiedDate()).isAfter(now);
//
//    }


}