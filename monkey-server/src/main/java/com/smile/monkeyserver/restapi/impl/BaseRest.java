package com.smile.monkeyserver.restapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName BaseRest
 * @Author yamei
 * @Date 2019/7/10
 **/
@Component
public  class BaseRest {
    @Autowired
    private RestTemplate restTemplate;

    private RequestEntity postEntity(String url, Object body) {
        return RequestEntity.post(URI.create(url))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(JSON.toJSONString(body));
    }

    private RequestEntity getEntity(Map<String,String> header, String url, Object body) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.setAll(header);
        return new RequestEntity(JSON.toJSONString(body),headers, HttpMethod.GET,URI.create(url));
    }

    protected <T> ResponseEntity<T> postForResponseEntity(String url, Object body, Class<T> clazz) {
        return restTemplate.exchange(postEntity(url,body), clazz);
    }

    protected <T> ResponseEntity<T> postForResponseEntity(String url, Object body, ParameterizedTypeReference<T> parameterizedTypeReference) {
        return restTemplate.exchange(postEntity(url,body), parameterizedTypeReference);
    }
}
