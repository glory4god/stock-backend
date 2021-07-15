package com.stock.spring.kakaoOAuth;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class KakaoLoginService {
    private final KakaoUserRepository kakaoUserRepository;

    public Object getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=536a201af32aa0d66156738f15380b36");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:3000/login");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            bw.close();

            System.out.println("response body : " + response.toString());
            String token = response.toString().substring(17, 71);
            KakaoUser result = getUserInfo(token);

            if (result.getId().equals(0L)) {
                Map<String, String> failed = new HashMap<>();
                failed.put("result", "Failed to get user data!");
                return failed;
            } else {
                return new KakaoUserResponseDto(result.getId(), result.getNickname(), token);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch blocks
            e.printStackTrace();
            Map<String, String> failed = new HashMap<>();
            failed.put("result", "Failed to get token!");
            return failed;
        }


    }

    public KakaoUser getUserInfo(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer "+ token);

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response);

            JSONObject jsonObject = new JSONObject(response.toString());
            Long id = jsonObject.getLong("id");
            JSONObject properties = jsonObject.getJSONObject("properties");
            String nickname = properties.getString("nickname");
            KakaoUser kakaoUser = KakaoUser.builder()
                    .id(id)
                    .nickname(nickname)
                    .build();
            if (!kakaoUserRepository.existsById(id)) {
                kakaoUserRepository.save(kakaoUser);
            }
            return kakaoUser;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Map<String, String> failed = new HashMap<>();
            failed.put("result", "Failed to get user information");
            System.out.println(failed);
        }
        return new KakaoUser(0L, "");
    }


}
