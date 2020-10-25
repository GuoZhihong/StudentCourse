package io.guozhihong.demo.service;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.model.TableModel;
import io.guozhihong.demo.repo.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepo tableRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public List<TableModel> findAll(){
        return tableRepo.findAll();
    }

//    public TableModel findById(Long id){
//        return tableRepo.findById(id);
//    }

    synchronized public String add(TableModel table){
        if(!validTable(table)){//判断在student表和course表里是否有相应的记录
            return "input student id or course id does not exist.";
        }else {
            if(!existTable(table)){
                TableModel t = tableRepo.add(table);
                return "add course id: " + t.getCid()+ " to student id: " + t.getSid();
            }else {
                return "this course id has been already selected by this student id";
            }
        }
    }

    synchronized public String delete(Long sid, Long cid){
        TableModel tableModel = new TableModel(sid,cid);
        if (existTable(tableModel)){
            tableRepo.delete(sid,cid);
            return "delete course id： " + cid +" from student id " + sid;
        }else {
            return "this timetable record dose not exist.";
        }
    }

    synchronized public String deleteAll(){
        tableRepo.deleteAll();
        return "delete all records from timetable";
    }

    synchronized public String updateStudent(Long sid,Long cid,Long newsid){
        TableModel table = new TableModel(sid,cid);
        if(!existTable(table)){
            return "This record does not exist.";
        }else {
            if(studentService.existStudent(newsid)) {
                tableRepo.updateStudent(sid, cid, newsid);
                return "update course id ：" + cid + " from student id: " + sid + " to student id : " + newsid;
            }else {
                return "Invalid operation, that student id does not exist.";
            }
        }
    }


    synchronized public String updateCourse(Long sid,Long cid,Long newcid){
        TableModel table = new TableModel(sid,cid);
        if(!existTable(table)){
            return "This record does not exist.";
        }else {
            if(courseService.existCourse(newcid)){
                tableRepo.updateCourse(sid,cid,newcid);
                return "update student id ： " + sid + " with course id ： " + cid + " to another course id : " + newcid;
            }else {
                return "Invalid operation,that course id does not exist.";
            }
        }
    }

    /*因为timetable是由student和course两个表联合主键，所以不管删除student/course表里的记录timetable表要删除相应的值 要不会出现无效记录*/
    synchronized public void deleteStudent(Long sid){
        tableRepo.deleteStudent(sid);
    }
    synchronized public void deleteCourse(Long cid){
        tableRepo.deleteCourse(cid);
    }



    public List<StudentModel> searchStudentsByCid(Long cid){
        List<StudentModel> students = new ArrayList<>();
        for (TableModel x: findAll()) {
            if(x.getCid() == cid){//跨表查询table和 course，一致从table里找出student表的id
                students.add(studentService.findById(x.getSid()));//总结一个课程id底下有的所有学生
            }
        }
        return students;
    }

    public List<CourseModel> searchCourseBySid(Long sid){
        List<CourseModel> courses = new ArrayList<>();
        for (TableModel x: findAll()) {
            if(x.getSid() == sid){//跨表查询table和 student，一致从table里找出course表的id
                courses.add(courseService.findById(x.getCid()));//总结一个学生id底下有的所有课程
            }
        }
        return courses;
    }

    /*existTable和 validTable是两个不同的方法，existTable 是找table表里的重复记录 ，validTable 是找student和course表里的是否有对应的key*/
    public boolean existTable(TableModel table){
        List<TableModel> tables = findAll();
        for (TableModel x: tables) {
            if(x.getSid() == table.getSid() && x.getCid() == table.getCid()){//当学号和课号都有的情况下 是存在的
                return true;
            }
        }
        return false;
    }

    /*existTable和 validTable是两个不同的方法，existTable 是找table表里的重复记录 ，validTable 是找student和course表里的是否有对应的key*/
    public boolean validTable(TableModel table){
        if(studentService.existStudent(table.getSid()) && courseService.existCourse(table.getCid())){
            return true;
        }else {
            return false;
        }
    }
}
