package io.guozhihong.demo.repo;



import io.guozhihong.demo.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;//不需要dataSource，因为在properties文件已经设置

    public List<TableModel> findAll(){
        String sql = "SELECT * FROM timetable ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(TableModel.class));
    }

//    public TableModel findById(Long id){
//        String sql = "SELECT * FROM timetable WHERE id=?";//有传参数进来
//        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(TableModel.class));//SQL,object[参数],Rowmapper
//    }

    public TableModel add(TableModel table){
        String sql = "INSERT INTO timetable VALUES(?,?,?)";
        jdbcTemplate.update(sql,table.getId(), table.getSid(),table.getCid());//SQL语句，和需要修改的两个参数
        return table;
    }

    public void delete(Long sid,Long cid){
        String sql = "DELETE FROM timetable WHERE sid = ? AND cid = ?";
        jdbcTemplate.update(sql,sid,cid);
    }


    /*因为timetable是由student和course两个表联合主键，所以不管删除student/course表里的记录timetable表要删除相应的值 要不会出现无效记录*/
    public void deleteStudent(Long sid){
        String sql = "DELETE FROM timetable WHERE sid = ?";
        jdbcTemplate.update(sql,sid);
    }
    public void deleteCourse(Long cid){
        String sql = "DELETE FROM timetable WHERE cid = ?";
        jdbcTemplate.update(sql,cid);
    }

    public void deleteAll(){
        String sql = "DELETE FROM timetable";
        jdbcTemplate.update(sql);
    }

    public void updateStudent(Long sid,Long cid,Long newsid){
        String sql = "UPDATE timetable SET sid = ? WHERE cid = ? AND sid = ?";
        jdbcTemplate.update(sql,newsid,cid,sid);
    }
    public void updateCourse(Long sid,Long cid,Long newcid){
        String sql = "UPDATE  timetable SET cid = ? WHERE sid = ? AND cid = ?";
        jdbcTemplate.update(sql,newcid,sid,cid);
    }
}
