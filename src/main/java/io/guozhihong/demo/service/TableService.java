package io.guozhihong.demo.service;

import io.guozhihong.demo.model.CourseModel;
import io.guozhihong.demo.model.StudentModel;
import io.guozhihong.demo.model.TableModel;
import io.guozhihong.demo.repo.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<TableModel> findAll(){
        return tableRepo.findAll();
    }

    @Transactional
    synchronized public String add(TableModel table){
        if(!validTable(table)){//判断在student表和course表里是否有相应的记录
            return "input student id or course id does not exist.";
        }else {
            TableModel tableModel = tableRepo.findBySidAndCid(table.getSid(),table.getCid());
            if(tableModel == null){
                TableModel t = tableRepo.saveAndFlush(table);
                return "add course id: " + t.getCid()+ " to student id: " + t.getSid();
            }else {
                return "this course id has been already selected by this student id";
            }
        }
    }

    @Transactional
    synchronized public String delete(Long sid, Long cid){
        TableModel table = tableRepo.findBySidAndCid(sid,cid);
        if (table != null){
            tableRepo.deleteBySidAndCid(sid,cid);
            return "delete course id： " + cid +" from student id " + sid;
        }else {
            return "this timetable record dose not exist.";
        }
    }

    @Transactional
    synchronized public String deleteAll(){
        tableRepo.deleteAll();
        return "delete all records from timetable";
    }

    @Transactional
    synchronized public String update(TableModel tableModel){
        if(validTable(tableModel)){
            if(tableRepo.findBySidAndCid(tableModel.getSid(),tableModel.getCid()) == null){
                return "this timetable record dose not exist.";
            }else {
                String s = "";
                s += "previous record: " + tableRepo.findBySidAndCid(tableModel.getSid(),tableModel.getCid()).toString() + "\n";
                tableRepo.update(tableModel.getTime(),tableModel.getSid(),tableModel.getCid());
                TableModel newModel = tableRepo.findBySidAndCid(tableModel.getSid(),tableModel.getCid());
                s += "new record : " + newModel.toString();
                return s;
            }
        }else {
            return "input student id or course id does not exist";
        }
    }


    @Transactional
    /*因为timetable是由student和course两个表联合主键，所以不管删除student/course表里的记录timetable表要删除相应的值 要不会出现无效记录*/
    synchronized public void deleteStudent(Long sid){
        tableRepo.deleteTableModelsBySid(sid);
    }

    @Transactional
    synchronized public void deleteCourse(Long cid){
        tableRepo.deleteTableModelsByCid(cid);
    }

    @Transactional
    public List<StudentModel> searchStudentsByCid(Long cid){
        List<StudentModel> students = new ArrayList<>();
        for (TableModel x: findAll()) {
            if(x.getCid() == cid){//跨表查询table和 course，一致从table里找出student表的id
                students.add(studentService.findById(x.getSid()));//总结一个课程id底下有的所有学生
            }
        }
        return students;
    }

    @Transactional
    public List<CourseModel> searchCourseBySid(Long sid){
        List<CourseModel> courses = new ArrayList<>();
        for (TableModel x: findAll()) {
            if(x.getSid() == sid){//跨表查询table和 student，一致从table里找出course表的id
                courses.add(courseService.findById(x.getCid()));//总结一个学生id底下有的所有课程
            }
        }
        return courses;
    }

    @Transactional
    /*existTable和 validTable是两个不同的方法，existTable 是找table表里的重复记录 ，validTable 是找student和course表里的是否有对应的key*/
    public boolean validTable(TableModel table){
        if(studentService.existStudent(table.getSid()) && courseService.existCourse(table.getCid())){
            return true;
        }else {
            return false;
        }
    }
}
