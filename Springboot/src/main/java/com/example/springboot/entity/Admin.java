package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//@Data替换getter setter代码
@Data
public class Admin {
    private Integer id;
    private String name;
    private String tel;
    private String sex;
    private Integer age;
    private String pos;
    private String ads;
    private String pwd;
    private String prior;
    private String birth;
    private String cause;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date in_time;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date out_time;
}

