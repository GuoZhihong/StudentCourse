package io.guozhihong.demo.model;



import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@lombok.Setter
@lombok.Getter
@Entity
@IdClass(TableModel.class)//对于有多个属性作为联合主键的情况加当前类的类名
@Table(name = "table_student_course")////对于有多个属性作为联合主键的情况这里必须写两个联合主键的表名
public class TableModel implements Serializable {//对于有多个属性作为联合主键的情况要实现 java.io.Serializable 接口

    @Id//联合主键
    private long sid;
    @Id
    private long cid;

    @Column(name = "time")
    private String time;

}
