package com.stock.spring.domain.data.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodDataRepository extends JpaRepository<GoodData, Long> {

    boolean existsByUserIdAndReportId(Long userId, Long reportId);

    GoodData findByUserIdAndReportId(Long userId, Long reportId);

}
