package com.stock.spring.kakaoOAuth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "kakao_user")
public class KakaoUser {

    @Id
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "image")
    private String image;

    @Builder
    public KakaoUser(Long id, String nickname, String image) {
        this.id = id;
        this.nickname = nickname;
        this.image = image;
    }
}
