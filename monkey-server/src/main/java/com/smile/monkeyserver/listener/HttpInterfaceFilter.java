package com.smile.monkeyserver.listener;

import com.smile.monkeyapi.enitity.InterviewDTO;
import com.smile.monkeyserver.service.VistorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.smile.monkeyserver.util.IpUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author kris
 */
@WebFilter
public class HttpInterfaceFilter implements Filter {

    public final static Logger LOG = LoggerFactory.getLogger(HttpInterfaceFilter.class);

    public final static String STATICSURL = "/statistics/surf";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VistorService vistorService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse reponse = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        LOG.info("Filter session ->"+session);
        LOG.info("filter deal with url :"+request.getRequestURI());
        session.setMaxInactiveInterval(15);
        LOG.info("filter initialLized");
        if(STATICSURL.equals(request.getRequestURI())){
            String ip = IpUtil.getIpAddr(request);
            LOG.info("ip->"+ip);
            Boolean hasIp = redisTemplate.hasKey(ip);
            //接口访问则redis添加访问记录
            redisTemplate.opsForValue().set(ip,1,1, TimeUnit.MINUTES);
            if(!hasIp){
                vistorService.sendMQTask(ip,(new Date()).getTime());
            }else{
            }
        }
        filterChain.doFilter(request,reponse);
    }
}
