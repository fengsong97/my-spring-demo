package com.fengsong97.spring.demo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.fengsong97.spring.demo.request.BookEntityRequest;
import io.swagger.models.HttpMethod;
import okhttp3.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 创建人 fengsong
 * 创建时间 2018/04/27 18:05
 **/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    String urlString = "http://localhost:8081";

    @GetMapping
    public String consumer() {


        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(urlString + "?name={name}&age={age}", String.class, "冯松", "21");
        return result;
    }

    @PostMapping(name = "/okHttpPost")
    public String okHttpPost() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build();
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        MediaType mediaType = MediaType.parse("application/json");

        BookEntityRequest bookEntityRequest =new BookEntityRequest();
        String content = JSONObject.toJSONString(bookEntityRequest);

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(urlString+"/book")
                .post(body)
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .build();

        try {

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            return "error";
        }

    }
}
