package com.smile.monkeyserver.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName RestService
 * @Author kris
 * @Date 2019/7/10
 **/
public interface RestService {

    public <T> T monkeyGet(String url, Map<String, String> header, Class<T> clazz);
}
