package com.stock.spring.kakaoOAuth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/api/v2/login/callback/kakao")
    public Object kakaoLogin(@RequestParam(value = "code") String request) {

        return kakaoLoginService.getAccessToken(request);
    }

}
