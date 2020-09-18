package io.guozhihong.demo.controller;
import io.guozhihong.demo.model.DemoModelCourse;
import io.guozhihong.demo.model.DemoModelStudent;
import io.guozhihong.demo.model.DemoModelTable;
import io.guozhihong.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/students")
    public List<DemoModelStudent> getStudents(){
        return demoService.findAllStudents();
    }

    @GetMapping("/courses")
    public List<DemoModelCourse> getCourses(){
        return demoService.findAllCourse();
    }

    @GetMapping("/tables")
    public List<DemoModelTable> getTable(){
        return demoService.findAllTables();
    }

    @PostMapping("/students/add")
    @ResponseBody
    public String addStudent(@RequestBody DemoModelStudent student){
        if(!demoService.existStudent(student.getSid())) {
            demoService.addStudent(student);
            return "添加学生 " + student.getSid() + "  " + student.getsName();
        }else {
            return "学生已存在";
        }
    }

    @PostMapping("/courses/add")
    @ResponseBody
    public String addCourse(@RequestBody DemoModelCourse course){
        if (!demoService.existCourse(course.getCid())){
            demoService.addCourse(course);
            return "添加课程 " + course.getCid() + " " + course.getcName();
        }else {
            return "课程已存在";
        }
    }

    @PostMapping("/tables/add")
    @ResponseBody
    public String addTable(@RequestBody DemoModelTable table){
        if(!demoService.existTable(table)){
            demoService.addTable(table);
            return "添加课程 " + table.getCid()+ " 到学生号 " + table.getSid();
        }else {
            return "这个课程已经被这个学生选过";
        }
    }

    @GetMapping("/students/search/{sid}")
    public String getStudentCourses(@PathVariable Long sid){
        if(demoService.existStudent(sid)){
            String s = "学生： " + demoService.findStudentById(sid).getsName() +
                    "\n学号： " + demoService.findStudentById(sid).getSid() +
                    "\n所选课程：\n" ;
            List<DemoModelCourse> courses = demoService.searchCourseBySid(sid);
            for (int i = 0; i < courses.size(); i++) {
                s += "\t\t" + courses.get(i).getCid() + "\t\t" + courses.get(i).getcName() + "\n";
            }
            return s;
        }else {
            return "学生不存在";
        }
    }

    @GetMapping("/courses/search/{cid}")
    public String getCourseStudents(@PathVariable Long cid){
        if(demoService.existCourse(cid)){
            String s = "课程号： " + demoService.findCourseById(cid).getCid() +
                    "\n课程名： " + demoService.findCourseById(cid).getcName() +
                    "\n选课学生： \n";
            List<DemoModelStudent> students = demoService.searchStudentsByCid(cid);
            for (int i = 0; i < students.size(); i++) {
                s += "\t\t" + students.get(i).getSid() + "\t\t" + students.get(i).getsName() + "\n";
            }
            return s;
        }else {
            return "课程不存在";
        }
    }
}
