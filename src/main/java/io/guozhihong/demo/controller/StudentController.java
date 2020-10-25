package io.guozhihong.demo.controller;


import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public List<StudentModel> getStudents(){
        return studentService.findAll();
    }

    @PostMapping("/students/add")
    @ResponseBody//传回body到UI
    public String addStudent(@RequestBody StudentModel student){//requestBody JSON专用
            return studentService.add(student);
    }

    @GetMapping("/students/search/{sid}")
    public String getStudentCourses(@PathVariable Long sid){
        return studentService.getStudentCourses(sid);
    }

    @DeleteMapping("/students/del/{sid}")
    public String deleteStudent(@PathVariable Long sid){
        return studentService.deleteStudent(sid);
    }

    @DeleteMapping("/students/del/all")//"!非常危险"
    public String deleteAll(){
        return studentService.deleteAll();
    }

    @PutMapping("/students/update/name")
    public String updateName(@RequestParam("sid") Long sid,@RequestParam("sName") String sName){
        return studentService.updateName(sid,sName);
    }

    @PutMapping("/students/update/sex")
    public String updateSex(@RequestParam("sid") Long sid,@RequestParam("sSex") String sSex){
        return studentService.updateSex(sid,sSex);
    }
    @PutMapping("/students/update/age")
    public String updateAge(@RequestParam("sid") Long sid,@RequestParam("sAge") Integer sAge){
        return studentService.updateAge(sid,sAge);
    }

    @PutMapping("/students/update")
    @ResponseBody
    public String update(@RequestBody StudentModel student){
        return studentService.update(student);
    }
}