package io.guozhihong.system.service;

import io.guozhihong.system.model.CourseModel;
import io.guozhihong.system.model.StudentModel;
import io.guozhihong.system.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CourseService implements io.guozhihong.system.service.Service {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private TableService tableService;//需要跨表服务查询student表的相应记录

    @Transactional//该注解将为此方法添加事务
    public List<CourseModel> findAll(){
        return courseRepo.findAll();
    }

    @Transactional
    public CourseModel findById(Long id){
        return courseRepo.findById(id).get();
    }

    @Transactional
    synchronized public String add(CourseModel course){
        if (!courseRepo.existsById(course.getCid())){//Jpa 定义好的方法
            CourseModel c = courseRepo.saveAndFlush(course);//Jpa 定义好的方法
            return "add course id :" + c.getCid() + " name: " + c.getC_name();
        }else {
            return "course already exists.";
        }
    }

    @Transactional
    public String getCourseStudents(Long cid){
        String s = "course id： " + findById(cid).getCid() +
                "\n course ： " + findById(cid).getC_name() +
                "\n registered students： \n";
        List<StudentModel> students = tableService.searchStudentsByCid(cid);
        if(students.size() == 0){
            s += "\t\t\t\t\t none";
        }else {
            for (int i = 0; i < students.size(); i++) {
                s += "\t\t\t\t\t" + students.get(i).getSid() + "\t\t" + students.get(i).getS_name() + "\n";
            }
        }
        return s;
    }

    @Transactional
    synchronized public String deleteCourse(Long cid){
        tableService.deleteCourse(cid);//删除某个课程必须从选课表里把该课程所有记录都删除
        String s = "course : " + findById(cid).getC_name() + " course id: " + findById(cid).getCid() + " has been deleted.";
        courseRepo.deleteById(cid);
        return s;
    }

    @Transactional
    synchronized public String deleteAll(){
        tableService.deleteAll();//删除所有课程的同时删除相关的课程表
        courseRepo.deleteAll();//Jpa 定义好的方法
        return "delete all course records";
    }

    @Transactional
    synchronized public String update(CourseModel course){
        if(existCourse(course.getCid())){
            courseRepo.saveAndFlush(course);//JPA自动判定这个对象是否存在，存在更新，不存在则添加//saveAndFlush会立马添加到数据库save不会
            return "update course ： " + course.getC_name() +" as course id： " + course.getCid();
        }else {
            return "course does not exist.";
        }
    }

    @Transactional
    public Boolean existCourse(Long cid){
        return courseRepo.existsById(cid);
    }
}
