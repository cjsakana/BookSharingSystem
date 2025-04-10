package kg.us.sakanatang.controller.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kg.us.sakanatang.utils.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Calendar;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        try {
            // 从 Header 取
//            token = request.getHeader("Authorization");

            // 从 cookie 取
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }

            token = token.substring(7);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString("401"));
            return false;
        }
        DecodedJWT decodedJWT = JWTUtil.getTokenInfo(token);
        if (decodedJWT == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString("401"));
            return false;
        }


        // 验证 Token 是否过期
        Calendar currentTime = Calendar.getInstance();
        if (decodedJWT.getExpiresAt().before(currentTime.getTime())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString("401"));
            return false;
        }

        request.setAttribute("id", decodedJWT.getClaim("id").asInt());
        request.setAttribute("role", decodedJWT.getClaim("role").asInt());

        // 允许继续
        return true;
    }
}
