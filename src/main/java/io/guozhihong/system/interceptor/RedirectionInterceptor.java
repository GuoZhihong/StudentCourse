package io.guozhihong.system.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*request.getContextPath() 返回项目名称 http://localhost:8080/system/students/all 返回 /system */
        response.sendRedirect(request.getContextPath() + "/index.html");// redirect to index page and let user to choose
        return false;
    }

}
