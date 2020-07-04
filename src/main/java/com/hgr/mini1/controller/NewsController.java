package com.hgr.mini1.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@Controller
public class NewsController {

    @GetMapping("/news")
    public String news() {
        return "news";
    }


    @GetMapping("/getNews")
    @ResponseBody
    public Map<String, Object> getNews(){
        RestTemplate rt = new RestTemplate();
        RequestEntity requestEntity = null;
        try {
            requestEntity = RequestEntity.get(
                    new URI("https://openapi.naver.com/v1/search/news.json?display=50&sort=sim&query=" +
                            URLEncoder.encode("블록체인", "utf-8")))
                    .header("X-Naver-Client-Id", "PP4F78Os1q39Iwoy1NOA")
                    .header("X-Naver-Client-Secret", "yYnKdT5xGM")
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
        Map<String, Object> result = entity.getBody();
        return result;
    }
}
