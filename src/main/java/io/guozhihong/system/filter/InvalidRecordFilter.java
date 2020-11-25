package io.guozhihong.system.filter;

import io.guozhihong.system.service.CourseService;
import io.guozhihong.system.service.Service;
import io.guozhihong.system.service.StudentService;
import io.guozhihong.system.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "invalidRecordFilter",urlPatterns = "/*")
public class InvalidRecordFilter implements Filter {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    TableService tableService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*getServletPath() http://localhost:8080/system/students/all 返回 /student/all */
        log.info("start filtering");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String path  = request.getServletPath();
        if(path.contains("/students/")){
            if(!validId(studentService,path)){
                servletRequest.getRequestDispatcher("/system/students/invalidId").forward(servletRequest,servletResponse);
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else if(path.contains("/courses/")){
            if(!validId(courseService,path)){
                servletRequest.getRequestDispatcher("/system/courses/invalidId").forward(servletRequest,servletResponse);
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else if(path.contains("/tables/")){
            if(!validId(tableService,path)){
                servletRequest.getRequestDispatcher("/system/tables/invalidId").forward(servletRequest,servletResponse);
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    private boolean validId(Service service, String path){
        String [] splitPath = path.split("/");
        Long id;
        if(path.contains("search") ){
            id = Long.parseLong(splitPath[splitPath.length - 1]);
            if(path.contains("students")){
                StudentService studentService = (StudentService) service;
                return studentService.existStudent(id);
            }else if(path.contains("courses")){
                CourseService courseService = (CourseService) service;
                return courseService.existCourse(id);
            }
        }else if(path.contains("del") && !path.contains("all")){
            if(path.contains("students")){
                id = Long.parseLong(splitPath[splitPath.length - 1]);
                StudentService studentService = (StudentService) service;
                return studentService.existStudent(id);
            }else if(path.contains("courses")){
                id = Long.parseLong(splitPath[splitPath.length - 1]);
                CourseService courseService = (CourseService) service;
                return courseService.existCourse(id);
            }
        }
        return true;
    }
}
