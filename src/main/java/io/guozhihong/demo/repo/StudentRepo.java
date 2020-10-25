package io.guozhihong.demo.repo;

import io.guozhihong.demo.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;//不需要dataSource，因为在properties文件已经设置

    public List<StudentModel> findAll(){
        String sql = "SELECT * FROM student ORDER BY sid";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(StudentModel.class));
    }

    public StudentModel findById(Long sid){
        String sql = "SELECT * FROM student WHERE sid=?";//有传参数进来
        return jdbcTemplate.queryForObject(sql,new Object[]{sid},new BeanPropertyRowMapper<>(StudentModel.class));//SQL,object[参数],Rowmapper
    }

    public StudentModel add(StudentModel student){
        String sql = "INSERT INTO student(sid,sName,sSex,sAge) values(?,?,?,?)";
        jdbcTemplate.update(sql,student.getSid(),student.getsName(),student.getsSex(),student.getsAge());//SQL语句，和需要修改的两个参数
        return student;
    }

    public void delete(Long sid){
        String sql = "DELETE FROM student WHERE sid = ?";
        jdbcTemplate.update(sql,sid);
    }

    public void deleteAll(){
        String sql = "DELETE FROM student";
        jdbcTemplate.update(sql);
    }

    public void update(StudentModel student){
        String sql = "UPDATE student SET sName = ? , sSex = ? , sAge = ? WHERE  sid =?";
        jdbcTemplate.update(sql,student.getsName(),student.getsSex(),student.getsAge(),student.getSid());
    }

    public void updateName(Long sid, String sName){
        String sql = "UPDATE student SET sName = ? WHERE  sid =?";
        jdbcTemplate.update(sql,sName,sid);//这的传参顺序必须和上面SQL一直 顺序错了就错了
    }

    public void updateSex(Long sid, String sSex){
        String sql = "UPDATE student SET sSex = ? WHERE  sid =?";
        jdbcTemplate.update(sql,sSex,sid);
    }

    public void updateAge(Long sid,Integer sAge){
        String sql = "UPDATE student SET sAge = ? WHERE  sid =?";
        jdbcTemplate.update(sql,sAge,sid);
    }

}
