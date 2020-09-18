package io.guozhihong.demo.repo;

import io.guozhihong.demo.model.DemoModelStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoStudentRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DemoModelStudent> findAll(){
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(DemoModelStudent.class));
    }

    public DemoModelStudent findById(Long sid){
        String sql = "SELECT * FROM student WHERE sid=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{sid},new BeanPropertyRowMapper<>(DemoModelStudent.class));
    }

    public DemoModelStudent add(DemoModelStudent student){
        String sql = "INSERT INTO student(sid,sName,sSex,sAge) values(?,?,?,?)";
        jdbcTemplate.update(sql,student.getSid(),student.getsName(),student.getsSex(),student.getsAge());
        return student;
    }
}
