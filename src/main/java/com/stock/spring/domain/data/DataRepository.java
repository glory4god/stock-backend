package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DataRepository extends JpaRepository<Data, String> {

    //    @Query("SELECT d FROM Data d WHERE (DATE(d.date) >= DATE(:startDate) AND DATE(d.date) <= DATE(:endDate))")
    @Query("SELECT d FROM Data d WHERE company_name=?1 AND (date BETWEEN ?2 AND ?3) ORDER BY d.date")
    List<Data> dataListByDate(String companyName,String startDate, String endDate);

    @Query("SELECT d FROM Data d WHERE company_name=?1 ORDER BY d.date")
    List<Data> dateStartByCompany(String companyName);

    @Query("SELECT d FROM Data d WHERE company_name=?1 ORDER BY d.date DESC")
    List<Data> dateEndByCompany(String companyName);
}
