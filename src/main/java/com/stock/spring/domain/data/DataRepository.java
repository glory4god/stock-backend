package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DataRepository extends JpaRepository<Data, String> {
    @Query("SELECT d FROM Data d WHERE DATE(d.date) >= DATE(:startDate) AND DATE(d.date) <= DATE(:endDate)")
    List<Data> dataListByDate(String startDate, String endDate);
}
