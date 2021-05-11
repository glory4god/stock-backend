package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, String> {
    @Query("SELECT d FROM Data d WHERE d.date= :date")
    List<Data> dataListByDate(String date);
}
