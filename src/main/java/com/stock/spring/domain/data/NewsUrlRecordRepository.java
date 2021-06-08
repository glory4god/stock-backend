package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsUrlRecordRepository extends JpaRepository<NewsUrlRecord, String> {

    @Query("SELECT u from NewsUrlRecord u GROUP BY u.link ORDER BY COUNT(u.link) DESC")
    List<NewsUrlRecord> getPopularNews();
}
