package io.guozhihong.system.controller;


import io.guozhihong.system.model.StudentModel;
import io.guozhihong.system.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/system/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/all")
    public List<StudentModel> getStudents(){
        return studentService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody//传回body到UI
    public String addStudent(@RequestBody StudentModel student){//requestBody JSON专用
            return studentService.add(student);
    }

    @GetMapping("/search/{sid}")
    public String getStudentCourses(@PathVariable Long sid){
        return studentService.getStudentCourses(sid);
    }

    @DeleteMapping("/del/{sid}")
    public String deleteStudent(@PathVariable Long sid) throws Exception {
        return studentService.deleteStudent(sid);
    }

    @DeleteMapping("/del/all")//"!非常危险"
    public String deleteAll(){
        return studentService.deleteAll();
    }

    @PutMapping("/update")
    @ResponseBody
    public String update(@RequestBody StudentModel student){
        return studentService.update(student);
    }

    @PutMapping("/update/name")
    public String updateName(@RequestParam("sid") Long sid,@RequestParam("s_name") String s_name){
        return studentService.updateName(sid,s_name);
    }

    @PutMapping("/update/sex")
    public String updateSex(@RequestParam("sid") Long sid,@RequestParam("s_sex") String s_sex){
        return studentService.updateSex(sid,s_sex);
    }
    @PutMapping("/update/age")
    public String updateAge(@RequestParam("sid") Long sid,@RequestParam("s_age") Integer s_age){
        return studentService.updateAge(sid,s_age);
    }

    @RequestMapping ("/invalidId")
    public String invalidId(){
        log.info("filter success");
        return "student Id does not exist";
    }
}
