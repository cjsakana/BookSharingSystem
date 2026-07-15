package kg.us.sakanatang.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintStream;

@Component
public class RequestLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("URL: " + request.getRequestURI());
        // 如果需要记录更多信息
        System.out.println("URL: " +request.getMethod()+": " + request.getRequestURL().toString());
//        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Query: " + request.getQueryString());
        System.out.println();
        return true;
    }
}