package com.smile.monkeyserver.restapi.impl;

import com.smile.monkeyserver.restapi.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @ClassName RestServiceImpl
 * @Author kris
 * @Date 2019/7/10
 **/
@Service
public class RestServiceImpl extends BaseRest implements RestService {

    @Override
    public <T> T monkeyGet(String url, Map<String, String> header, Class<T> clazz) {
        ResponseEntity<T> resultEntity = this.getForResponseEntity(url, null, header, clazz);
        if (HttpStatus.OK.equals(resultEntity.getStatusCode())) {
            T target = resultEntity.getBody();
            return target;
        } else {
            return null;
        }
    }

}
