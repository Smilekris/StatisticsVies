package com.smile.monkeyserver.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author kris
 *
 */

//@WebListener
//@WebFilter
public class SurfingSessionFilter implements HttpSessionListener, Filter {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse reponse = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(true);
//        System.out.println("Filter session ->"+session);
//        session.setMaxInactiveInterval(10);
//        System.out.println("filter initialLized");
//        String ip = IpUtil.getIpAddr(request);
//        System.out.println("ip->"+ip);
//        Boolean hasIp = redisTemplate.hasKey(ip);
//        //接口访问则redis添加访问记录
//        if(!hasIp){
//            redisTemplate.opsForValue().set(ip,1,1, TimeUnit.MINUTES);
//        }else{
//            redisTemplate.opsForValue().set(ip,1,1, TimeUnit.MINUTES);
//        }
//        filterChain.doFilter(request,reponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }


}
