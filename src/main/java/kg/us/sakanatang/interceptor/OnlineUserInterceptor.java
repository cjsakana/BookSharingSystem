package kg.us.sakanatang.interceptor;

import kg.us.sakanatang.utils.OnlineUserCounter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class OnlineUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        System.out.println("sessionId: "+sessionId);
        System.out.println("session.isNew(): "+session.isNew());
        if (!OnlineUserCounter.containsUser(sessionId)) {
            OnlineUserCounter.addUser(sessionId);
        }

        return true;
    }
}