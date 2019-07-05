package app.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import app.util.IpUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebFilter
public class HttpInterfaceFilter implements Filter {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse reponse = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        System.out.println("Filter session ->"+session);
        System.out.println("filter deal with url :"+request.getRequestURI());
        session.setMaxInactiveInterval(10);
        System.out.println("filter initialLized");
        String ip = IpUtil.getIpAddr(request);
        System.out.println("ip->"+ip);
        Boolean hasIp = redisTemplate.hasKey(ip);
        //接口访问则redis添加访问记录
        if(!hasIp){
            redisTemplate.opsForValue().set(ip,1,1, TimeUnit.MINUTES);
        }else{

        }
        filterChain.doFilter(request,reponse);
    }
}
