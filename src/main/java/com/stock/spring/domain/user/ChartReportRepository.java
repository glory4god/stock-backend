package com.stock.spring.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChartReportRepository extends JpaRepository<ChartReport, String> {

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

}
