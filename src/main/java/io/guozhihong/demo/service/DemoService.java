package io.guozhihong.demo.service;


import io.guozhihong.demo.model.DemoModelCourse;
import io.guozhihong.demo.model.DemoModelStudent;
import io.guozhihong.demo.model.DemoModelTable;
import io.guozhihong.demo.repo.DemoCourseRepo;
import io.guozhihong.demo.repo.DemoStudentRepo;
import io.guozhihong.demo.repo.DemoTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoStudentRepo demoStudentRepo;
    @Autowired
    private DemoCourseRepo demoCourseRepo;
    @Autowired
    private DemoTableRepo demoTableRepo;

    public List<DemoModelTable> findAllTables(){
        return demoTableRepo.findAll();
    }
    public List<DemoModelStudent> findAllStudents(){
        return demoStudentRepo.findAll();
    }
    public List<DemoModelCourse> findAllCourse(){
        return demoCourseRepo.findAll();
    }

    public DemoModelTable findTableById(Long id){
        return demoTableRepo.findById(id);
    }

    public DemoModelStudent findStudentById(Long id){
        return demoStudentRepo.findById(id);
    }
    public DemoModelCourse findCourseById(Long id){
        return demoCourseRepo.findById(id);
    }

    public DemoModelStudent addStudent(DemoModelStudent student){
        return demoStudentRepo.add(student);
    }

    public DemoModelCourse addCourse(DemoModelCourse course){
        return demoCourseRepo.add(course);
    }

    public DemoModelTable addTable(DemoModelTable table){
        return demoTableRepo.add(table);
    }

    public boolean existStudent(Long sid){//如果已经包含这个学生的学号 就不做操作
        try {
            findStudentById(sid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean existCourse(Long cid){//如果已经包含这个课程的学号 就不做操作
        try {
            findCourseById(cid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean existTable(DemoModelTable table){
        List<DemoModelTable> tables = findAllTables();
        for (DemoModelTable x: tables) {
            if(x.getSid() == table.getSid() && x.getCid() == table.getCid()){//当学号和课号都有的情况下 是存在的
                return true;
            }
        }
        return false;
    }

    public List<DemoModelCourse> searchCourseBySid(Long sid){
        List<DemoModelCourse> courses = new ArrayList<>();
        for (DemoModelTable x: findAllTables()) {
            if(x.getSid() == sid){//跨表查询table和 student，一致从table里找出course表的id
                courses.add(findCourseById(x.getCid()));//总结一个学生id底下有的所有课程
            }
        }
        return courses;
    }

    public List<DemoModelStudent> searchStudentsByCid(Long cid){
        List<DemoModelStudent> students = new ArrayList<>();
        for (DemoModelTable x: findAllTables()) {
            if(x.getCid() == cid){//跨表查询table和 course，一致从table里找出student表的id
                students.add(findStudentById(x.getSid()));//总结一个课程id底下有的所有学生
            }
        }
        return students;
    }
}
