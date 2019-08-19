package com.smile.monkeyserver.listener;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * response 在拦截器返回
 * @ClassName MonkeyHandlerAdapter
 * @Author kris
 * @Date 2019/8/19
 **/
@Component
public class MonkeyHandlerAdapter extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains("surf")){
            return true;
        }else{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
//            Map<String,Object> outputMap = new HashMap<>();
//            outputMap.put("code",403);
//            outputMap.put("msg","you have no rights");
            JSONObject o = new JSONObject();
            o.put("code",403);
            o.put("msg","you have no rights");
            writer.write(o.toString());
            return false;
        }

    }
}
