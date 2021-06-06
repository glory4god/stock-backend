package com.stock.spring.domain.data;


import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRecordRepository extends JpaRepository<NewsRecord,String> {
}
