package io.guozhihong.system.cofig;

import io.guozhihong.system.interceptor.RedirectionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*path interceptors for invalid URL*/
        registry.addInterceptor(new RedirectionInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/system/students/**","/system/courses/**","/system/tables/**","/index.html")
                .excludePathPatterns("/updateStudent.html","/updateCourse.html","/updateTimetable.html");
        //这里必须排除掉指向文件/index.html否则会形成指向循环
    }
}
