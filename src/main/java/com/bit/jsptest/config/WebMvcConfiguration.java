package com.bit.jsptest.config;

import com.bit.jsptest.interceptor.ConfirmInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"com.**.mapper"})
//@EnableAspectJAutoProxy
//
public class WebMvcConfiguration implements WebMvcConfigurer{
    private final List<String> patterns = Arrays.asList("/guestbook/*", "/admin", "/user/list");
    @Autowired
    private ConfirmInterceptor confirmInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
    }


}
