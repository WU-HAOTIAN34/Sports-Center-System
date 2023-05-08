package com.gym1.util;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/comment/**")
                .addPathPatterns("/itemComment/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/itemOrder/**")
                .addPathPatterns("/venueState/**")
                .addPathPatterns("/speak/**")
                .addPathPatterns("/venueType/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/venue/**")
                .excludePathPatterns("/item/**");
    }
}
