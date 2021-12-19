package com.stock.spring.domain.data.freeBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    @Query("SELECT f FROM FreeBoard f WHERE id=?1")
    FreeBoard getFreeBoardById(Long id);

    @Modifying
    @Query("update FreeBoard f set f.good=?2 where f.id=?1")
    int updateGoodById(Long id, int good);

    @Modifying
    @Query("update FreeBoard f set f.views=?2 where f.id=?1")
    int updateViewsById(Long id, int views);

    @Modifying
    @Query("update FreeBoard f set f.title=?2, f.content=?3 WHERE f.id=?1")
    int updateFreeBoardById(Long id, String title, String content);
}


