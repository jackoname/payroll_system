package com.oyhj.cofig;

import com.oyhj.cofig.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Myinterceptor  implements WebMvcConfigurer {
    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(jwtValidateInterceptor);
        interceptorRegistration.addPathPatterns("/**").
                excludePathPatterns(
                        "/user/login",
                        "/user/info",
                        "/user/login",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**");
    }
}
