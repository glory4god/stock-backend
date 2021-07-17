package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChartDataRepository extends JpaRepository<ChartData, String> {

    //    @Query("SELECT d FROM Data d WHERE (DATE(d.date) >= DATE(:startDate) AND DATE(d.date) <= DATE(:endDate))")
    @Query("SELECT d FROM ChartData d WHERE company_name=?1 AND (date BETWEEN ?2 AND ?3) ORDER BY d.date")
    List<ChartData> dataListByDate(String companyName, String startDate, String endDate);

    @Query(nativeQuery = true,value = "SELECT * FROM ChartData WHERE company_name=?1 ORDER BY date LIMIT 1")
    ChartData dateStartByCompany(String company);

    @Query(nativeQuery = true,value = "SELECT * FROM ChartData WHERE company_name=?1 ORDER BY date DESC LIMIT 1")
    ChartData dateEndByCompany(String company);
}
