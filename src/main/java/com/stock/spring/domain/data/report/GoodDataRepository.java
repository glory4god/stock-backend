package com.stock.spring.domain.data.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodDataRepository extends JpaRepository<GoodData, Long> {

    boolean existsByUserIdAndReportIdAndDbName(Long userId, Long reportId, String dbName);

    GoodData findByUserIdAndReportIdAndDbName(Long userId, Long reportId,String dbName);

}
