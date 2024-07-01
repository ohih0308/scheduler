package com.ohih.scheduler.config;

import com.ohih.scheduler.interceptor.LoginCheckInterceptor;
import com.ohih.scheduler.interceptor.LogoutCheckInterceptor;
import com.ohih.scheduler.webConstant.UrlConst;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    private final LogoutCheckInterceptor logoutCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor).addPathPatterns(UrlConst.SCHEDULER);
//
//        registry.addInterceptor(logoutCheckInterceptor).addPathPatterns(UrlConst.LOGIN, UrlConst.REGISTER);
    }
}
