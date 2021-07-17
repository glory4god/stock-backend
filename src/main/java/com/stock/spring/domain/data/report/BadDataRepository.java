package com.stock.spring.domain.data.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BadDataRepository extends JpaRepository<BadData, Long> {

    boolean existsByUserIdAndReportId(Long userId, Long reportId);

    BadData findByUserIdAndReportId(Long userId, Long reportId);
}
