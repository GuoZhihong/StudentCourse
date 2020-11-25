package io.guozhihong.system.repo;



import io.guozhihong.system.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepo extends JpaRepository<TableModel,Long> {//必须是接口才可以 以前的类就不行了，泛型值为返回对象和其主键类型

    @Modifying
    @Query(value = "update table_student_course set time = ? where sid = ? And cid = ?",nativeQuery = true)
    public void update(String time,Long sid,Long cid);

    public TableModel findBySidAndCid(Long sid,Long cid);

    public void deleteBySidAndCid(Long sid,Long cid);

    public void deleteTableModelsByCid(Long cid);

    public void deleteTableModelsBySid(Long sid);
}
