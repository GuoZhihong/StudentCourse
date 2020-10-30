package io.guozhihong.demo.service;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TableService tableService;//需要跨表服务查询course表的相应记录

    @Transactional
    public List<StudentModel> findAll(){
        return studentRepo.findAll();//Jpa 定义好的方法
    }

    @Transactional
    public StudentModel findById(Long id){
        return studentRepo.findById(id).get();//Jpa 定义好的方法
    }

    @Transactional
    synchronized public String add(StudentModel student){

        if(!studentRepo.existsById(student.getSid())) {//Jpa 定义好的方法
            StudentModel s = studentRepo.saveAndFlush(student);//Jpa 定义好的方法
            return "add student id: " + s.getSid() + "  name :" + s.getS_name()
                    + " sex : " + s.getS_sex() + " age: " + s.getS_age();
        }else {
            return "this student already exists";
        }
    }

    @Transactional
    public String getStudentCourses(Long sid){
        if(studentRepo.existsById(sid)){//Jpa 定义好的方法
            String s = "student name： " + findById(sid).getS_name() +
                    "\n student id ： " + findById(sid).getSid() +
                    "\n selected courses：\n" ;
            List<CourseModel> courses = tableService.searchCourseBySid(sid);
            if(courses.size() == 0){
                s += "\t\t\t\t\tnone";
            }else {
                for (int i = 0; i < courses.size(); i++) {
                    s += "\t\t\t\t\t" + courses.get(i).getCid() + "\t\t" + courses.get(i).getC_name() + "\n";
                }
            }
            return s;
        }else {
            return "student does not exist.";
        }
    }

    @Transactional
    synchronized public String deleteStudent(Long sid){
        if(studentRepo.existsById(sid)){//Jpa 定义好的方法
            String s = "Student name : " + findById(sid).getS_name() +" Student id: " +
                    findById(sid).getSid() +" has been deleted.";
            tableService.deleteStudent(sid);//删除某个学生必须从选课表里把该学生所有记录都删除 //Jpa 定义好的方法
            studentRepo.deleteById(sid);//Jpa 定义好的方法
            return s;
        }else {
            return "This student does not exist.";
        }
    }

    @Transactional
    synchronized public String deleteAll(){
        tableService.deleteAll();//删除所有学生同时删除相关所有课程表
        studentRepo.deleteAll();//Jpa 定义好的方法
        return "delete all student records";
    }

    @Transactional
    synchronized public String update(StudentModel student){
        if(existStudent(student.getSid())){
            studentRepo.saveAndFlush(student);//Jpa 定义好的方法
            return "update student id： " + student.getSid() + " corresponding name to： " + student.getS_name() +
                    " corresponding sex to： " + student.getS_sex() + " corresponding age to： " + student.getS_age();
        }else {
            return "This student does not exist.";
        }
    }

    @Transactional
    synchronized public String updateName(Long sid,String s_name){
        studentRepo.updateStudentName(s_name,sid);
        return "update student id： " + studentRepo.findById(sid).get().getSid() + " corresponding name to ： " + studentRepo.findById(sid).get().getS_name();
    }

    @Transactional
    synchronized public String updateSex(Long sid, String s_sex){
        studentRepo.updateStudentSex(s_sex,sid);
        return "update student id： " + studentRepo.findById(sid).get().getSid() + " corresponding sex to ： " + studentRepo.findById(sid).get().getS_sex();
    }

    @Transactional
    synchronized public String updateAge(Long sid,Integer s_age){
        studentRepo.updateStudentAge(s_age,sid);
        return "update student id： " + studentRepo.findById(sid).get().getSid() + " corresponding age to： " + studentRepo.findById(sid).get().getS_age();
    }

    @Transactional
    public Boolean existStudent(Long sid){
        return studentRepo.existsById(sid);
    }
}
