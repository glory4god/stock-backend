package com.stock.spring.domain.data.report;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChartReportRepository extends JpaRepository<ChartReport, Long> {

    @Query("SELECT c FROM ChartReport c WHERE id=?1")
    ChartReport getReportById(Long id);

    @Modifying
    @Query("update ChartReport c set c.good=?2 where c.id=?1")
    int updateGoodById(Long id, int good);

    @Modifying
    @Query("update ChartReport c set c.views=?2 where c.id=?1")
    int updateViewsById(Long id, int views);

    @Modifying
    @Query("update ChartReport c set c.bad=?2 where c.id=?1")
    int updateBadById(Long id, int bad);


    // Sort 넘겨서 정렬 좀 더 쉽게 할 수 있음!!!
    // 근데 WHERE 절도 간단하게 할 수 있을텐데 잘 모르겠다 ㅠㅠ
    @Query("SELECT c FROM ChartReport c WHERE companyName=?1")
    List<ChartReport> findByName(String companyName, Sort sort);

    @Query("SELECT c FROM ChartReport c WHERE username=?1")
    List<ChartReport> findAllByUsername(String nickname);

    @Query("SELECT c FROM ChartReport c WHERE username LIKE %?1%")
    List<ChartReport> searchListByUsername(String username, Sort sort);

    @Query("SELECT c FROM ChartReport c WHERE companyName LIKE %?1%")
    List<ChartReport> searchListByCompanyName(String companyName, Sort sort);

    @Query("SELECT c FROM ChartReport c WHERE (c.title LIKE %?1% AND c.content LIKE %?1%)")
    List<ChartReport> searchListByTitleAndContent(String value, Sort sort);


}
