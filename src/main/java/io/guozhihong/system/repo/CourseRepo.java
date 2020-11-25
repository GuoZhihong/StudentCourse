package io.guozhihong.system.repo;


import io.guozhihong.system.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface CourseRepo extends JpaRepository<CourseModel,Long> {//必须是接口才可以 以前的类就不行了，泛型值为返回对象和其主键类型
}
