package com.stock.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@RequiredArgsConstructor
@Service
public class NewsApiGetService {

    public Object getNews(String keyword) {
        String clientId = "VgK9ERVBz6_4f9p3RDzo";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "IALrdMLh3d";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+text+"&display=10&sort=sim";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

//    public static void main(String[] args)  {
//
//        String clientId = "VgK9ERVBz6_4f9p3RDzo";//애플리케이션 클라이언트 아이디값";
//        String clientSecret = "IALrdMLh3d";//애플리케이션 클라이언트 시크릿값";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-Naver-Client-Id", clientId);
//        headers.set("X-Naver-Client-Secret", clientSecret);
//
//        HttpEntity request = new HttpEntity(headers);
//        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("https://openapi.naver.com/v1/search/news.json")
//                .queryParam("query", "삼성")
//                .queryParam("display", 10)
//                .queryParam("sort", "sim");
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                uriBuilder.toUriString(),
//                HttpMethod.GET,
//                request,
//                String.class
//        );
//        System.out.println(response.toString());
//
//    }


}
