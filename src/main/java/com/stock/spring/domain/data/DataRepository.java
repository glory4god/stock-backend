package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DataRepository extends JpaRepository<Data, String> {

    @Query("SELECT d FROM Data d WHERE DATE(d.date) >= DATE(:startDate) AND DATE(d.date) <= DATE(:endDate)")
    List<Data> dataListByDate(String startDate, String endDate);

    @Query(nativeQuery = true,value = "SELECT * FROM Data d WHERE d.company_name=?1 ORDER BY d.date LIMIT 1")
    Data dateStartByCompany(String company);

    @Query(nativeQuery = true,value = "SELECT * FROM Data d WHERE d.company_name=?1 ORDER BY d.date DESC LIMIT 1")
    Data dateEndByCompany(String company);
}
