package com.stock.spring.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChartReportRepository extends JpaRepository<ChartReport, String> {

    @Query("SELECT r FROM ChartReport r WHERE id=?1")
    ChartReport getReportById(Long id);
}

