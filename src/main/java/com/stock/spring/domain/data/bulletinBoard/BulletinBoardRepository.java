package com.stock.spring.domain.data.bulletinBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long> {

    @Query("SELECT b FROM BulletinBoard b WHERE id=?1")
    BulletinBoard getBulletinBoardById(Long id);

    @Modifying
    @Query("update BulletinBoard b set b.good=?2 where b.id=?1")
    int updateGoodById(Long id, int good);

    @Modifying
    @Query("update BulletinBoard b set b.views=?2 where b.id=?1")
    int updateViewsById(Long id, int views);

    @Modifying
    @Query("update BulletinBoard b set b.title=?2, b.content=?3 WHERE b.id=?1")
    int updateBulletinBoardById(Long id, String title, String content);


}



