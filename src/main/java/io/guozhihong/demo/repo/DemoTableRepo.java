package io.guozhihong.demo.repo;



import io.guozhihong.demo.model.DemoModelTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoTableRepo{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DemoModelTable> findAll(){
        String sql = "SELECT * FROM timetable ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(DemoModelTable.class));
    }

    public DemoModelTable findById(Long id){
        String sql = "SELECT * FROM timetable WHERE id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(DemoModelTable.class));
    }
    public DemoModelTable add(DemoModelTable table){
        String sql = "INSERT INTO timetable VALUES(?,?,?)";
        jdbcTemplate.update(sql,table.getId(), table.getSid(),table.getCid());
        return table;
    }

}
