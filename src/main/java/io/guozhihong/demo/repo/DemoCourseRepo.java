package io.guozhihong.demo.repo;


import io.guozhihong.demo.model.DemoModelCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  class DemoCourseRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DemoModelCourse> findAll(){
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(DemoModelCourse.class));
    }
    public DemoModelCourse findById(Long cid){
        String sql = "SELECT * FROM course WHERE cid=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{cid},new BeanPropertyRowMapper<>(DemoModelCourse.class));
    }

    public DemoModelCourse add(DemoModelCourse course){
        String sql = "INSERT INTO course values(?,?)";
        jdbcTemplate.update(sql,course.getCid(),course.getcName());
        return course;
    }
}
