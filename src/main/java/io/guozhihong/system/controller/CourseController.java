package io.guozhihong.system.controller;


import io.guozhihong.system.model.CourseModel;
import io.guozhihong.system.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/system/courses")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/all")
    public List<CourseModel> getCourses(){
        return courseService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public String addCourse(@RequestBody CourseModel course){//这里传进来的Json参数必须和model里的命名一致
        return courseService.add(course);
    }

    @DeleteMapping("/del/{cid}")
    public String deleteCourse(@PathVariable Long cid){
        return courseService.deleteCourse(cid);
    }

    @DeleteMapping("/del/all")
    public String deleteAll(){//"!非常危险"
        return courseService.deleteAll();
    }

    @PutMapping("/update")
    @ResponseBody
    public String update(@RequestBody CourseModel course){
        return courseService.update(course);
    }

    @GetMapping("/search/{cid}")
    public String getCourseStudents(@PathVariable Long cid){
        return courseService.getCourseStudents(cid);
    }

    @GetMapping ("/invalidId")
    public String invalidId(){
        log.info("filter success");
        return "Course Id does not exist";
    }
}
