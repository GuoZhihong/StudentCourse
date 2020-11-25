package io.guozhihong.system.repo;

import io.guozhihong.system.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel,Long> {//必须是接口才可以 以前的类就不行了，泛型值为返回对象和其主键类型

    /*这里有个大坑 和jbdc一样 SQL语句 传参数据顺序必须和传到repo顺序一致*/
    @Modifying//update/add/delete
    @Query(value = "update student set s_name = ? where sid = ?",nativeQuery = true)//通过SQL语句查询
    public void updateStudentName(String s_name,Long sid);

    @Modifying//update/add/delete
    @Query(value = "update student set s_sex = ? where sid = ?",nativeQuery = true)//通过SQL语句查询
    public void updateStudentSex(String s_sex,Long sid);

    @Modifying//update/add/delete
    @Query(value = "update student set s_age = ? where sid = ?",nativeQuery = true)//通过SQL语句查询
    public void updateStudentAge(Integer s_age, Long sid);
}
