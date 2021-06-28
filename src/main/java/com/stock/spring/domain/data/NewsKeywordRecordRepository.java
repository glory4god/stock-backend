package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsKeywordRecordRepository extends JpaRepository<NewsKeywordRecord,String> {

//    @Query("SELECT k from NewsKeywordRecord k GROUP BY k.keyword ORDER BY COUNT(k.keyword) DESC")
//    List<NewsKeywordRecord> getPopularKeyword();

    //오늘 인기순위
    @Query("SELECT k from NewsKeywordRecord k WHERE DATE_FORMAT(create_date, '%Y-%m-%d')=DATE_FORMAT(now(),'%Y-%m-%d') GROUP BY k.keyword ORDER BY COUNT(k.keyword) DESC")
    List<NewsKeywordRecord> getPopularKeywordByDaily();

    //일주일 인기순위
    @Query("SELECT k from NewsKeywordRecord k WHERE (create_date BETWEEN ?2 AND ?1) GROUP BY k.keyword ORDER BY COUNT(k.keyword) DESC")
    List<NewsKeywordRecord> getPopularKeywordByWeekly(String currentTime, String intervalWeekTime);

}
