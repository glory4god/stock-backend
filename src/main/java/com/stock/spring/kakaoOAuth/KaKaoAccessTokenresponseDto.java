package com.stock.spring.kakaoOAuth;

import lombok.Getter;

@Getter
public class KaKaoAccessTokenresponseDto {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String refresh_token_expires_in;

    public KaKaoAccessTokenresponseDto(String access_token, String token_type,
                                       String refresh_token, String expires_in,
                                       String scope, String refresh_token_expires_in) {

    }
}
