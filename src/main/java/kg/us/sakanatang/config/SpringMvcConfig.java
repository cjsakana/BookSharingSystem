package kg.us.sakanatang.config;

import kg.us.sakanatang.interceptor.AuthInterceptor;
import kg.us.sakanatang.interceptor.OnlineUserInterceptor;
import kg.us.sakanatang.interceptor.RequestLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan({"kg.us.sakanatang.controller","kg.us.sakanatang.config",
        "kg.us.sakanatang.utils","kg.us.sakanatang.interceptor"})
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 放行静态资源
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    }

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private RequestLogInterceptor requestLogInterceptor;
    @Autowired
    OnlineUserInterceptor onlineUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLogInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/icon/**", "/static/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/icon/**", "/static/**")
                .excludePathPatterns("/api/email/sendVerifiedCode")
                .excludePathPatterns("/api/user/register","/api/user/login","/api/test");
        registry.addInterceptor(onlineUserInterceptor).addPathPatterns("/api/**");
    }
}
