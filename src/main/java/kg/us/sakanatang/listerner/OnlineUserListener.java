package kg.us.sakanatang.listerner;

import kg.us.sakanatang.utils.OnlineUserCounter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUserListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 已在拦截器中处理
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        OnlineUserCounter.removeUser(se.getSession().getId());
    }
}