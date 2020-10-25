package io.guozhihong.demo.repo;


import io.guozhihong.demo.model.CourseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  class CourseRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;//不需要dataSource，因为在properties文件已经设置

    public List<CourseModel> findAll(){
        String sql = "SELECT * FROM course ORDER BY cid";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(CourseModel.class));
    }
    public CourseModel findById(Long cid){
        String sql = "SELECT * FROM course WHERE cid=?";//有传参数进来
        return jdbcTemplate.queryForObject(sql,new Object[]{cid},new BeanPropertyRowMapper<>(CourseModel.class));//SQL,object[参数],Rowmapper
    }

    public CourseModel add(CourseModel course){
        String sql = "INSERT INTO course values(?,?)";
        jdbcTemplate.update(sql,course.getCid(),course.getcName());//SQL语句，和需要修改的两个参数
        return course;
    }

    public void update(CourseModel course){
        String sql = "UPDATE course SET cName = ? WHERE cid = ?";
        jdbcTemplate.update(sql,course.getcName(),course.getCid());
    }

    public void delete(Long cid){
        String sql = "DELETE FROM course where cid = ?";
        jdbcTemplate.update(sql,cid);
    }

    public void deleteAll(){
        String sql = "DELETE FROM course";
        jdbcTemplate.update(sql);
    }
}
