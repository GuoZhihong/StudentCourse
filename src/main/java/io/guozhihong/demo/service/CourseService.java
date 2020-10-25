package io.guozhihong.demo.service;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private TableService tableService;//需要跨表服务查询student表的相应记录

    public List<CourseModel> findAll(){
        return courseRepo.findAll();
    }

    public CourseModel findById(Long id){
        return courseRepo.findById(id);
    }

    synchronized public String add(CourseModel course){
        if (!existCourse(course.getCid())){
            CourseModel c = courseRepo.add(course);
            return "add course id :" + c.getCid() + " name: " + c.getcName();
        }else {
            return "course already exists.";
        }
    }

    public String getCourseStudents(Long cid){
        if(existCourse(cid)){
            String s = "course id： " + findById(cid).getCid() +
                    "\n course ： " + findById(cid).getcName() +
                    "\n registered students： \n";
            List<StudentModel> students = tableService.searchStudentsByCid(cid);
            if(students.size() == 0){
                s += "\t\t\t\t\t none";
            }else {
                for (int i = 0; i < students.size(); i++) {
                    s += "\t\t\t\t\t" + students.get(i).getSid() + "\t\t" + students.get(i).getsName() + "\n";
                }
            }
            return s;
        }else {
            return "course does not exist.";
        }
    }
    synchronized public String deleteCourse(Long cid){
        if(existCourse(cid)){
            tableService.deleteCourse(cid);//删除某个课程必须从选课表里把该课程所有记录都删除
            String s = "course : " + findById(cid).getcName() + " course id: " + findById(cid).getCid() + " has been deleted.";
            courseRepo.delete(cid);
            return s;
        }else {
            return "course does not exist.";
        }
    }

    synchronized public String deleteAll(){
        tableService.deleteAll();//删除所有课程的同时删除相关的课程表
        courseRepo.deleteAll();
        return "delete all course records";
    }

    synchronized public String update(CourseModel course){
        courseRepo.update(course);
        return "update course ： " + course.getcName() +" as course id： " + course.getCid();
    }


    public boolean existCourse(Long cid){//如果已经包含这个课程的学号 就不做操作
        try {
            findById(cid);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
