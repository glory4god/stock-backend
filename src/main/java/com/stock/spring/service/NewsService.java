package com.stock.spring.service;

import com.stock.spring.domain.data.NewsKeywordRecordRepository;
import com.stock.spring.domain.data.NewsUrlRecordRepository;
import com.stock.spring.web.dto.news.NewsKeywordRecordRequestDto;
import com.stock.spring.web.dto.news.NewsUrlRecordRequestDto;
import com.stock.spring.web.dto.news.NewsUrlRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsKeywordRecordRepository newsKeywordRecordRepository;
    private final NewsUrlRecordRepository newsUrlRecordRepository;

    public Object getNews(String keyword, String sort) {
        String clientId = "VgK9ERVBz6_4f9p3RDzo";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "IALrdMLh3d";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+text+"&display=25&sort="+sort;
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

            NewsKeywordRecordRequestDto requestDto = new NewsKeywordRecordRequestDto(keyword);
            newsKeywordRecordRepository.save(requestDto.toEntity());

            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Long saveNewsUrl(NewsUrlRecordRequestDto requestDto) {
        return newsUrlRecordRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<String> getPopularKeywordByDaily() {
        return newsKeywordRecordRepository.getPopularKeywordByDaily().stream()
                .map((c)->c.getKeyword())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> getPopularKeywordByWeekly() {
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        String current = format.format (System.currentTimeMillis());
        cal.add(Calendar.DAY_OF_MONTH, -7);
        String intervalWeek = format.format (cal.getTime());

        return newsKeywordRecordRepository.getPopularKeywordByWeekly(current,intervalWeek).stream()
                .map((c)->c.getKeyword())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NewsUrlRecordResponseDto> getPopularUrlByDaily() {
        return newsUrlRecordRepository.getPopularNewsByDaily().stream()
                .map(NewsUrlRecordResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NewsUrlRecordResponseDto> getPopularUrlByWeekly() {
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        String current = format.format (System.currentTimeMillis());
        cal.add(Calendar.DAY_OF_MONTH, -7);
        String intervalWeek = format.format (cal.getTime());

        return newsUrlRecordRepository.getPopularNewsByWeekly(current,intervalWeek).stream()
                .map(NewsUrlRecordResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NewsUrlRecordResponseDto> getPopularUrlByKeyword(String keyword){
        return newsUrlRecordRepository.getPopularNewsByKeyword(keyword).stream()
                .map(NewsUrlRecordResponseDto::new)
                .collect(Collectors.toList());
    }



    public void getKeywords(String keyword) {

        String clientId = "VgK9ERVBz6_4f9p3RDzo";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "IALrdMLh3d";//애플리케이션 클라이언트 시크릿값";
        String startDate = "2021-05-28";
        String endDate = "2021-06-01";
        String timeUnit = "date";

        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = "{\"startDate\":\"2017-01-01\"," +
                "\"endDate\":\"2017-04-30\"," +
                "\"timeUnit\":\"month\"," +
                "\"keywordGroups\":[{\"groupName\":\"한글\"," + "\"keywords\":[\"한글\",\"korean\"]}," +
                "{\"groupName\":\"영어\"," + "\"keywords\":[\"영어\",\"english\"]}]," +
                "\"device\":\"pc\"," +
                "\"ages\":[\"1\",\"2\"]," +
                "\"gender\":\"f\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);
        System.out.println(responseBody);
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
