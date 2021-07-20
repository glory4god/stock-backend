package com.stock.spring.kakaoOAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {

    boolean existsById(Long id);


}
