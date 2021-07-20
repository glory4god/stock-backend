package com.stock.spring.kakaoOAuth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoUserResponseDto {
    private Long id;
    private String nickname;
    private String image;
    private String access_token;

    @Builder
    public KakaoUserResponseDto(Long id, String nickname,String image, String access_token) {
        this.id = id;
        this.nickname = nickname;
        this.image = image;
        this.access_token = access_token;
    }
}
