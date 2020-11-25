package io.guozhihong.system.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter

@Entity
@Table(name = "course") //自定义table名
public class CourseModel implements Serializable {

    @Id //主键
    private Long cid;

    @Column(name = "c_name")//自定义表里column名字
    private String c_name;
    @Column(name = "credits")
    private Double credits;
    @Column(name = "lecturer")
    private String lecturerName;

}
