package io.guozhihong.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter

@Entity
@Table(name = "student")
public class StudentModel {
    @Id
    private long sid;

    @Column
    private String s_name;
    private String s_sex;
    private int s_age;

}
