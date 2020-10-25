package io.guozhihong.demo.service;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TableService tableService;//需要跨表服务查询course表的相应记录

    public List<StudentModel> findAll(){
        return studentRepo.findAll();
    }

    public StudentModel findById(Long id){
        return studentRepo.findById(id);
    }

    synchronized public String add(StudentModel student){
        if(!existStudent(student.getSid())) {
            StudentModel s = studentRepo.add(student);
            return "add student id: " + s.getSid() + "  name :" + s.getsName()
                    + " sex : " + s.getsSex() + " age: " + s.getsAge();
        }else {
            return "this student already exists";
        }
    }

    public String getStudentCourses(Long sid){
        if(existStudent(sid)){
            String s = "student name： " + findById(sid).getsName() +
                    "\n student id ： " + findById(sid).getSid() +
                    "\n selected courses：\n" ;
            List<CourseModel> courses = tableService.searchCourseBySid(sid);
            if(courses.size() == 0){
                s += "\t\t\t\t\tnone";
            }else {
                for (int i = 0; i < courses.size(); i++) {
                    s += "\t\t\t\t\t" + courses.get(i).getCid() + "\t\t" + courses.get(i).getcName() + "\n";
                }
            }
            return s;
        }else {
            return "student does not exist.";
        }
    }
    synchronized public String deleteStudent(Long sid){
        if(existStudent(sid)){
            String s = "Student name : " + findById(sid).getsName() +" Student id: " +
                    findById(sid).getSid() +" has been deleted.";
            tableService.deleteStudent(sid);//删除某个学生必须从选课表里把该学生所有记录都删除
            studentRepo.delete(sid);
            return s;
        }else {
            return "This student does not exist.";
        }
    }

    synchronized public String deleteAll(){
        tableService.deleteAll();//删除所有学生同时删除相关所有课程表
        studentRepo.deleteAll();
        return "delete all student records";
    }

    synchronized public String updateName(Long sid,String sName){
        studentRepo.updateName(sid,sName);
        return "update student id： " + studentRepo.findById(sid).getSid() + " corresponding name to ： " + studentRepo.findById(sid).getsName();
    }


    synchronized public String updateSex(Long sid, String sSex){
        studentRepo.updateSex(sid,sSex);
        return "update student id： " + studentRepo.findById(sid).getSid() + " corresponding sex to ： " + studentRepo.findById(sid).getsSex();
    }

    synchronized public String updateAge(Long sid,Integer sAge){
        studentRepo.updateAge(sid,sAge);
        return "update student id： " + studentRepo.findById(sid).getSid() + " corresponding age to： " + studentRepo.findById(sid).getsAge();
    }

    synchronized public String update(StudentModel student){
        studentRepo.update(student);
        return "update student id： " + student.getSid() + " corresponding name to： " + student.getsName() +
                " corresponding sex to： " + student.getsSex() + " corresponding age to： " + student.getsAge();
    }


    public boolean existStudent(Long sid){//如果已经包含这个学生的学号 就不做操作
        try {
            findById(sid);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
