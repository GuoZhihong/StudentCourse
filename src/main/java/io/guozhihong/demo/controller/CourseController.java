package io.guozhihong.demo.controller;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping("/courses")
    public List<CourseModel> getCourses(){
        return courseService.findAll();
    }

    @PostMapping("/courses/add")
    @ResponseBody
    public String addCourse(@RequestBody CourseModel course){
        return courseService.add(course);
    }

    @DeleteMapping("/courses/del/{cid}")
    public String deleteCourse(@PathVariable Long cid){
        return courseService.deleteCourse(cid);
    }

    @DeleteMapping("/courses/del/all")
    public String deleteAll(){//"!非常危险"
        return courseService.deleteAll();
    }

    @PutMapping("/courses/update")
    @ResponseBody
    public String update(@RequestBody CourseModel course){
        return courseService.update(course);
    }

    @GetMapping("/courses/search/{cid}")
    public String getCourseStudents(@PathVariable Long cid){
        return courseService.getCourseStudents(cid);
    }
}
