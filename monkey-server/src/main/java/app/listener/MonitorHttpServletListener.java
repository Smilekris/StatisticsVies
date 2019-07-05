package app.listener;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

//@WebListener
public class MonitorHttpServletListener implements ServletRequestListener {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequestEvent initialLized");
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("ip->"+ip);
    }
}
