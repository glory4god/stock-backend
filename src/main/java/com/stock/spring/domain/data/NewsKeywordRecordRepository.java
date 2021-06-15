package com.stock.spring.domain.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsKeywordRecordRepository extends JpaRepository<NewsKeywordRecord,String> {

    @Query("SELECT k from NewsKeywordRecord k GROUP BY k.keyword ORDER BY COUNT(k.keyword) DESC")
    List<NewsKeywordRecord> getPopularKeyword();
}
