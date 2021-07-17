package com.stock.spring.domain.data.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsUrlRecordRepository extends JpaRepository<NewsUrlRecord, String> {

    @Query("SELECT u from NewsUrlRecord u GROUP BY u.link ORDER BY COUNT(u.link) DESC")
    List<NewsUrlRecord> getPopularNews();

    @Query("SELECT u from NewsUrlRecord u WHERE u.keyword=?1 GROUP BY u.link ORDER BY COUNT(u.link) DESC")
    List<NewsUrlRecord> getPopularNewsByKeyword(String keyword);

    //오늘 인기순위
    @Query("SELECT u from NewsUrlRecord u WHERE DATE_FORMAT(create_date, '%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d') GROUP BY u.keyword ORDER BY COUNT(u.keyword) DESC")
    List<NewsUrlRecord> getPopularNewsByDaily();

    //일주일 인기순위
    @Query("SELECT u from NewsUrlRecord u WHERE (create_date BETWEEN ?2 AND ?1) GROUP BY u.keyword ORDER BY COUNT(u.keyword) DESC")
    List<NewsUrlRecord> getPopularNewsByWeekly(String currentTime, String intervalWeekTime);
}
