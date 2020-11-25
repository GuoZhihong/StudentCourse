package io.guozhihong.system.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter

@Entity
@Table(name = "student")
public class StudentModel implements Serializable {
    @Id
    private long sid;

    @Column
    private String s_name;
    private String s_sex;
    private int s_age;

}
