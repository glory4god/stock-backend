package com.stock.spring.domain.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, String> {

    @Query("SELECT c FROM Chat c ORDER BY c.createDate")
    List<Chat> findLatest();
}
