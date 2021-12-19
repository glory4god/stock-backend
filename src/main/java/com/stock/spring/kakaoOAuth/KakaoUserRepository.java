package com.stock.spring.kakaoOAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {

    boolean existsById(Long id);

    @Query("SELECT k FROM KakaoUser k WHERE k.id=?1")
    KakaoUser getKakaoById(Long id);

    KakaoUser findByNickname(String nickname);
}
